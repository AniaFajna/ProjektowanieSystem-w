import javax.swing.*;

public class MainAlgorythm {

	private static long[][] A, AA, L, Lt, M;
	private static long timeElapsed;

	MainAlgorythm(int input) {
		A = new long[input][input];
		AA = new long[input][input];
		L = new long[input][input];
		Lt = new long[input][input];
		M = new long[input][input];

		fillMatrix(input);
		//showMatrix(input, A);

		long start = System.nanoTime();
		choleskyAlgorithm(input, AA);
		long finish = System.nanoTime();
		timeElapsed = finish - start;

		setTriangleMatrix(input);
		transposeLMatrix(input);
		multiplyMatrix(input);
	}

	private static void fillMatrix(int N) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				A[j][i] = j+1;
				A[i][j] = A[j][i];
				AA[j][i] = j+1;
				AA[i][j] = AA[j][i];
			}
		}
	}

	public void showMatrix(int N, long[][] matrix, JTextArea area) {
		area.append("\n");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				area.append(" " + matrix[i][j] + " ");
			}
			area.append("\n");
		}
		area.append("\n");
	}

	private static void setTriangleMatrix(int N) {
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i >= j) {
					L[i][j] = AA[i][j];
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

	private static void choleskyAlgorithm(int N, long[][] A) {
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
	public long[][] getA() { return A; }

	public long[][] getL() { return L; }

	public long[][] getLt() { return Lt; }

	public long[][] getM() { return M; }

	public long getTimeElapsed() { return timeElapsed; }
}
