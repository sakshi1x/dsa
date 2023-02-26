public class MatrixMultiplication {
    private static int[][] A;
    private static int[][] B;
    private static int[][] C;

    public static void main(String[] args) {
        int n = 1000; // Size of the matrices
        int numThreads = 4; // Number of threads to use

        // Initialize matrices with random values
        A = new int[n][n];
        B = new int[n][n];
        C = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = rand.nextInt(10);
                B[i][j] = rand.nextInt(10);
            }
        }

        // Create and start worker threads
        List<WorkerThread> threads = new ArrayList<>();
        int blockSize = n / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int startRow = i * blockSize;
            int endRow = startRow + blockSize;
            threads.add(new WorkerThread(startRow, endRow));
            threads.get(i).start();
        }

        // Wait for worker threads to finish
        try {
            for (WorkerThread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the result matrix
        System.out.println("Result matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Worker thread class
    private static class WorkerThread extends Thread {
        private int startRow;
        private int endRow;

        public WorkerThread(int startRow, int endRow) {
            this.startRow = startRow;
            this.endRow = endRow;
        }

        public void run() {
            int n = A.length;
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
    }
}