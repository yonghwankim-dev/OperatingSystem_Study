package chap04_01_prime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @title �Ҽ��� ����ϴ� ���� ������
 * @author qkdlf
 * 
 * input
 * n
 * 
 * output
 * n���� �۰ų� ���� ��� �Ҽ��� ���
 */
public class Driver {

	public static boolean isPrime(int n)
	{
		if(n==1)
		{
			return false;
		}
		
		for(int i=2;i<=Math.sqrt(n);i++)
		{
			if(n%i==0)
			{
				return false;
			}
		}
		return true;
	}
	
	public static void printPrimes(boolean[] primes, int n)
	{
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=n;i++)
		{
			if(primes[i])
			{
				sb.append(i+" ");
			}
			if(i%10==0)
			{
				sb.append("\n");
			}
		}
		System.out.println(sb.toString().trim());
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0;
		boolean[] primes;
		Thread thread1, thread2, thread3;
	
		n = Integer.parseInt(br.readLine());
		primes = new boolean[n+1];
		thread1 = new Thread(new MyThread("thread1",n, primes, 1, n/2));
		thread2 = new Thread(new MyThread("thread2",n, primes,(n/2)+1,n));
		thread3 = new Thread(new PrintThread(n, primes));
			
		try {
			thread1.start();
			thread2.start();
			
			thread1.join();
			thread2.join();
			
			thread3.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
