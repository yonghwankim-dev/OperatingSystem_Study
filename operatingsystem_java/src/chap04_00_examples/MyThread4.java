package chap04_00_examples;

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
			thread.join();	// 占쎈쾺占쎌쟿占쎈굡揶쏉옙 �넫�굝利� 占쎈쭍 占쎈르揶쏉옙筌욑옙 wait
		}
		catch(InterruptedException ie)
		{
			System.out.println("Parent thread is interruped");
		}
		System.out.println("Hello, My Joined Child!");
	}
	
}
