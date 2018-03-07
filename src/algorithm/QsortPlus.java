package algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class QsortPlus{
	private int[] array;
	private int low;
	private int high;
	private int pivot;
	private ExecutorService pool = Executors.newFixedThreadPool(5);
		
	public void qsort(int[] array,int low,int high) {
		if(low<high) {
			Future<Integer> future = pool.submit(new RankAlgorithmThread1(array, low, high));			
			try{
				pivot = future.get();
			}			
			catch(Exception e) {
				e.printStackTrace();
			}
			qsort(array,low,pivot-1);
			qsort(array,pivot+1,high);
		}
	}	
}
