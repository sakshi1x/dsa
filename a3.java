public static int minProductDiff(int[] nums) {
        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;

        for (int i = 1; i < (1 << n); i++) {
        int count = Integer.bitCount(i);
        if (count != n / 2) continue;

        int prod1 = 1, prod2 = 1;
        for (int j = 0; j < n; j++) {
        if ((i & (1 << j)) != 0) {
        prod1 *= nums[j];
        } else {
        prod2 *= nums[j];
        }
        }

        minDiff = Math.min(minDiff, Math.abs(prod1 - prod2));
        }

        return minDiff;
        }
