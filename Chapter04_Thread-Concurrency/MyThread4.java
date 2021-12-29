package test;

// 부모 쓰레드의 대기 : join
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
			thread.join();	// 쓰레드가 종료 될 때가지 wait
		}
		catch(InterruptedException ie)
		{
			System.out.println("Parent thread is interruped");
		}
		System.out.println("Hello, My Joined Child!");
	}
	
}
