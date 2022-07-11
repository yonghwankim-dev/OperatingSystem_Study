package chap06_00_examples.chap06_00_01_racecondition2;

public class Driver {
	public static void main(String[] args) throws InterruptedException
	{
		RunnableOne run1 = new RunnableOne();
		RunnableOne run2 = new RunnableOne();
		
		Thread t1 = new Thread(run1);
		Thread t2 = new Thread(run2);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		// Result : 154359
		System.out.println("Result : " + RunnableOne.count);
	}
}
