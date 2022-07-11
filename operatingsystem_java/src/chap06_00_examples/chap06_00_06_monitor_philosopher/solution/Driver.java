package chap06_00_examples.chap06_00_06_monitor_philosopher.solution;

/**
 * �Ļ��ϴ� ö���� �������� ���� �ذ��
 * �������� 4������ ȯ����⸦ �ذ��Ͽ� �������� �ذ�
 * ö���� �������� id�� �����̹Ƿ� id�� ¦����
 * ö���ڴ� ���� ���������� ����, id�� Ȧ����
 * ö���ڴ� ������ ���������� ���� �Ͽ� �������¸�
 * �ذ��մϴ�.
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
