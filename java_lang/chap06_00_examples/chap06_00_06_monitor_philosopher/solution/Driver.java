package java_lang.chap06_00_examples.chap06_00_06_monitor_philosopher.solution;

/**
 * 식사하는 철학자 교착상태 문제 해결안
 * 교착상태 4가지중 환형대기를 해결하여 교착상태 해결
 * 철학자 스레드의 id가 정수이므로 id가 짝수인
 * 철학자는 왼쪽 젓가락부터 집고, id가 홀수인
 * 철학자는 오른쪽 젓가락부터 집게 하여 교착상태를
 * 해결합니다.
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
