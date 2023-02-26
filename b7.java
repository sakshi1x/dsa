import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class WebCrawler {
    private final int MAX_PAGES = 1000;
    private final int MAX_DEPTH = 10;
    private final int NUM_WORKERS = 5;
    private final int NUM_PARSERS = 5;

    private Set<String> visitedPages;
    private BlockingQueue<Page> pageQueue;
    private BlockingQueue<String> linkQueue;

    private static class Page {
        private final String url;
        private final String content;
        private final int depth;

        public Page(String url, String content, int depth) {
            this.url = url;
            this.content = content;
            this.depth = depth;
        }

        public String getUrl() {
            return url;
        }

        public String getContent() {
            return content;
        }

        public int getDepth() {
            return depth;
        }
    }

    private class Worker implements Runnable {
        private final HttpClient client;

        public Worker() {
            this.client = HttpClient.newHttpClient();
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Page page = pageQueue.take();
                    if (visitedPages.contains(page.getUrl())) {
                        continue;
                    }
                    visitedPages.add(page.getUrl());
                    if (page.getDepth() >= MAX_DEPTH) {
                        continue;
                    }
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(page.getUrl()))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() == 200) {
                        String content = response.body();
                        linkQueue.put(page.getUrl());
                        pageQueue.put(new Page(page.getUrl(), content, page.getDepth() + 1));
                    }
                } catch (InterruptedException | IOException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private class Parser implements Runnable {
        private final Pattern linkPattern = Pattern.compile("<a href=\"(.*?)\"");

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String url = linkQueue.take();
                    Page page = getPage(url);
                    if (page != null) {
                        Matcher matcher = linkPattern.matcher(page.getContent());
                        while (matcher.find()) {
                            String link = matcher.group(1);
                            if (link.startsWith("http")) {
                                pageQueue.put(new Page(link, null, 0));
                            } else if (link.startsWith("/")) {
                                URL baseUrl = new URL(url);
                                pageQueue.put(new Page(baseUrl.getProtocol() + "://" + baseUrl.getHost() + link, null, 0));
                            }
                        }
                    }
                } catch (InterruptedException | IOException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        private Page getPage(String url) throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return new Page(url, response.body(), 0);
            }