package skyDance;

import java.util.Scanner;



public class CommonIntersection {


	public static void findCommonPoint(int[][]coeff, int[][] constants){

		int[][] d1 = new int[3][3];
		int[][] d2 = new int[3][3];
		int[][] d3 = new int[3][3];

		if(determinant(coeff,3)==0)
			return;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(j==0)
					d1[i][j]=constants[i][0];
				if(j!=0)
				d1[i][j] = coeff[i][j];
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(j==1)
					d2[i][j]=constants[i][0];
				if(j!=1)
				d2[i][j] = coeff[i][j];
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(j==2)
					d3[i][j]=constants[i][0];
				if(j!=2)
				d3[i][j] = coeff[i][j];
			}
		}

		double x=(double)determinant(d1, 3)/determinant(coeff,3);
		double y=(double)determinant(d2, 3)/determinant(coeff,3);
		double z=(double)determinant(d3, 3)/determinant(coeff,3);
		System.out.println("x="+x+"  y="+y+"  z="+z);

	}

	public static int determinant(int A[][], int N) {
		int det = 0;
		if (N == 1) {
			det = A[0][0];
		} else if (N == 2) {
			det = A[0][0] * A[1][1] - A[1][0] * A[0][1];
		} else {
			det = 0;
			for (int j1 = 0; j1 < N; j1++) {
				int[][] m = new int[N - 1][];
				for (int k = 0; k < (N - 1); k++) {
					m[k] = new int[N - 1];
				}
				for (int i = 1; i < N; i++) {
					int j2 = 0;
					for (int j = 0; j < N; j++) {
						if (j == j1)
							continue;
						m[i - 1][j2] = A[i][j];
						j2++;
					}
				}
				det += Math.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1] * determinant(m, N - 1);
			}
		}
		return det;
	}


	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the order of the coefficient matrix");
		int n = input.nextInt();

		System.out.println("Enter the elements of the coefficient matrix");
		int[][] mat = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = input.nextInt();
			}
		}
		System.out.println("Enter the elements of the constant matrix");
		int[][] constant= new int[3][1];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 1; j++) {
				constant[i][j] = input.nextInt();
			}
		}

		findCommonPoint(mat, constant);
		input.close();

	}

}
