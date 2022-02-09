package java_lang.chap04_05_matrix;

public class MatrixThread implements Runnable{
	private int i,j;
	static int[][] A;
	static int[][] B;
	static int[][] C;
	static int M,K,N;
	
	public MatrixThread(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public void run() {
		int sum =0;
	
		for(int col=0; col<K; col++)
		{
			sum += (A[i][col]*B[col][j]);
		}
		
		C[i][j] = sum;
	}
	
	
}
