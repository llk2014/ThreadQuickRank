package algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class RankAlgorithmThread2 extends RecursiveTask{
	private int[] array;
	private int low;
	private int high;
	//private ExecutorService pool;
	private ForkJoinPool fkjPool;					
	private final int MAX_BLOCK = 5000;	//区块大小设置
	
	/*public RankAlgorithmThread2(int[] array,int low,int high,ExecutorService pool) {
		this.array=array;
		this.high=high;
		this.low=low;
		this.pool=pool;
	}*/
	
	public RankAlgorithmThread2(int[] array,int low,int high,ForkJoinPool fkjPool) {
		this.array=array;
		this.high=high;
		this.low=low;
		this.fkjPool=fkjPool;
	}
	
	private int partition() {
		int tempLow = low;
		int tempHigh = high;
		int pivot = array[tempLow];
		while(tempLow<tempHigh) {
			while(tempLow<tempHigh && array[tempHigh]>=pivot)
				--tempHigh;
			array[tempLow]=array[tempHigh];
			while(tempLow<tempHigh && array[tempLow]<=pivot)
				++tempLow;
			array[tempHigh]=array[tempLow];
		}
		array[tempLow]=pivot;
		return tempLow;
	}
	
	public void qsort() {
		if(low<high) {			
			int pivot=partition();				
			/*Thread rat2_1 = new RankAlgorithmThread2(array,low,pivot-1,pool);
			pool.submit(rat2_1);
			Thread rat2_2 = new RankAlgorithmThread2(array,pivot+1,high,pool);
			pool.submit(rat2_2);
			try {
				rat2_1.join();
				rat2_2.join();
			}			
			catch(Exception e) {
				e.printStackTrace();
			}*/
			RankAlgorithmThread2 rat2_1 = null;
			RankAlgorithmThread2 rat2_2 = null;
			if(pivot-1-low>MAX_BLOCK) {										//依据区块大小来决定是否使用多线程运行，当数据量小于某个大小时，多线程运行没有发挥优势。
				rat2_1 = new RankAlgorithmThread2(array,low,pivot-1,fkjPool);
				//invokeAll(rat2_1);
				rat2_1.fork();
			}			
			if(high-pivot-1>MAX_BLOCK) {				
				rat2_2 = new RankAlgorithmThread2(array,pivot+1,high,fkjPool);
				//invokeAll(rat2_2);
				rat2_2.fork();
			}			
			if(rat2_1==null && rat2_2==null) {
				Qsort.qsort(array, low, pivot-1);
				Qsort.qsort(array, pivot+1, high);
			}else if(rat2_2==null){
				Qsort.qsort(array, pivot+1, high);
				rat2_1.join();
			}else if(rat2_1==null){
				Qsort.qsort(array, low, pivot-1);
				rat2_2.join();
			}else{			
				rat2_1.join();
				rat2_1.join();
			}			
		}
	}
	
	public void run() {
		qsort();
	}
	
	public Integer compute() {
		//System.out.print("*");
		qsort();
		return 0;
	}
}
