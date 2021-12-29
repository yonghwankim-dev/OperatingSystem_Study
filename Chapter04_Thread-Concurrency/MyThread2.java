package test;

// 쓰레드 구현 방법2 : Runnable 인터페이스 구현
public class MyThread2 implements Runnable{

	@Override
	public void run() {
		try 
		{
			while(true)
			{
				System.out.println("Hello, Runnable!");
				Thread.sleep(500);
			}
		}
		catch(InterruptedException ie) {
			System.out.println("I'm interruptedException");
		}
	}
	
	public static void main(String[] args)
	{
		Thread thread = new Thread(new MyThread2());
		thread.start();
		System.out.println("Hello, My Runnable Child");
	}
	
}
