package chap06_00_examples.chap06_00_01_racecondition2;

public class RunnableOne implements Runnable{
	static int count=0;

	@Override
	public void run() {
		for(int i=0;i<100000;i++)
		{
			count++;
		}
	}

	
}
