package algorithm;

public class Qsort {
	public static void quickSort(int[] array){
	    qsort(array, 0, array.length-1);
	}
	public static void qsort(int[] array, int low, int high){
	    if (low < high){
	        int pivot=partition(array, low, high);        //将数组分为两部分
	        qsort(array, low, pivot-1);                   //递归排序左子数组
	        qsort(array, pivot+1, high);                  //递归排序右子数组
	    }
	}
	private static int partition(int[] array, int low, int high){
	    int pivot = array[low];     //枢轴记录
	    while (low<high){
	        while (low<high && array[high]>=pivot) --high;
	        array[low]=array[high];             //交换比枢轴小的记录到左端
	        while (low<high && array[low]<=pivot) ++low;
	        array[high] = array[low];           //交换比枢轴小的记录到右端
	    }
	    //扫描完成，枢轴到位
	    array[low] = pivot;
	    //返回的是枢轴的位置
	    return low;
	}
}
