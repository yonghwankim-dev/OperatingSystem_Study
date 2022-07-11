package chap06_00_examples.chap06_00_01_racecondition1;

public class RunnableOne implements Runnable{
	int count=0;

	@Override
	public void run() {
		for(int i=0;i<100000;i++)
		{
			count++;
		}
	}

	
}
