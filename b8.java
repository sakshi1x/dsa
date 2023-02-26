public static int findKthMissingEven(int[] arr, int k) {
        int missingCount = 0;
        int prev = -2; // initialize to -2 since arr[0] can be 0
        for (int i = 0; i < arr.length; i++) {
        missingCount += (arr[i] - prev - 2) / 2;
        if (missingCount >= k) {
        return arr[i] - (missingCount - k) * 2;
        }
        prev = arr[i];
        }
        return -1;
        }