import java.util.*;

 class CheapestRoute {
    public static int cheapestRoute(int[][] edge, int[] charges, int source, int destination, int timeConstraint) {
        // Initialize an empty priority queue and a HashMap to keep track of the minimum cost and time to reach each country
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        HashMap<Integer, int[]> minCostTime = new HashMap<>();
        for (int i = 0; i < charges.length; i++) {
            minCostTime.put(i, new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE});
        }
        minCostTime.put(source, new int[]{0, 0});
        pq.offer(new int[]{0, source, 0});

        // While the priority queue is not empty
        while (!pq.isEmpty()) {
            // Pop the country with the minimum cost and time from the priority queue
            int[] curr = pq.poll();
            int currCost = curr[0];
            int currCountry = curr[1];
            int currTime = curr[2];

            // If the popped country is the destination country, return the minimum cost to reach it
            if (currCountry == destination) {
                return currCost;
            }

            // For each neighbor of the current country
            for (int i = 0; i < edge.length; i++) {
                int src = edge[i][0];
                int dest = edge[i][1];
                int time = edge[i][2];
                if (src == currCountry) {
                    // Check if the time required to reach the neighbor plus the current time is less than or equal to the time constraint
                    int totalTime = currTime + time;
                    if (totalTime <= timeConstraint) {
                        // Compute the total cost to reach the neighbor by adding the cost of entering the neighbor country to the cost of the current country
                        int totalCost = currCost + charges[i];
                        // If the total cost is less than the minimum cost to reach the neighbor so far, update the minimum cost and time to reach the neighbor
                        if (totalCost < minCostTime.get(dest)[0] || (totalCost == minCostTime.get(dest)[0] && totalTime < minCostTime.get(dest)[1])) {
                            minCostTime.put(dest, new int[]{totalCost, totalTime});
                            // Add the neighbor to the priority queue with the updated cost and time
                            pq.offer(new int[]{totalCost, dest, totalTime});
                        }
                    }
                }
            }
        }

        // If we have exhausted all possible paths that meet the time constraint and have not reached the destination country, return -1
        return -1;
    }

    public static void main(String[] args) {
        int[][] edge = {{0, 1, 5}, {0, 3, 2}, {1, 2, 5}, {3, 4, 5}, {4, 5, 6}, {2, 5, 5}};
        int[] charges = {10, 2, 3, 25, 25, 4};
        int source = 0;
        int destination = 5;
        int timeConstraint = 14;
        int cheapestCost = cheapestRoute(edge, charges, source, destination, timeConstraint);
        System.out.println("Cheapest cost to reach destination: " + cheapestCost);
    }
}