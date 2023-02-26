public static List<Integer> findBorderCoordinates(int[][] height) {
        List<Integer> keyPoints = new ArrayList<>();
        int n = height.length;

        // Check for the left border of the first rectangle
        if (height[0][0] != 0) {
        keyPoints.add(height[0][0]);
        keyPoints.add(0);
        }

        for (int i = 0; i < n; i++) {
        int left = height[i][0];
        int right = height[i][1];
        int currHeight = height[i][2];

        // Check for left border
        if (i == 0 || height[i - 1][2] < currHeight) {
        keyPoints.add(left);
        keyPoints.add(currHeight);
        }

        // Check for right border
        if (i == n - 1 || height[i + 1][2] < currHeight) {
        keyPoints.add(right);
        keyPoints.add(currHeight);
        }
        }

        // Check for the right border of the last rectangle
        if (height[n - 1][1] != 0) {
        keyPoints.add(height[n - 1][1]);
        keyPoints.add(0);
        }

        return keyPoints;
        }