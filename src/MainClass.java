import java.util.*;

public class MainClass {

	private static long[][] A, L, Lt, M;
	private static int nr, XLength, ZLength, WLength;
	private static int[] W1, W2, W3, Z1, Z2, Z3, X1, X2, X3, V1, V2, operat;
	private static int[][] Ic1, Ic2, Ic3, Ib1, Ib2, Ib3, Ia1, II, JI, JK, KI, I3, I33, I4, I34;
	private static String[] options;
	private static int[][] I24;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		A = new long[input][input];
		L = new long[input][input];
		Lt = new long[input][input];
		M = new long[input][input];
		X1 = new int[100];
		X2 = new int[100];
		X3 = new int[100];
		W1 = new int[100];
		W2 = new int[100];
		W3 = new int[100];
		Z1 = new int[100];
		Z2 = new int[100];
		Z3 = new int[100];
		V1 = new int[200];
		Ic1 = new int[100][4];
		Ic2 = new int[100][4];
		Ic3 = new int[100][4];
		Ib1 = new int[100][4];
		Ib2 = new int[100][4];
		Ib3 = new int[100][4];
		Ia1 = new int[100][4];
		I3 = new int[100][30];
		I4 = new int[100][30];
		I24 = new int[100][30];
		I34 = new int[100][30];
		options = new String[3];
		options[0] = "sqrt";
		options[1] = "div";
		options[2] = "sub(multiply)";

		fillMatrix(input);
		//showMatrix(input, A);

		//long start = System.nanoTime();
		choleskyAlgorithm(input);
		//long finish = System.nanoTime();
		//long timeElapsed = finish - start;

		setTriangleMatrix(input);
		transposeLMatrix(input);
		multiplyMatrix(input);

		//showMatrix(input, L);
		//showMatrix(input, Lt);
		//showMatrix(input, M);
		//System.out.println("czas = " + timeElapsed);

		choleskyGrafPart1(input);
		choleskyGrafPart2(input);
		choleskyGrafPart3(input);
		showCholeskyGrafPart1();
		showCholeskyGrafPart2();
		showCholeskyGrafPart3();
		choleskyGrafMixTogether();
		showMix();
		showWezlyI3();
		showWezlyI4();
		showWezlyI34();
		showWezlyI24();
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

	private static void choleskyGrafPart1(int N) {
		nr = 0;
		XLength = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j <= i; j++) {
				for (int k = i; k <= i; k++) {
					nr += 1;
					X1[nr] = i;
					X2[nr] = j;
					X3[nr] = k;
					Ia1[nr][0] = i;
					Ia1[nr][1] = i;
					Ia1[nr][2] = i * 100 + j * 10 + k;
					Ia1[nr][3] = 0;
					XLength++;
					//A[i][i] = (long) Math.sqrt(A[i][i]);
				}
			}
		}
	}

	private static void choleskyGrafPart2(int N) {
		nr = 0;
		ZLength = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				for (int k = i; k <= i; k++) {
					nr += 1;
					Z1[nr] = i;
					Z2[nr] = j;
					Z3[nr] = k;
					Ib1[nr][0] = j;
					Ib1[nr][1] = i;
					Ib1[nr][2] = i * 100 + j * 10 + k;
					Ib1[nr][3] = 1;
					Ib2[nr][0] = i;
					Ib2[nr][1] = i;
					Ib2[nr][2] = i * 100 + j * 10 + k;
					Ib2[nr][3] = 1;
					ZLength++;
					//A[j][i] = A[j][i]/A[i][i];
				}
			}
		}
	}

	private static void choleskyGrafPart3(int N) {
		nr = 0;
		WLength = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				for (int k = i+1; k <= j; k++) {
					nr += 1;
					W1[nr] = i;
					W2[nr] = j;
					W3[nr] = k;
					Ic1[nr][0] = j;
					Ic1[nr][1] = k;
					Ic1[nr][2] = i * 100 + j * 10 + k;
					Ic1[nr][3] = 2;
					Ic2[nr][0] = j;
					Ic2[nr][1] = i;
					Ic2[nr][2] = i * 100 + j * 10 + k;
					Ic2[nr][3] = 2;
					Ic3[nr][0] = k;
					Ic3[nr][1] = i;
					Ic3[nr][2] = i * 100 + j * 10 + k;
					Ic3[nr][3] = 2;
					WLength++;
					//A[j][k] = A[j][k] - (A[j][i] * A[k][i]);
				}
			}
		}
	}

	private static void choleskyGrafMixTogether() {
		nr = 0;
		for (int i = 1; i <= XLength; i++) {
			nr++;
			V1[nr] = X1[i] * 100 + X2[i] * 10 + X3[i];
		}
		for (int i = 1; i <= ZLength; i++) {
			nr++;
			V1[nr] = Z1[i] * 100 + Z2[i] * 10 + Z3[i];
		}
		for (int i = 1; i <= WLength; i++) {
			nr++;
			V1[nr] = W1[i] * 100 + W2[i] * 10 + W3[i];
		}
		V2 = new int[nr+1];
		V2 = Arrays.copyOfRange(V1, 0, nr+1);
		Arrays.sort(V2);

		II = new int[nr+1][2];
		JI = new int[nr+1][2];
		JK = new int[nr+1][2];
		KI = new int[nr+1][2];
		operat = new int[nr+1];
		for (int i = 0; i <= nr; i++) {
			for (int j = 1; j <= XLength; j++) {
				if (V2[i] == Ia1[j][2]) {
					II[i][0] = Ia1[j][0];
					II[i][1] = Ia1[j][1];
					operat[i] = Ia1[j][3];
					break;
				} else {
					II[i][0] = 1000;
					II[i][1] = 1000;
				}
			}
			for (int j = 1; j <= ZLength; j++) {
				if (V2[i] == Ib1[j][2]) {
					JI[i][0] = Ib1[j][0];
					JI[i][1] = Ib1[j][1];
					operat[i] = Ib1[j][3];
					break;
				} else {
					JI[i][0] = 1000;
					JI[i][1] = 1000;
				}
			}
			for (int j = 1; j <= WLength; j++) {
				if (V2[i] == Ic1[j][2]) {
					JK[i][0] = Ic1[j][0];
					JK[i][1] = Ic1[j][1];
					operat[i] = Ic1[j][3];
					break;
				} else {
					JK[i][0] = 1000;
					JK[i][1] = 1000;
				}
			}
			for (int j = 1; j <= WLength; j++) {
				if (V2[i] == Ic3[j][2]) {
					KI[i][0] = Ic3[j][0];
					KI[i][1] = Ic3[j][1];
					operat[i] = Ic3[j][3];
					break;
				} else {
					KI[i][0] = 1000;
					KI[i][1] = 1000;
				}
			}
		}

		int[][] usedI3 = new int[60][2];
		int indexI3 = 1;
		int argsIndex = 2;
		for (int i = 0; i <= nr; i++) {
			if (JK[i][0] == 1000 && JK[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI3, Ic1[i][0], Ic1[i][1])) {
					for (int j = i-1; j <= nr; j++) {
						if (Ic1[i][0] == Ic1[j][0] && Ic1[i][1] == Ic1[j][1]) {
							I3[indexI3][argsIndex] = Ic1[j][2];
							argsIndex++;
							I3[indexI3][0] = Ic1[j][0];
							I3[indexI3][1] = Ic1[j][1];
						}
					}
					usedI3[indexI3][0] = Ic1[i][0];
					usedI3[indexI3][1] = Ic1[i][1];
					argsIndex = 2;
					indexI3++;
				} else {
					continue;
				}
			}
		}
		int[][] usedI4 = new int[60][2];
		int indexI4 = 1;
		argsIndex = 2;
		for (int i = 0; i <= nr; i++) {
			if (KI[i][0] == 1000 && KI[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI4, Ic3[i][0], Ic3[i][1])) {
					for (int j = i-1; j <= nr; j++) {
						if (Ic3[i][0] == Ic3[j][0] && Ic3[i][1] == Ic3[j][1]) {
							I4[indexI4][argsIndex] = Ic3[j][2];
							argsIndex++;
							I4[indexI4][0] = Ic3[j][0];
							I4[indexI4][1] = Ic3[j][1];
						}
					}
					usedI4[indexI4][0] = Ic3[i][0];
					usedI4[indexI4][1] = Ic3[i][1];
					argsIndex = 2;
					indexI4++;
				} else {
					continue;
				}
			}
		}
		int[][] usedI34 = new int[60][2];
		int indexI34 = 1;
		argsIndex = 2;
		for (int i = 0; i <= nr; i++) {
			if (JK[i][0] == 1000 && JK[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI34, Ic1[i][0], Ic1[i][1])) {
					for (int j = i; j <= nr; j++) {
						if (Ic1[i-1][0] == Ic3[j][0] && Ic1[i-1][1] == Ic3[j][1]) {
							I34[indexI34][argsIndex] = Ic3[j][2];
							argsIndex++;
							I34[indexI34][0] = Ic3[j][0];
							I34[indexI34][1] = Ic3[j][1];
						}
					}
					usedI34[indexI34][0] = Ic1[i][0];
					usedI34[indexI34][1] = Ic1[i][1];
					argsIndex = 2;
					indexI34++;
				} else {
					continue;
				}
			}
		}
		int[][] usedI24 = new int[60][2];
		int indexI24 = 1;
		argsIndex = 2;
		for (int i = 0; i <= nr; i++) {
			if (JI[i][0] == 1000 && JI[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI24, Ib1[i][0], Ib1[i][1])) {
					for (int j = i; j <= nr; j++) {
						if (Ib1[i-1][0] == Ic3[j][0] && Ib1[i-1][1] == Ic3[j][1]) {
							I24[indexI24][argsIndex] = Ic3[j][2];
							argsIndex++;
							I24[indexI24][0] = Ic3[j][0];
							I24[indexI24][1] = Ic3[j][1];
						}
					}
					usedI24[indexI24][0] = Ib1[i][0];
					usedI24[indexI24][1] = Ib1[i][1];
					argsIndex = 2;
					indexI24++;
				} else {
					continue;
				}
			}
		}
	}

	private static boolean matrixSearch(int[][] matrix, int srchNum1, int srchNum2) {
		for (int k = 0; k < matrix.length; k++) {
			if (srchNum1 == matrix[k][0] && srchNum2 == matrix[k][1]) {
				return false;
			}
		}
		return true;
	}

	private static void showMix() {
		System.out.println();
		System.out.println("nr	V		 I1		I2		I3		I4");
		for (int i = 1; i < V2.length; i++) {
			System.out.print(i + "\t" + V2[i] + "\t\t");
			if (II[i][0] == 1000 || II[i][1] == 1000) {
				System.out.print("x x\t\t");
			} else {
				System.out.format("%d %d		", II[i][0], II[i][1]);
			}
			if (JI[i][0] == 1000 || JI[i][1] == 1000) {
				System.out.print("x x\t\t");
			} else {
				System.out.format("%d %d		", JI[i][0], JI[i][1]);
			}
			if (JK[i][0] == 1000 || JK[i][1] == 1000) {
				System.out.print("x x\t\t");
			} else {
				System.out.format("%d %d\t\t", JK[i][0], JK[i][1]);
			}
			if (KI[i][0] == 1000 || KI[i][1] == 1000) {
				System.out.print("x x\t\t");
			} else {
				System.out.format("%d %d\t\t", KI[i][0], KI[i][1]);
			}
			System.out.format("%s\n", options[operat[i]]);
		}
	}

	private static void showWezlyI3() {
		for (int i = 0; i < 100; i++) {
			if (I3[i][0] == 0) {
				continue;
			} else {
				System.out.format("I3 : %d %d : %d %d %d\n",I3[i][0], I3[i][1], I3[i][2], I3[i][3], I3[i][4]);
			}
		}
		System.out.println();
	}

	private static void showWezlyI4() {
		for (int i = 0; i < 100; i++) {
			if (I4[i][0] == 0) {
				continue;
			} else {
				System.out.format("I4 : %d %d : %d %d %d\n",I4[i][0], I4[i][1], I4[i][2], I4[i][3], I4[i][4]);
			}
		}
		System.out.println();
	}

	private static void showWezlyI34() {
		for (int i = 0; i < 100; i++) {
			if (I34[i][0] == 0) {
				continue;
			} else {
				System.out.format("I34 : %d %d : %d %d %d\n", I34[i][0], I34[i][1], I34[i][2], I34[i][3], I34[i][4]);
			}
		}
		System.out.println();
	}

	private static void showWezlyI24() {
		for (int i = 0; i < 100; i++) {
			if (I24[i][0] == 0) {
				continue;
			} else {
				System.out.format("I24 : %d %d : %d %d %d\n", I24[i][0], I24[i][1], I24[i][2], I24[i][3], I24[i][4]);
			}
		}
		System.out.println();
	}

	private static void showCholeskyGrafPart1() {
		nr = 1;
		System.out.println("nr	X1	X2	X3		Ia1");
		while(X2[nr] != 0 || nr == 1) {
			System.out.format("%d	%d	%d	%d\t\t%d %d		sqrt\n",nr, X1[nr], X2[nr], X3[nr],
					Ia1[nr][0], Ia1[nr][1]);
			nr++;
		}
	}

	private static void showCholeskyGrafPart2() {
		nr = 1;
		System.out.println("nr	Z1	Z2	Z3		Ib1		Ib2");
		while(Z2[nr] != 0 || nr == 1) {
			System.out.format("%d	%d	%d	%d\t\t%d %d\t\t%d %d		div\n",nr, Z1[nr], Z2[nr], Z3[nr],
					Ib1[nr][0], Ib1[nr][1], Ib2[nr][0], Ib2[nr][1]);
			nr++;
		}
	}

	private static void showCholeskyGrafPart3() {
		nr = 1;
		System.out.println("nr	W1	W2	W3		Ic1		Ic2		Ic3");
		while(W3[nr] != 0) {
			System.out.format("%d	%d	%d	%d\t\t%d %d\t\t%d %d\t\t%d %d		sub(multiply)\n",nr, W1[nr], W2[nr], W3[nr],
					Ic1[nr][0], Ic1[nr][1], Ic2[nr][0], Ic2[nr][1], Ic3[nr][0], Ic3[nr][1]);
			nr++;
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
