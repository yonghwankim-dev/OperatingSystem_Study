package chap04_01_prime;

public class PrintThread implements Runnable{
	private int n;
	private boolean[] primes;

	public PrintThread(int n, boolean[] primes) {
		this.n = n;
		this.primes = primes;
	}


	@Override
	public void run() {
		for(int i=1;i<=n;i++)
		{
			if(primes[i])
			{
				System.out.print(i + " ");
			}
		}		
	}

}
