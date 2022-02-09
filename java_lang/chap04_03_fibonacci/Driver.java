package java_lang.chap04_03_fibonacci;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {
	public static void main(String args[]) throws InterruptedException, ExecutionException
	{
		ExecutorService pool = Executors.newFixedThreadPool(3);
		int[] result = new int[20];
		
		
		for(int i=0;i<20;i++)
		{
			try {
				pool.submit(new MyThread(i, result)).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		pool.submit(new PrintThread(result)).get();
		
		pool.shutdown();
	}
}
