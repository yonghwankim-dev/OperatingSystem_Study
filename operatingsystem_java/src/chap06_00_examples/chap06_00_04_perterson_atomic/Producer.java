package chap06_00_examples.chap06_00_04_perterson_atomic;

public class Producer implements Runnable{
	
	@Override
	public void run() {
		
		for(int k = 0; k < 10000; k++)
		{
			// entry section
			Driver.flag[0].set(true);
			Driver.turn = 1;
			
			while(Driver.flag[1].get() && Driver.turn == 1)
			{
				
			}
			
			// critical section
			Driver.sum++;
			
			// exit section
			Driver.flag[0].set(false);
			
			// remainder section
		}
		
	}

}
