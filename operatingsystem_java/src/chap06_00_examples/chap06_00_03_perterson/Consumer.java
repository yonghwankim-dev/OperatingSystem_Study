package chap06_00_examples.chap06_00_03_perterson;

public class Consumer implements Runnable{
	
	@Override
	public void run() {
		
		for(int k = 0; k < 10000; k++)
		{
			// entry section
			Driver.flag[1] = true;
			Driver.turn = 0;
			
			while(Driver.flag[0]==true && Driver.turn == 0)
			{
				
			}
			
			// critical section
			Driver.sum--;
			
			// exit section
			Driver.flag[1] = false;
			
			// remainder section
		}
		
	}

}
