public class QuickSort {
    public static int[] quickSort(int[] digits) {
        if (digits.length < 2)
            return digits;
        quickSortCore(digits, 0, digits.length - 1);
        return digits;
    }

    private static void quickSortCore(int[] digits, int start, int end) {
        if (start > end)
            return;
        int temp = digits[start], l = start, h = end;
        while (l < h) {
            while (h > l && digits[h] >= temp) {
                h--;
            }
            //先取尾，后取头
            while (l < h && digits[l] <= temp) {
                l++;
            }
            if (l < h)
                swap(digits, l, h);
        }
        //
        swap(digits, start, l);
        quickSortCore(digits, start, h - 1);
        quickSortCore(digits, h + 1, end);
    }

    private static void swap(int[] digits, int l, int h) {
        int temp = digits[l];
        digits[l] = digits[h];
        digits[h] = temp;
    }
}
