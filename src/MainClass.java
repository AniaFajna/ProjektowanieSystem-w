import java.util.Scanner;

public class MainClass {

	private static long[][] A, L, Lt, M;
	private static long start, finish, timeElapsed;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		A = new long[input][input];
		L = new long[input][input];
		Lt = new long[input][input];
		M = new long[input][input];

		fillMatrix(input);
		start = System.nanoTime();
		choleskyAlgorithm(input);
		finish = System.nanoTime();
		timeElapsed = finish - start;

		setTriangleMatrix(input);
		transposeLMatrix(input);
		multiplyMatrix(input);

		showMatrix(input, A);
		showMatrix(input, L);
		showMatrix(input, Lt);
		showMatrix(input, M);
		System.out.println("czas = " + timeElapsed);
	}

	private static void fillMatrix(int N) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				A[j][i] = j+1;
				A[i][j] = A[j][i];
			}
		}
	}

	private static void showMatrix(int N, long[][] matrix) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	private static void choleskyAlgorithm(int N) {
		for (int i = 0; i < N; i++) {
			A[i][i] = (long) Math.sqrt(A[i][i]);
			for (int j = i; j < N; j++) {
				A[j][i] = A[j][i]/A[i][i];
			}
			for (int j = i+1; j < N; j++) {
				for (int k = i+1; k < j; k++) {
					A[j][k] = A[j][k] - (A[j][i] * A[k][i]);
				}
			}
		}
	}

	private static void setTriangleMatrix(int N) {
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i >= j) {
					L[i][j] = A[i][j];
				}
			}
		}
	}

	private static void transposeLMatrix(int N) {
		for(int i = 0 ; i < N ; i++){
			for(int j = 0 ; j < N ; j++){
				Lt[i][j] = L[j][i];
			}
		}
	}

	private static void multiplyMatrix(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					M[i][j] += L[i][k] * Lt[k][j];
				}
			}
		}
	}
}
