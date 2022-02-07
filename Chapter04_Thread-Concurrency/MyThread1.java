package test;

// 쓰레드 구현 방법1 : Thread 클래스 상속
public class MyThread1 extends Thread{

	@Override
	public void run() {
		try 
		{
			while(true)
			{
				System.out.println("Hello, Thread!");
				Thread.sleep(500);
			}
		}
		catch(InterruptedException ie) {
			System.out.println("I'm interruptedException");
		}
	}
	
	public static void main(String[] args)
	{
		MyThread1 thread = new MyThread1();
		thread.start();
		System.out.println("Hello, my child");
	}
	
}
