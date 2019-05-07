public class MatrixMult {
	public static int[][] mult(int[][] a1, int[][] a2) {
		if (a1[0].length == a2.length) {
			int[][] a3 = new int[a1.length][a2[0].length];
			for (int y = 0; y < a1.length; y++) {
				for (int x = 0; x < a2[0].length; x++) {
					int sum = 0;
					for (int z = 0; z < a1[0].length; z++) {
						sum += a1[y][z] * a2[z][x];
					}
					a3[y][x] = sum;
				}
			}
			return a3;
		}
		return new int[0][0];
	}
}