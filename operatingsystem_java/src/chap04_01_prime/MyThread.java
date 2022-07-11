package chap04_01_prime;

public class MyThread implements Runnable{
	private String name;
	private int n;	// �Է°�
	private boolean[] primes;	// �Ҽ� Ȯ�� �迭
	private int start;
	private int end;
	
	public MyThread(String name, int n, boolean[] primes, int start, int end) {
		this.name = name;
		this.n = n;
		this.primes = primes;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		for(int i=start; i<=end;i++)
		{
			if(isPrime(i))
			{
				primes[i] = true;
			}
		}
	}
	
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
	
	
	
}
