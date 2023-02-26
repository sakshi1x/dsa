Copy code
public class MaxZeroSquare {
    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};
        int[] result = findMaxZeroSquare(matrix);
        System.out.println("Maximum zero square has size " + result[2] + " and starts at (" + result[0] + ", " + result[1] + ")");
    }

    public static int[] findMaxZeroSquare(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        int[] maxPos = new int[2];

        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
            if (dp[i][0] > max) {
                max = dp[i][0];
                maxPos[0] = i;
                maxPos[1] = 0;
            }
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
            if (dp[0][j] > max) {
                max = dp[0][j];
                maxPos[0] = 0;
                maxPos[1] = j;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        maxPos[0] = i;
                        maxPos[1] = j;
                    }
                }
            }
        }

        int[] result = new int[3];
        result[0] = maxPos[0] - max + 1;
        result[1] = maxPos[1] - max + 1;
        result[2] = max;
        return result;
    }
}