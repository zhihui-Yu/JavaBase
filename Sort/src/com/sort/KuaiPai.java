package com.sort;

public class KuaiPai {

    public static void quickSort(int A[], int p, int q) {
        if (p < q) {
//			将数组分成3部分：p~r-1,r,r+1~q
            int r = partition(A, p, q);
            quickSort(A, p, r - 1);
            quickSort(A, r + 1, q);
        }
    }

    static int partition(int A[], int p, int q) {//划分主元，将比主元小的元素放在左边，比主元大的放在右边
        int x = A[p], i = p, j, swap;
        for (j = p + 1; j <= q; j++) {
            if (A[j] <= x) {
                i++;//i指向的是这一轮循环中最后一个比主元小的元素
                swap = A[i];
                A[i] = A[j];
                A[j] = swap;
            }
        }
        swap = A[i];
        A[i] = A[p];
        A[p] = swap;
        return i;//返回本轮循环结束后主元所在的位置，之后主元不需要再参与比较
    }

    /**
     * ----升序---- 快排：以第一个数为基数， 先从后往前比较：
     * 小于基数则换位置，换完位置则以基数的位置为指针，以原来的位置为头指针
     * 从前往后比较 大于则尾指针向前移动一位
     * <p>
     * 从前往后比较： 头指针数小于基数，则位置不变，头指针向前移动 大于时
     * 与基数换位置，基数指针位置为换完的位置，然后从后往前比较
     * <p>
     * 直到头指针位置大于或等于尾指针位置(第一个比较完成)
     * <p>
     * 将数组分成两边 在做快排，直到左右不可分
     *
     * @param arr   数组
     * @param begin 头指针位置
     * @param last  尾指针位置
     * @return
     */
    public static void sort(int[] arr, int begin, int last) {

        int start = begin;
        int end = last;
        // 头大于等于尾 则结束
        if (begin >= last) {
            return;
        }

        // 确认基数
        int x = arr[begin];

        // 循环
        while (begin < last) {
            // 从后往前找
            while (begin < last && arr[last] >= x) {
                last--;
            }
            // 如果是在范围之内找到 则交换位置
            if (begin < last) {
                arr[begin] = arr[last];
                // 头指针位置前进一位
                begin++;
            }
            // 从前往后找
            while (begin < last && arr[begin] <= x) {
                begin++;
            }
            // 头指针所指的数大于基数
            if (begin < last) {
                arr[last] = arr[begin];
                // 尾指针回退一位
                last--;
            }

        }
        // 最后begin >= last 将基数赋予begin 或 last 都可以
        // 因为最后begin会在等于last时候退出循环
        arr[begin] = x;
        // 循环结束 表示一个数已经排序完 递归排序左右两边
        // 左边递归排序
        sort(arr, start, begin - 1);
        // 右边递归排序
        sort(arr, begin + 1, end);
    }
}
