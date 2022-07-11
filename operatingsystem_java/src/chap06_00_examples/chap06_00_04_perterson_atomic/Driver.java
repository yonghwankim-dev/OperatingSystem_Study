package chap06_00_examples.chap06_00_04_perterson_atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class Driver {
	static int sum = 0;
	
	static AtomicBoolean[] flag = new AtomicBoolean[2];
	static {
		for(int i=0;i<flag.length;i++)
		{
			flag[i] = new AtomicBoolean();
		}
	}
	static int turn = 0;
	
	public static void main(String[] args)
	{
		Thread producer = new Thread(new Producer());
		Thread consumer = new Thread(new Consumer());
		
		producer.start();
		consumer.start();
		
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("sum = " + sum);
	}
}
