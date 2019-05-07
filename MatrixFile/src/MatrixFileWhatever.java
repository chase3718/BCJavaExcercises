
import java.io.*;
import java.util.*;

public class MatrixFileWhatever {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("MatrixFileUltimate.txt"));
		int[][][] a = new int[10][10][10];
		int[][][] b = new int[10][10][10];
		int row = -1;
		int[] mra = new int[10];
		int[] mrb = new int[10];
		int column = -1;
		int[] mca = new int[10];
		int[] mcb = new int[10];
		int numArray = 0;
		String nextL;
		sc.nextLine();
		do {
			row = -1;
			column = -1;
			do {
				nextL = sc.nextLine();
				if (nextL.equals("row")) {
					row++;
					mra[numArray] = Math.max(mra[numArray], row + 1);
					column = -1;
				} else if (!nextL.equals("matrix")) {
					column++;
					mca[numArray] = Math.max(mca[numArray], column + 1);
					a[numArray][row][column] = Integer.parseInt(nextL);
				}
			} while (!nextL.equals("matrix"));
			row = -1;
			column = -1;
			do {
				nextL = sc.nextLine();
				if (nextL.equals("row")) {
					row++;
					mrb[numArray] = Math.max(mrb[numArray], row + 1);
					column = -1;
				} else if (!nextL.equals("matrix")) {
					column++;
					mcb[numArray] = Math.max(mcb[numArray], column + 1);
					b[numArray][row][column] = Integer.parseInt(nextL);
				}
			} while (!nextL.equals("matrix") && sc.hasNextLine());
			numArray++;
		} while (sc.hasNextLine());
		for (int i = 0; i < numArray; i++) {
			System.out.println("Matrices " + (2 * i + 1) + " and " + (2 * i + 2) + ":");
			int[][] a2 = new int[mra[i]][mca[i]];
			for (int y = 0; y < mra[i]; y++) {
				for (int x = 0; x < mca[i]; x++) {
					a2[y][x] = a[i][y][x];
				}
			}
			int[][] b2 = new int[mrb[i]][mcb[i]];
			for (int y = 0; y < mrb[i]; y++) {
				for (int x = 0; x < mcb[i]; x++) {
					b2[y][x] = b[i][y][x];
				}
			}
			int[][] c = MatrixMult.mult(a2, b2);
			if (c.length <= 0) {
				System.out.println("No Work");
			} else {
				for (int y = 0; y < c.length; y++) {
					for (int x = 0; x < c[y].length; x++) {
						System.out.print(c[y][x] + "\t");
					}
					System.out.println();
				}
			}
		}
	}
}
