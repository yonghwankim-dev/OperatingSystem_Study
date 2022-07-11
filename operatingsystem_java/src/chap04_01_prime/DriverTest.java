package chap04_01_prime;

import java.io.IOException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DriverTest {

	// ���� ������ ����
	// n=10000000 (1��)
	// time : 11s
	@Test
	@Disabled
	void singleThreadTest() throws NumberFormatException, IOException {
		int n=0;
		boolean[] primes;
	
		n = 10000000; 
		primes = new boolean[n+1];
		
		for(int i=1;i<=n;i++)
		{
			if(Driver.isPrime(i))
			{
				primes[i] = true;
			}
		}
		Driver.printPrimes(primes, n);		
	}
	
	// ������ 2�� ����
	// input : n=10000000 (1��)
	// output : 8.5s
	@Test
	@Disabled
	void doubleThreadTest() throws NumberFormatException, IOException {
		int n=0;
		boolean[] primes;
		Thread thread1, thread2;
		
		n = 10000000; 
		primes = new boolean[n+1];
		thread1 = new Thread(new MyThread("thread1",n, primes, 1, n/2));
		thread2 = new Thread(new MyThread("thread2",n, primes,(n/2)+1,n));
		
		try {
			thread1.start();
			thread2.start();
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		Driver.printPrimes(primes, n);		
	}
	
	// ������ 3�� ����
	// input : n=10000000 (1��)
	// output : 7.445s 
	@Test
	void thirdThreadTest() throws NumberFormatException, IOException {
		int n=0;
		boolean[] primes;
		Thread thread1, thread2,thread3;
		
		n = 10000000; 
		primes = new boolean[n+1];
		thread1 = new Thread(new MyThread("thread1",n, primes, 1, n/3));
		thread2 = new Thread(new MyThread("thread2",n, primes,(n/3)+1,(n/3+1)*2));
		thread3 = new Thread(new MyThread("thread2",n, primes,(n/3+1)*2,n));
		try {
			thread1.start();
			thread2.start();
			thread3.start();
			thread1.join();
			thread2.join();
			thread3.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
		Driver.printPrimes(primes, n);		
	}

}
