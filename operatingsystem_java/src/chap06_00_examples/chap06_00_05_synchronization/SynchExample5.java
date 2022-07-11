package chap06_00_examples.chap06_00_05_synchronization;

public class SynchExample5 {
	static class Counter{
		
		public static int count = 0;
		public void increment() {
			synchronized (this) {
				count++;
			}
		}
	}
	
	static class MyRunnable implements Runnable{
		Counter counter;
		
		public MyRunnable(Counter counter) {
			this.counter = counter;
		}
		
		@Override
		public void run() {
			for(int i=0; i<10000; i++)
			{
				counter.increment();
			}
		}
		
	}
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[5];
		Counter counter = new Counter();
		for(int i=0; i<threads.length; i++) 
		{
			threads[i] = new Thread(new MyRunnable(counter));
			threads[i].start();
		}

		for(Thread t : threads) {
			t.join();
		}

		System.out.println("counter = " + Counter.count);	// 50000
	}

}
