package algorithm;

public class Qsort {
	public static void quickSort(int[] array){
	    qsort(array, 0, array.length-1);
	}
	public static void qsort(int[] array, int low, int high){
	    if (low < high){
	        int pivot=partition(array, low, high);        //�������Ϊ������
	        qsort(array, low, pivot-1);                   //�ݹ�������������
	        qsort(array, pivot+1, high);                  //�ݹ�������������
	    }
	}
	private static int partition(int[] array, int low, int high){
	    int pivot = array[low];     //�����¼
	    while (low<high){
	        while (low<high && array[high]>=pivot) --high;
	        array[low]=array[high];             //����������С�ļ�¼�����
	        while (low<high && array[low]<=pivot) ++low;
	        array[high] = array[low];           //����������С�ļ�¼���Ҷ�
	    }
	    //ɨ����ɣ����ᵽλ
	    array[low] = pivot;
	    //���ص��������λ��
	    return low;
	}
}
