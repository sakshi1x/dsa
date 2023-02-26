import java.util.*;

// Node class for building Huffman tree
class Node implements Comparable<Node> {
    int freq;
    char c;
    Node left, right;

    public Node(int freq, char c) {
        this.freq = freq;
        this.c = c;
    }

    public Node(int freq, Node left, Node right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public int compareTo(Node other) {
        return freq - other.freq;
    }
}

public class Huffman {
    public static void main(String[] args) {
        String text = "Hello, world!";
        String encodedText = encode(text);
        System.out.println("Encoded text: " + encodedText);

        String decodedText = decode(encodedText);
        System.out.println("Decoded text: " + decodedText);

        // Check if original and decoded texts match
        System.out.println("Original text matches decoded text? " + text.equals(decodedText));
    }

    public static String encode(String text) {
        // Create frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Build Huffman tree
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (char c : freqMap.keySet()) {
            pq.offer(new Node(freqMap.get(c), c));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(left.freq + right.freq, left, right);
            pq.offer(parent);
        }

        Node root = pq.poll();

        // Generate Huffman codes
        Map<Character, String> codeMap = new HashMap<>();
        generateCodes(root, "", codeMap);

        // Encode text using Huffman codes
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(codeMap.get(c));
        }

        return sb.toString();
    }

    public static String decode(String encodedText) {
        StringBuilder sb = new StringBuilder();
        Node root = buildTree(encodedText);
        Node curr = root;

        for (int i = 0; i < encodedText.length(); i++) {
            char c = encodedText.charAt(i);
            if (c == '0') {
                curr = curr.left;
            } else {
                curr = curr.right;
            }

            if (curr.isLeaf()) {
                sb.append(curr.c);
                curr = root;
            }
        }

        return sb.toString();
    }

    private static void generateCodes(Node node, String code, Map<Character, String> codeMap) {
        if (node.isLeaf()) {
            codeMap.put(node.c, code);
            return;
        }

        generateCodes(node.left, code + "0", codeMap);
        generateCodes(node.right, code + "1", codeMap);
    }

    private static Node buildTree(String encodedText) {
        Node root = null;
        Node curr = null;

        for (int i = 0; i < encodedText.length(); i++) {
            char c = encodedText.charAt(i);
            if (c == '0') {
                if (curr.left == null) {
                    curr.left = new Node(0, null, null);
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = new Node(0, null, null);}}}}}