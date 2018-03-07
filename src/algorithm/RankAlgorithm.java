package algorithm;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
//import java.util.concurrent.Future;

public class RankAlgorithm extends RecursiveTask<Integer> {
		public static void main(String args[]){				
			try{
				//ExecutorService pool = Executors.newCachedThreadPool();
				ForkJoinPool fkjPool = new ForkJoinPool(4);
				long start;
				long end;
				
				//-------------------------------------------------------------------读取数组文件__begin
				File f=new File("./array.txt");
				BufferedReader br =new BufferedReader(new FileReader(f));
				int [] array= new int[10000000];
				int [] array2 = new int[10000000];
				int indexOfArray=0;
				String [] line = br.readLine().split(" ");
				for(String index:line) 
					array[indexOfArray++] = Integer.parseInt(index);	
				System.arraycopy(array, 0, array2, 0, array.length);
				//-------------------------------------------------------------------读取数组文件__end
				
				//-------------------------------------------------------------------执行多线程快排算法 并计时__begin
				start = System.currentTimeMillis();
				//QsortPlus qs = new QsortPlus();
				//qs.qsort(array, 0, array.length-1);
				//Qsort.quickSort(array);
				//RankAlgorithmThread2 rat2 = new RankAlgorithmThread2(array,0,array.length-1,pool);				
				RankAlgorithmThread2 rat2 = new RankAlgorithmThread2(array,0,array.length-1,fkjPool);
				rat2.fork();
				rat2.join();
				//Thread.currentThread().sleep(2000);
				end = System.currentTimeMillis();	
				System.out.println(end-start+"这是多线程快排");
				//-------------------------------------------------------------------执行多线程快排算法 并计时__end
				
				//-------------------------------------------------------------------执行单线程快排算法 并计时__begin				
				start = System.currentTimeMillis();
				Qsort.quickSort(array2);
				end = System.currentTimeMillis();	
				System.out.println(end-start+"单线程快排");
				//-------------------------------------------------------------------执行单线程快排算法 并计时__end	
				
				//-------------------------------------------------------------------文件输出__begin
				File f2 = new File("./ans.txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
				for(int index:array) {					
					bw.write(index+" ");
				}
				br.close();
				bw.close();		
				//-------------------------------------------------------------------文件输出__end
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public Integer compute() {
			return 0;
		}
}