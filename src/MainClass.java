import java.util.*;

public class MainClass {

	private static long[][] A, L, Lt, M;
	private static int nr, XLength, ZLength, WLength;
	private static int[] W1, W2, W3, Z1, Z2, Z3, X1, X2, X3, V1, V2, operat;
	private static int[][] Ic1;
	private static int[][] Ic2;
	private static int[][] Ic3;
	private static int[][] Ib1;
	private static int[][] Ib2;
	private static int[][] Ia1;
	private static int[][] II;
	private static int[][] JI;
	private static int[][] JK;
	private static int[][] KI;
	private static int[][][] I1;
	private static int[][][] I2;
	private static int[][][] I3;
	private static int[][][] I13;
	private static int[][][] I4;
	private static int[][][] I34;
	private static int[][][] I23;
	private static String[] options;
	private static int[][][] I24;

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
		Ia1 = new int[100][4];
		I1 = new int[100][30][2];
		I2 = new int[100][30][2];
		I3 = new int[100][30][2];
		I4 = new int[100][30][2];
		I13 = new int[100][30][2];
		I23 = new int[100][30][2];
		I24 = new int[100][30][2];
		I34 = new int[100][30][2];
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
		showWezlyI1();
		showWezlyI2();
		showWezlyI3();
		showWezlyI4();
		showWezlyI34();
		showWezlyI24();
		showWezlyI23();
		showWezlyI13();
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
			for (int j = i+1; j < N; j++) {
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
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 4; j++) {
				Ia1[i][j] = 999;
			}
		}
		nr = 0;
		XLength = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i; j <= i; j++) {
				for (int k = i; k <= i; k++) {
					X1[nr] = i;
					X2[nr] = j;
					X3[nr] = k;
					Ia1[nr][0] = i;
					Ia1[nr][1] = i;
					Ia1[nr][2] = i * 100 + j * 10 + k;
					Ia1[nr][3] = 0;
					nr++;
					XLength++;
					//A[i][i] = (long) Math.sqrt(A[i][i]);
				}
			}
		}
	}

	private static void choleskyGrafPart2(int N) {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 4; j++) {
				Ib2[i][j] = 999;
			}
		}
		nr = 0;
		ZLength = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				for (int k = i; k <= i; k++) {
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
					nr++;
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
					nr++;
					WLength++;
					//A[j][k] = A[j][k] - (A[j][i] * A[k][i]);
				}
			}
		}
	}

	private static void choleskyGrafMixTogether() {
		nr = 0;
		for (int i = 0; i < XLength; i++) {
			V1[nr] = X1[i] * 100 + X2[i] * 10 + X3[i];
			nr++;
		}
		for (int i = 0; i < ZLength; i++) {
			V1[nr] = Z1[i] * 100 + Z2[i] * 10 + Z3[i];
			nr++;
		}
		for (int i = 0; i < WLength; i++) {
			V1[nr] = W1[i] * 100 + W2[i] * 10 + W3[i];
			nr++;
		}
		V2 = new int[nr];
		V2 = Arrays.copyOfRange(V1, 0, nr);
		Arrays.sort(V2);

		II = new int[nr][2];
		JI = new int[nr][2];
		JK = new int[nr][2];
		KI = new int[nr][2];
		operat = new int[nr];
		for (int i = 0; i < nr; i++) {
			for (int j = 0; j < ZLength; j++) {
				if (V2[i] == Ia1[j][2]) {
					II[i][0] = Ia1[j][0];
					II[i][1] = Ia1[j][1];
					operat[i] = Ia1[j][3];
					break;
				} else if (V2[i] == Ib2[j][2]){
					II[i][0] = Ib2[j][0];
					II[i][1] = Ib2[j][1];
					operat[i] = Ib2[j][3];
					break;
				} else {
					II[i][0] = 1000;
					II[i][1] = 1000;
				}
			}
			for (int j = 0; j < WLength; j++) {
				if (V2[i] == Ib1[j][2]) {
					JI[i][0] = Ib1[j][0];
					JI[i][1] = Ib1[j][1];
					operat[i] = Ib1[j][3];
					break;
				} else if (V2[i] == Ic2[j][2]){
					JI[i][0] = Ic2[j][0];
					JI[i][1] = Ic2[j][1];
					operat[i] = Ic2[j][3];
					break;
				} else {
					JI[i][0] = 1000;
					JI[i][1] = 1000;
				}
			}
			for (int j = 0; j < WLength; j++) {
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
			for (int j = 0; j < WLength; j++) {
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
		createI1();
		createI2();
		createI3();
		createI4();
		createI24();
		createI34();
		createI23();
		createI13();
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
		for (int i = 0; i < V2.length; i++) {
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

	private static void createI1() {
		int[][] usedI1 = new int[60][2];
		for (int i = 0; i < 60; i++) {
			for (int j = 0; j < 2; j++) {
				usedI1[i][j] = 999;
			}
		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 30; j++) {
				I1[i][j][0] = 999;
				I1[i][j][1] = 999;
			}
		}
		int indexI1 = 0;
		int argsIndex = 0;
		for (int i = 0; i < nr; i++) {
			if (II[i][0] == 1000 && II[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI1, II[i][0], II[i][1])) {
					int P = 999;
					for (int j = 0; j < nr; j++) {
						if (II[i][0] == Ia1[j][0] && II[i][1] == Ia1[j][1]) {
							if (P == 999) {
								P = Ia1[j][2];
								I1[indexI1][0][0] = Ia1[j][0];
								I1[indexI1][0][1] = Ia1[j][1];
							} else {
								I1[indexI1][argsIndex][0] = P;
								I1[indexI1][argsIndex][1] = Ia1[j][2];
								P = Ia1[j][2];
							}
							argsIndex++;
						}
						if (II[i][0] == Ib2[j][0] && II[i][1] == Ib2[j][1]) {
							if (P == 999) {
								P = Ib2[j][2];
								I1[indexI1][0][0] = Ib2[j][0];
								I1[indexI1][0][1] = Ib2[j][1];
							} else {
								I1[indexI1][argsIndex][0] = P;
								I1[indexI1][argsIndex][1] = Ib2[j][2];
								P = Ib2[j][2];
							}
							argsIndex++;
						}
					}
				}
				usedI1[indexI1][0] = II[i][0];
				usedI1[indexI1][1] = II[i][1];
				argsIndex = 0;
				indexI1++;
			}
		}
	}

	private static void createI2() {
		int[][] usedI2 = new int[60][2];
		int indexI2 = 0;
		int argsIndex = 0;
		for (int i = 0; i < nr; i++) {
			if (JI[i][0] == 1000 && JI[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI2, JI[i][0], JI[i][1])) {
					int P = 0;
					for (int j = 0; j < nr; j++) {
						if (JI[i][0] == Ib1[j][0] && JI[i][1] == Ib1[j][1]) {
							if (P == 0) {
								P = Ib1[j][2];
								I2[indexI2][0][0] = Ib1[j][0];
								I2[indexI2][0][1] = Ib1[j][1];
							} else {
								I2[indexI2][argsIndex][0] = P;
								I2[indexI2][argsIndex][1] = Ib1[j][2];
								P = Ib1[j][2];
							}
							argsIndex++;
						}
						if (JI[i][0] == Ic2[j][0] && JI[i][1] == Ic2[j][1]) {
							if (P == 0) {
								P = Ic2[j][2];
								I2[indexI2][0][0] = Ic2[j][0];
								I2[indexI2][0][1] = Ic2[j][1];
							} else {
								I2[indexI2][argsIndex][0] = P;
								I2[indexI2][argsIndex][1] = Ic2[j][2];
								P = Ic2[j][2];
							}
							argsIndex++;
						}
					}
				}
				usedI2[indexI2][0] = JI[i][0];
				usedI2[indexI2][1] = JI[i][1];
				argsIndex = 0;
				indexI2++;
			}
		}
	}

	private static void createI3() {
		int[][] usedI3 = new int[60][2];
		int indexI3 = 0;
		int argsIndex = 0;
		for (int i = 0; i < nr; i++) {
			if (JK[i][0] == 1000 && JK[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI3, JK[i][0], JK[i][1])) {
					int P = 0;
					for (int j = 0; j < nr; j++) {
						if (JK[i][0] == Ic1[j][0] && JK[i][1] == Ic1[j][1]) {
							if (P == 0) {
								P = Ic1[j][2];
								I3[indexI3][0][0] = Ic1[j][0];
								I3[indexI3][0][1] = Ic1[j][1];
							} else {
								I3[indexI3][argsIndex][0] = P;
								I3[indexI3][argsIndex][1] = Ic1[j][2];
								P = Ic1[j][2];
							}
							argsIndex++;
						}
					}
					usedI3[indexI3][0] = JK[i][0];
					usedI3[indexI3][1] = JK[i][1];
					argsIndex = 0;
					indexI3++;
				}
			}
		}
	}

	private static void createI4() {
		int[][] usedI4 = new int[60][2];
		int indexI4 = 0;
		int argsIndex = 0;
		for (int i = 0; i < nr; i++) {
			if (KI[i][0] == 1000 && KI[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI4, KI[i][0], KI[i][1])) {
					int P = 0;
					for (int j = 0; j < nr; j++) {
						if (KI[i][0] == Ic3[j][0] && KI[i][1] == Ic3[j][1]) {
							if (P == 0) {
								P = Ic3[j][2];
								I4[indexI4][0][0] = Ic3[j][0];
								I4[indexI4][0][1] = Ic3[j][1];
							} else {
								I4[indexI4][argsIndex][0] = P;
								I4[indexI4][argsIndex][1] = Ic3[j][2];
								P = Ic3[j][2];
							}
							argsIndex++;
						}
					}
					usedI4[indexI4][0] = KI[i][0];
					usedI4[indexI4][1] = KI[i][1];
					argsIndex = 0;
					indexI4++;
				}
			}
		}
	}

	private static void createI24() {
		int[][] usedI24 = new int[60][2];
		int indexI24 = 0;
		int argsIndex = 0;
		for (int i = 0; i < nr; i++) {
			if (JI[i][0] == 1000 && JI[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI24, JI[i][0], JI[i][1])) {
					int P = 0;
					for (int j = 0; j < nr; j++) {
						if (JI[i][0] == Ic3[j][0] && JI[i][1] == Ic3[j][1]) {
							if (P == 0) {
								for (int k = 0; k < Ib1.length; k++) {
									if (Ic3[j][0] == Ib1[k][0] && Ic3[j][1] == Ib1[k][1]) {
										P = Ib1[k][2];
										break;
									}
								}
								I24[indexI24][0][0] = Ic3[j][0];
								I24[indexI24][0][1] = Ic3[j][1];
								argsIndex++;
								I24[indexI24][argsIndex][0] = P;
								I24[indexI24][argsIndex][1] = Ic3[j][2];
							} else {
								I24[indexI24][argsIndex][0] = P;
								I24[indexI24][argsIndex][1] = Ic3[j][2];
								P = Ic3[j][2];
							}
							argsIndex++;
						}
					}
					usedI24[indexI24][0] = JI[i][0];
					usedI24[indexI24][1] = JI[i][1];
					argsIndex = 0;
					indexI24++;
				}
			}
		}
	}

	private static void createI34() {
		int[][] usedI34 = new int[60][2];
		int indexI34 = 0;
		int argsIndex = 0;
		for (int i = 0; i < nr; i++) {
			if (JK[i][0] == 1000 && JK[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI34, JK[i][0], JK[i][1])) {
					int P = 0;
					for (int j = 0; j < nr; j++) {
						if (JK[i][0] == Ic3[j][0] && JK[i][1] == Ic3[j][1]) {
							if (P == 0) {
								for (int k = 0; k < Ib1.length; k++) {
									if (Ic3[j][0] == Ic1[k][0] && Ic3[j][1] == Ic1[k][1]) {
										P = Ic1[k][2];
										break;
									}
								}
								I34[indexI34][0][0] = Ic3[j][0];
								I34[indexI34][0][1] = Ic3[j][1];
								argsIndex++;
								I34[indexI34][argsIndex][0] = P;
								I34[indexI34][argsIndex][1] = Ic3[j][2];
							} else {
								I34[indexI34][argsIndex][0] = P;
								I34[indexI34][argsIndex][1] = Ic3[j][2];
								P = Ic3[j][2];
								//argsIndex++;
							}
							argsIndex++;
						}
					}
					usedI34[indexI34][0] = JK[i][0];
					usedI34[indexI34][1] = JK[i][1];
					argsIndex = 0;
					indexI34++;
				}
			}
		}
	}

	private static void createI23() {
		int[][] usedI23 = new int[60][2];
		int indexI23 = 1;
		int argsIndex = 2;
		int lastVal = 0;
		for (int i = 0; i < nr; i++) {
			if (JI[i][0] == 1000 && JI[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI23, JI[i][0], JI[i][1])) {
					int P = 0;
					for (int j = 0; j < nr; j++) {
						if (JI[i][0] == Ic1[j][0] && JI[i][1] == Ic1[j][1]) {
							if (P == 0) {
								for (int k = 0; k < Ib1.length; k++) {
									if (Ic1[j][0] == Ib1[k][0] && Ic1[j][1] == Ib1[k][1]) {
										P = Ib1[k][2];
										break;
									}
								}
								I23[indexI23][0][0] = Ic1[j][0];
								I23[indexI23][0][1] = Ic1[j][1];
								argsIndex++;
								I23[indexI23][argsIndex][1] = P;
								I23[indexI23][argsIndex][0] = Ic1[j][2];
							} else {
								I23[indexI23][argsIndex][1] = P;
								I23[indexI23][argsIndex][0] = Ic1[j][2];
								P = Ic1[j][2];
							}
							argsIndex++;
						}
					}
					usedI23[indexI23][0] = JI[i][0];
					usedI23[indexI23][1] = JI[i][1];
					argsIndex = 0;
					indexI23++;
				}
			}
		}
	}

	private static void createI13() {
		int[][] usedI13 = new int[60][2];
		int indexI13 = 1;
		int argsIndex = 2;
		int lastP = 0;
		for (int i = 0; i < nr; i++) {
			if (II[i][0] == 1000 && II[i][1] == 1000) {
				continue;
			} else {
				if (matrixSearch(usedI13, II[i][0], II[i][1])) {
					int P = 0;
					for (int j = 0; j < nr; j++) {
						if (II[i][0] == Ic1[j][0] && II[i][1] == Ic1[j][1]) {
							if (P == 0) {
								for (int k = 0; k < Ia1.length; k++) {
									if (Ic1[j][0] == Ia1[k][0] && Ic1[j][1] == Ia1[k][1]) {
										P = Ia1[k][2];
										if (P == 333) {
											lastP = P;
										}
										break;
									}
								}
								I13[indexI13][0][0] = Ic1[j][0];
								I13[indexI13][0][1] = Ic1[j][1];
								argsIndex++;
								I13[indexI13][argsIndex][1] = P;
								I13[indexI13][argsIndex][0] = Ic1[j][2];
							} else {
								I13[indexI13][argsIndex][1] = P;
								I13[indexI13][argsIndex][0] = Ic1[j][2];
								P = Ic1[j][2];
							}
							argsIndex++;
						}
					}
					if (i == nr-1) {
						I13[indexI13][argsIndex][1] = lastP;
						I13[indexI13][argsIndex][0] = P;
					}
					usedI13[indexI13][0] = II[i][0];
					usedI13[indexI13][1] = II[i][1];
					argsIndex = 0;
					indexI13++;
				}
			}
		}
	}

	private static void showWezlyI1() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 30; j++) {
				if (I1[i][j][0] == 999 || I1[i][j][1] == 999) {
					continue;
				} else {
					System.out.format("I1 : %d %d : ", I1[i][j][0], I1[i][j][1]);
					j+=28;
					for (int k = 1; k < 30; k++) {
						if (I1[i][k][0] == 999 || I1[i][k][1] == 999)  {
							continue;
						} else {
							System.out.format("[%d %d] ", I1[i][k][0], I1[i][k][1]);
						}
					}
					System.out.println();
				}
			}
		}
		System.out.println();
	}

	private static void showWezlyI2() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 30; j++) {
				if (I2[i][j][0] == 0 && I2[i][j][1] == 0) {
					continue;
				} else {
					System.out.format("I2 : %d %d : ", I2[i][j][0], I2[i][j][1]);
					j+=28;
					for (int k = 1; k < 30; k++) {
						if (I2[i][k][0] == 0 && I2[i][k][1] == 0)  {
							continue;
						} else {
							System.out.format("[%d %d] ", I2[i][k][0], I2[i][k][1]);
						}
					}
					System.out.println();
				}
			}
		}
		System.out.println();
	}

	private static void showWezlyI3() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 30; j++) {
				if (I3[i][j][0] == 0 && I3[i][j][1] == 0) {
					continue;
				} else {
					System.out.format("I3 : %d %d : ", I3[i][j][0], I3[i][j][1]);
					j+=28;
					for (int k = 1; k < 30; k++) {
						if (I3[i][k][0] == 0 && I3[i][k][1] == 0)  {
							continue;
						} else {
							System.out.format("[%d %d] ", I3[i][k][0], I3[i][k][1]);
						}
					}
					System.out.println();
				}
			}
		}
		System.out.println();
	}

	private static void showWezlyI4() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 30; j++) {
				if (I4[i][j][0] == 0 && I4[i][j][1] == 0) {
					continue;
				} else {
					System.out.format("I4 : %d %d : ", I4[i][j][0], I4[i][j][1]);
					j+=28;
					for (int k = 1; k < 30; k++) {
						if (I4[i][k][0] == 0 && I4[i][k][1] == 0)  {
							continue;
						} else {
							System.out.format("[%d %d] ", I4[i][k][0], I4[i][k][1]);
						}
					}
					System.out.println();
				}
			}
		}
		System.out.println();
	}

	private static void showWezlyI34() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 30; j++) {
				if (I34[i][j][0] == 0 && I34[i][j][1] == 0) {
					continue;
				} else {
					System.out.format("I34 : %d %d : ", I34[i][j][0], I34[i][j][1]);
					j+=28;
					for (int k = 1; k < 30; k++) {
						if (I34[i][k][0] == 0 && I34[i][k][1] == 0)  {
							continue;
						} else {
							System.out.format("[%d %d] ", I34[i][k][0], I34[i][k][1]);
						}
					}
					System.out.println();
				}
			}
		}
		System.out.println();
	}

	private static void showWezlyI24() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 30; j++) {
				if (I24[i][j][0] == 0 && I24[i][j][1] == 0) {
					continue;
				} else {
					System.out.format("I24 : %d %d : ", I24[i][j][0], I24[i][j][1]);
					j+=28;
					for (int k = 1; k < 30; k++) {
						if (I24[i][k][0] == 0 && I24[i][k][1] == 0)  {
							continue;
						} else {
							System.out.format("[%d %d] ", I24[i][k][0], I24[i][k][1]);
						}
					}
					System.out.println();
				}
			}
		}
		System.out.println();
	}

	private static void showWezlyI23() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 30; j++) {
				if (I23[i][j][0] == 0 && I23[i][j][1] == 0) {
					continue;
				} else {
					System.out.format("I23 : %d %d : ", I23[i][j][0], I23[i][j][1]);
					j+=28;
					for (int k = 1; k < 30; k++) {
						if (I23[i][k][0] == 0 && I34[i][k][1] == 0)  {
							continue;
						} else {
							System.out.format("[%d %d] ", I23[i][k][0], I23[i][k][1]);
						}
					}
					System.out.println();
				}
			}
		}
		System.out.println();
	}

	private static void showWezlyI13() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 30; j++) {
				if (I13[i][j][0] == 0 && I13[i][j][1] == 0) {
					continue;
				} else {
					System.out.format("I13 : %d %d : ", I13[i][j][0], I13[i][j][1]);
					j+=28;
					for (int k = 1; k < 30; k++) {
						if (I13[i][k][0] == 0 && I13[i][k][1] == 0)  {
							continue;
						} else {
							System.out.format("[%d %d] ", I13[i][k][0], I13[i][k][1]);
						}
					}
					System.out.println();
				}
			}
		}
		System.out.println();
	}

	private static void showCholeskyGrafPart1() {
		nr = 0;
		System.out.println("nr	X1	X2	X3		Ia1");
		while(X2[nr] != 0 || nr == 0) {
			System.out.format("%d	%d	%d	%d\t\t%d %d		sqrt\n",nr, X1[nr], X2[nr], X3[nr],
					Ia1[nr][0], Ia1[nr][1]);
			nr++;
		}
	}

	private static void showCholeskyGrafPart2() {
		nr = 0;
		System.out.println("nr	Z1	Z2	Z3		Ib1		Ib2");
		while(Z2[nr] != 0 || nr == 0) {
			System.out.format("%d	%d	%d	%d\t\t%d %d\t\t%d %d		div\n",nr, Z1[nr], Z2[nr], Z3[nr],
					Ib1[nr][0], Ib1[nr][1], Ib2[nr][0], Ib2[nr][1]);
			nr++;
		}
	}

	private static void showCholeskyGrafPart3() {
		nr = 0;
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
