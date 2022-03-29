package java_lang.chap06_00_examples.chap06_00_05_synchronization;

public class SynchExample4 {
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
		for(int i=0; i<threads.length; i++) 
		{
			threads[i] = new Thread(new MyRunnable(new Counter()));
			threads[i].start();
		}
		for(int i=0; i<threads.length; i++)
		{
			 threads[i].join();
		}
		// output : not 50000
		// error cause : 5개의 쓰레드 각각의 서로 다른 Counter 객체를 생성되었기 때문에 동기화가 되지 않았음
		System.out.println("counter = " + Counter.count);
	}

}
