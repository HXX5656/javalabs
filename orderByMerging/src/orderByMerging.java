//该程序是归并排序的实现，使用了递归的方法
public class orderByMerging {
    //main方法用来找到入口，我随便打了一个数组进行测试
    public static void main(String[] args) {
        int[] list={1,2,3,5,9,100,78,90,101,48,78};
        mergeSort(list,0,list.length-1);
        //打印出排好序的数组
        for (int i=0;i<list.length;i++ ) {
            System.out.print(" "+ list[i]);
        }
    }
    //merge方法是实现归并的方法，即将两个数组按大小顺序合并
    public static void merge(int[] a, int low, int mid, int high) {
        //新建一个数组来储存排序后的信息
        int[] mergeAfter = new int[high - low + 1];
        //其中low是排序部分的起始位置，high是排序的终点位置，mid用来将待排序数组划分为左右两部分
        //让左边从low开始，右边从mid+1开始，定义两个初始位置
        int i = low;
        int j = mid + 1;
        //对于左右两部分的元素先进行比较，取其中较小的放入数组，且将对应部分的下标加一
        int k = 0;
        // 把较小的数先移到新数组中，直到其中一边的元素移完
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                mergeAfter[k] = a[i];
                k++;
                i++;
            } else {
                mergeAfter[k] = a[j];
                k++;
                j++;
            }
        }
        //若左边剩余 ，把左边剩余的数移入数组
        while (i <= mid) {
            mergeAfter[k] = a[i];
            k++;
            i++;
        }
        //若右边剩余， 把右边边剩余的数移入数组
        while (j <= high) {
            mergeAfter[k] = a[j];
            k++;
            j++;
        }
        // 把新数组中排好序的数copy并覆盖到以前的数组中去
        for (int k2 = 0; k2 < mergeAfter.length; k2++) {
            a[k2 + low] = mergeAfter[k2];
        }
    }
    //归并排序方法，不断的将数组递归，直到最终只剩一个元素，然后再返回归并，使每个数组都是排好序的再合并
    public static void mergeSort(int[] a, int low, int high) {
        //这里mid会强制转换为int类型的，mid将数组分为两部分
        int mid = (low + high) / 2;
        //用if判断是否到达基础情况，即每个数组中只有一个元素
        if (low < high) {
            // 左边归并排序
            mergeSort(a, low, mid);
            // 右边归并排序
            mergeSort(a, mid + 1, high);
        }
            // 再将左右合并
            merge(a, low, mid, high);



    }
}
