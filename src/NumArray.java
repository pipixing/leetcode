public class NumArray {
    private int[] sums;

    public NumArray(int[] nums) {
        int len = nums.length;
        sums = new int[len];
        int sum = 0;
        for (int i = 0; i < len; ++i) {
            sum += nums[i];
            sums[i] = sum;
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0)
            return sums[j];
        return sums[j] - sums[i - 1];
    }
}
