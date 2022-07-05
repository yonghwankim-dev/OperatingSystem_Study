package java_lang.chap06_00_examples.chap06_00_06_monitor_philosopher.problem;

/**
 * 식사하는 철학자 문제
 * 철학자는 스레드, 젓가락은 공유자원
 * 이 코드는 교착 상태의 4가지 조건(상호배타,
 * 보유 및 대기, 비선점, 환형대기)을 만족하기
 * 때문에 교착상태가 발생합니다.
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
