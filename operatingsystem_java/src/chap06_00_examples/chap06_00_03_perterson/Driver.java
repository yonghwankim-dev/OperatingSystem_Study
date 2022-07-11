package chap06_00_examples.chap06_00_03_perterson;

public class Driver {
	static int sum = 0;
	static int turn = 0;
	static boolean[] flag = new boolean[2];
	
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
