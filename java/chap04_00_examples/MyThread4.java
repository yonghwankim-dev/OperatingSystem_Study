package java.chap04_00_examples;

public class MyThread4{
	
	public static void main(String[] args)
	{
		Runnable task = ()->{
			for(int i=0;i <5; i++)
			{
				System.out.println("Hello, Lambda Runnable!");
			}
		};
		
		Thread thread = new Thread(task);
		thread.start();
		try
		{
			thread.join();	// �벐�젅�뱶媛� 醫낅즺 �맆 �븣媛�吏� wait
		}
		catch(InterruptedException ie)
		{
			System.out.println("Parent thread is interruped");
		}
		System.out.println("Hello, My Joined Child!");
	}
	
}
