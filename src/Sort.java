import java.util.Arrays;

public class Sort {

    /**
     * @Author: PPX
     * @Description: 快排
     * @Date: 2019-09-27
     **/
    public static class QuickSort {
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

    /**
     * @Author: PPX
     * @Description: 堆排序
     * @Date: 2019-09-27
     **/
    static class HeapSort {
        public static void main(String[] args) {
            int[] arr = {7, 6, 7, 11, 5, 12, 3, 0, 1};
            System.out.println("排序前：" + Arrays.toString(arr));
            sort(arr);
            System.out.println("排序前：" + Arrays.toString(arr));
        }

        public static void sort(int[] arr) {
            //1.构建大顶堆
            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                //从第一个非叶子结点从下至上，从右至左调整结构
                adjustHeap(arr, i, arr.length);
            }
            //2.调整堆结构+交换堆顶元素与末尾元素
            for (int j = arr.length - 1; j > 0; j--) {
                swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
                adjustHeap(arr, 0, j);//重新对堆进行调整
            }

        }

        /**
         * @Author: PPX
         * @Description: 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
         * @Date: 2019-09-27
         **/
        public static void adjustHeap(int[] arr, int i, int length) {
            for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
                if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                    k++;
                }
                if (arr[k] > arr[i]) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                    swap(arr, i, k);
                } else {
                    break;
                }
            }
        }

        public static void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}
