package com.syc.demo;

/**
 * Created by shiyucheng on 2018/10/23.
 */

public class Test {
    private static int[] a  = {10, 9, 8, 7, 6, 5, 3, 4, 2, 1, 0};
    public static void main(String... args){
        fastSort(a,0,a.length-1);
//        shellSort(a);
        for (int i:a) {
            System.out.print(i+"--");
        }
    }

    private static void fastSort(int[] a ,int low,int height){
//        int start = low;
//        int end = height;
//        int key = a[low];
//        while(end > start){
//            while (end > start && key <= a[end]){
//                end --;
//            }
//
//            if(key > a[end]){
//                int temp = a[start];
//                a[start] = a[end];
//                a[end] = temp;
//            }
//
//
//            while(end > start && key >= a[start]){
//                start++;
//            }
//            if(key < a[start]){
//                int temp = a[start];
//                a[start] = a[end];
//                a[end] = temp;
//            }
//
//        }
//        if(start > low){
//            fastSort(a,low,start-1);
//        }
//
//        if(end < height){
//            fastSort(a,end+1,height);
//        }
        int start = low;
        int end = height;
        int key = a[low];
        while(end > start){
            while (end > start && key <= a[end]){
                end--;
            }

            if(key>a[end]){
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }

            while (end > start && key >= a[start]){
                start++;
            }

            if(key<a[start]){
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
        }
        if(start > low){
            fastSort(a,low,start-1);
        }

        if(end < height){
            fastSort(a,end+1,height);
        }
    }

    private static void shellSort(int[] a){
//        for(int i = a.length/2;i>0;i/=2){
//            for(int j = i;j<a.length;j++){
//                int temp = a[j];
//                int r= j-i;
//                while (r >= 0 && temp < a[r]){
//                    a[r+i] = a[r];
//                    r -= i;
//                }
//                a[r+i] = temp;
//            }
//        }
        for(int i = a.length/2;i>0;i/=2){
            for(int j = i;j<a.length;j++){
                int temp = a[j];
                int r = j -i;
                while (r >= 0 && temp < a[r]){
                    a[r + i ] = a[r];
                    r = r- i;
                }
                a[r + i] = temp;
            }
        }
    }
}
