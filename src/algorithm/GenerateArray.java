package algorithm;

import java.io.*;
import java.util.Random;

public class GenerateArray {			//�������ɲ������ļ�

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {	
			File f=new File("./array.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			int numOfNum = 10000000;
			while (numOfNum-->0) {
				bw.write(new Random().nextInt(10000000)+" ");
			}
			bw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
