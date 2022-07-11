package chap04_00_examples;

public class MyThread5{
	
	public static void main(String[] args) throws InterruptedException
	{
		Runnable task = ()->{
			try
			{
				while(true)
				{
					System.out.println("Hello, Lambda Runnable!");
					Thread.sleep(100);
				}
			}
			catch(InterruptedException ie)
			{
				System.out.println("I'm Interruped");
			}
		};
		
		Thread thread = new Thread(task);
		thread.start();
		Thread.sleep(500);
		thread.interrupt();
		System.out.println("Hello, My Interruped Child!");
	}	
}
