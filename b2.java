public static int minServiceCenters(int[] tree) {
        int[] centers = new int[1];
        minServiceCenters(tree, 0, centers);
        return centers[0];
        }

private static int minServiceCenters(int[] tree, int node, int[] centers) {
        if (node >= tree.length || tree[node] == -1) {
        return 0; // leaf node or empty node
        }
        int numCenters = 0;
        for (int child : new int[]{2*node+1, 2*node+2}) {
        numCenters += minServiceCenters(tree, child, centers);
        }
        if (numCenters >= 2 || tree[node] == 1) {
        centers[0]++;
        return 1;
        }
        return numCenters;
        }