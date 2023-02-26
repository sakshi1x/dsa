import java.util.*;

 class NetworkDevices {

    public static List<Integer> getImpactedDevices(int[][] edges, int n) {
        Map<Integer, List<Integer>> adjList = buildAdjacencyList(edges);
        Set<Integer> reachableFromGateway = new HashSet<>();
        Set<Integer> reachableFromFailedDevice = new HashSet<>();

        // first DFS traversal from the gateway
        dfs(adjList, reachableFromGateway, 0, new HashSet<>());

        // second DFS traversal from the failed device
        dfs(adjList, reachableFromFailedDevice, n, new HashSet<>());

        // find the devices that are reachable from the failed device, but not from the gateway
        List<Integer> impactedDevices = new ArrayList<>();
        for (int device : reachableFromFailedDevice) {
            if (!reachableFromGateway.contains(device)) {
                impactedDevices.add(device);
            }
        }
        return impactedDevices;
    }

    private static Map<Integer, List<Integer>> buildAdjacencyList(int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }
        return adjList;
    }

    private static void dfs(Map<Integer, List<Integer>> adjList, Set<Integer> visited, int node, Set<Integer> blocked) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        for (int neighbor : adjList.getOrDefault(node, Collections.emptyList())) {
            if (!blocked.contains(neighbor)) {
                dfs(adjList, visited, neighbor, blocked);
            }
        }
    }

}