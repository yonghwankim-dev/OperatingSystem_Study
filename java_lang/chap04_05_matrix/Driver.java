package java_lang.chap04_05_matrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {
	
	public static void printMatrix(int[][] C)
	{
		for(int row=0; row<C.length; row++)
		{
			for(int col=0; col<C[row].length; col++)
			{
				System.out.print(C[row][col] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int M=3, K=2, N=3;
		int[][] A = {{1,4},{2,5},{3,6}};	// M*K
		int[][] B = {{8,7,6},{5,4,3}};		// K*N
		int[][] C = new int[M][N];
		final int NUM_THREADS = 10;
		ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
		
		MatrixThread.A = A;
		MatrixThread.B = B;
		MatrixThread.C = C;
		MatrixThread.M = M;
		MatrixThread.K = K;
		MatrixThread.N = N;
		
		for(int i=0;i<M;i++)
		{
			for(int j=0;j<N;j++)
			{
				try {
					pool.submit(new MatrixThread(i,j)).get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}		
			}
		}
		pool.shutdown();
		printMatrix(C);
		
	}

}
