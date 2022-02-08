package java_lang.chap04_03_fibonacci;

public class MyThread implements Runnable{

	private int n;
	private static int[] result;
	
	public MyThread(int n, int[] result) {
		this.n = n;
		this.result = result;
	}

	public static int fibo(int n)
	{
		if(n<=1)
		{
			return result[n] = n;
		}
		else
		{
			result[n-2] = result[n-2]==0 ? fibo(n-2) : result[n-2];
			result[n-1] = result[n-1]==0 ? fibo(n-1) : result[n-1];
			return result[n-2] + result[n-1];
		}
	}

	@Override
	public void run() {
		result[n] = fibo(n);
		System.out.printf("id:%s , result[%d] = %d\n",Thread.currentThread().getName(),
				n,result[n]);
	}

}
