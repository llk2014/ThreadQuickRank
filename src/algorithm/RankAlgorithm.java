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
				
				//-------------------------------------------------------------------��ȡ�����ļ�__begin
				File f=new File("./array.txt");
				BufferedReader br =new BufferedReader(new FileReader(f));
				int [] array= new int[10000000];
				int [] array2 = new int[10000000];
				int indexOfArray=0;
				String [] line = br.readLine().split(" ");
				for(String index:line) 
					array[indexOfArray++] = Integer.parseInt(index);	
				System.arraycopy(array, 0, array2, 0, array.length);
				//-------------------------------------------------------------------��ȡ�����ļ�__end
				
				//-------------------------------------------------------------------ִ�ж��߳̿����㷨 ����ʱ__begin
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
				System.out.println(end-start+"���Ƕ��߳̿���");
				//-------------------------------------------------------------------ִ�ж��߳̿����㷨 ����ʱ__end
				
				//-------------------------------------------------------------------ִ�е��߳̿����㷨 ����ʱ__begin				
				start = System.currentTimeMillis();
				Qsort.quickSort(array2);
				end = System.currentTimeMillis();	
				System.out.println(end-start+"���߳̿���");
				//-------------------------------------------------------------------ִ�е��߳̿����㷨 ����ʱ__end	
				
				//-------------------------------------------------------------------�ļ����__begin
				File f2 = new File("./ans.txt");
				BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
				for(int index:array) {					
					bw.write(index+" ");
				}
				br.close();
				bw.close();		
				//-------------------------------------------------------------------�ļ����__end
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public Integer compute() {
			return 0;
		}
}