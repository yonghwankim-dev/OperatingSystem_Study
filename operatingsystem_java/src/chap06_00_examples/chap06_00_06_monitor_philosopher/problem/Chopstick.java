package chap06_00_examples.chap06_00_06_monitor_philosopher.problem;

public class Chopstick {
	private boolean inUse;
	
	synchronized void acquire() throws InterruptedException
	{
		while(inUse)
		{
			wait();
		}
		inUse = true;
	}
	
	synchronized void release()
	{
		inUse = false;
		notify();
	}
}
