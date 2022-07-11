package chap06_00_examples.chap06_00_04_perterson_atomic;

public class Consumer implements Runnable{
	
	@Override
	public void run() {
		
		for(int k = 0; k < 10000; k++)
		{
			// entry section
			Driver.flag[1].set(true);
			Driver.turn = 0;
			
			while(Driver.flag[0].get() && Driver.turn == 0)
			{
				
			}
			
			// critical section
			Driver.sum--;
			
			// exit section
			Driver.flag[1].set(false);
			
			// remainder section
		}
		
	}

}
