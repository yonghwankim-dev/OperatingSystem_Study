package test;

// 쓰레드 구현 방법3 : Runnable 람다 포현식 사용하기
public class MyThread3{
	
	public static void main(String[] args)
	{
		Runnable task = ()->{
			try 
			{
				while(true) 
				{
					System.out.println("Hello, Lambda Runnable!");
					Thread.sleep(500);
				}
			}
			catch(InterruptedException ie)
			{
				System.out.println("I'm interrupted");
			}
		};
		
		Thread thread = new Thread(task);
		thread.start();
		System.out.println("Hello, My Lambda Child!");
	}
	
}
