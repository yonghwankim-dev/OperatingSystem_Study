package java_lang.chap04_03_fibonacci;

public class PrintThread implements Runnable{

	private int[] result;

	public PrintThread(int[] result) {
		this.result = result;
	}



	@Override
	public void run() {
		for(int i=0;i<result.length;i++)
		{
			System.out.print(result[i] + " ");
		}
		System.out.println();
	}

}
