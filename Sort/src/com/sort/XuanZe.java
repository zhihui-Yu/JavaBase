package com.sort;

public class XuanZe {
    /**
     * 选择：从数组中选择最小的数与第一个交换
     * 重复步骤选择出第二第三...
     *
     * @param arr   数组
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    public static long sort(int[] arr, long start, long end) {
        start = System.currentTimeMillis();
        //定义一个交互变量
        int temp;
        //每个位置都要选出最小的  所以有几个数 就有几轮
        for (int i = 0; i < arr.length - 1; i++) {
            //获取地址
            int min = i;
            //自身不用比,i之前的数据都已经排序完，顾也不用比
            for (int j = i + 1; j < arr.length - 1; j++) {
                //如果有比当前小的
                if (arr[j] < arr[min]) {
                    //最小的索引变化
                    min = j;
                }
            }
            //有变化过
            if (min != i) {
                temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }

        end = System.currentTimeMillis();
        return (end - start);
    }

}
