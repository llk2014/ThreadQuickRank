package algorithm;

import java.util.concurrent.Callable;

public class RankAlgorithmThread1 implements Callable<Integer>  {
		
	private int[] array;
	private int low;
	private int high;
	
	public RankAlgorithmThread1(int[] array,int low,int high) {
		this.array=array;
		this.high=high;
		this.low=low;
	}
	
	public Integer call() {		
		return partition();
	}
	
	private int partition() {
		int pivot=array[low];
		while(low<high) {
			while(low<high && array[high]>=pivot)
				--high;
			array[low]=array[high];
			while(low<high && array[low]<=pivot)
				++low;
			array[high]=array[low];
		}
		array[low]=pivot;
		return low;
	}
	
	public void run() {
		partition();
	}
}
