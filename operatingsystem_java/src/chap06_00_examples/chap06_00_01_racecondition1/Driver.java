package chap06_00_examples.chap06_00_01_racecondition1;

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
		// Result : 100000, 100000
		System.out.println("Result : " + run1.count + ", " + run2.count);
	}
}
