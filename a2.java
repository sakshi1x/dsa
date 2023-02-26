import java.util.*;

 class Solution {
    public int[] nearestAncestor(int[] values, int[][] edges) {
        int n = values.length;
        int[] gcd = new int[n];
        Arrays.fill(gcd, -1);
        gcd[0] = values[0];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        parent[0] = 0;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        result[0] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int[] edge : edges) {
                if (edge[0] == curr || edge[1] == curr) {
                    int next = edge[0] == curr ? edge[1] : edge[0];
                    if (parent[next] == -1) {
                        parent[next] = curr;
                        q.offer(next);
                        gcd[next] = values[next];
                    }
                    if (next != parent[curr]) {
                        gcd[next] = gcd(gcd[curr], values[next]);
                        if (gcd[gcd.length-1] == 1) {
                            result[next] = gcd[curr];
                        } else if (result[curr] != -1) {
                            result[next] = result[curr];
                        }
                    }
                }
            }
        }
        return result;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}