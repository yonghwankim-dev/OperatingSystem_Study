package java_lang.chap04_03_fibonacci;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {
	public static void main(String args[])
	{
		ExecutorService pool = Executors.newFixedThreadPool(3);
		int[] result = new int[20];
		
		
		for(int i=0;i<=50;i++)
		{
			pool.submit(new MyThread(i, result));
		}
		pool.submit(new PrintThread(result));
		
		pool.shutdown();
	}
}
