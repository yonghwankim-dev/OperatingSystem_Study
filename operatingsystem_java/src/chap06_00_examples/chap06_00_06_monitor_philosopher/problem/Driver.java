package chap06_00_examples.chap06_00_06_monitor_philosopher.problem;

/**
 * �Ļ��ϴ� ö���� ����
 * ö���ڴ� ������, �������� �����ڿ�
 * �� �ڵ�� ���� ������ 4���� ����(��ȣ��Ÿ,
 * ���� �� ���, ����, ȯ�����)�� �����ϱ�
 * ������ �������°� �߻��մϴ�.
 */
public class Driver {

	public static void main(String[] args) {
		int num = 5;
		
		Chopstick[] chopsticks = new Chopstick[num];
		Philosopher[] philosophers = new Philosopher[num];
		Thread[] threads = new Thread[num];
		
		for(int i=0; i<num; i++)
		{
			chopsticks[i] = new Chopstick();
		}
		for(int i=0; i<num; i++)
		{
			philosophers[i] = new Philosopher(i
											, chopsticks[i]
											, chopsticks[(i+1) % num]);
			threads[i] = new Thread(philosophers[i]);	
		}
		
		for(int i=0; i<num; i++)
		{
			threads[i].start();
		}
	}

}
