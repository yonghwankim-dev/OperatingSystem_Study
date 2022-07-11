package chap06_00_examples.chap06_00_06_monitor_philosopher.problem;

public class Philosopher implements Runnable{
	int id;	// ö������ ID
	Chopstick left_stick, right_stick;	// ���� ������, ������ ������
	
	public Philosopher(int id, Chopstick left_stick, Chopstick right_stick) {
		this.id = id;
		this.left_stick = left_stick;
		this.right_stick = right_stick;
	}

	@Override
	public void run() {
		try {
			while(true)
			{
				left_stick.acquire();
				right_stick.acquire();
				eating();
				left_stick.release();
				right_stick.release();
				thinking();
			}
		}catch(InterruptedException e) {
			System.out.println(e);
		}		
	}
	
	private void eating() {
		System.out.printf("[%d] eating\n",id);
	}
	
	private void thinking() {
		System.out.printf("[%d] thinking\n", id);
	}
	
}
