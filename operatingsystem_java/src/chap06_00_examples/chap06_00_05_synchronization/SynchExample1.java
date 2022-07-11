package chap06_00_examples.chap06_00_05_synchronization;

public class SynchExample1 {
	static class Counter{
		public static int count = 0;
		public static void increment() {
			count++;
		}
	}
	
	static class MyRunnable implements Runnable{

		@Override
		public void run() {
			for(int i=0; i<10000; i++)
			{
				Counter.increment();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[5];
		for(int i=0; i<threads.length; i++) 
		{
			threads[i] = new Thread(new MyRunnable());
			threads[i].start();
		}
		for(int i=0; i<threads.length; i++)
		{
			 threads[i].join();
		}
		System.out.println("counter = " + Counter.count);	// not 50000
	}

}
