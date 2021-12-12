/*
 * EGG DROPPING PUZZLE
 * 
 * Description:  There is a tower of k floors, and an egg dropper with n ideal eggs. 
 * The physical properties of the ideal egg is such that it will shatter if it is dropped from 
 * floor k' - 1 or above, and will have no damage whatsoever if it is dropped from floor k' - 1 or below.
 * The problem is to find a strategy such that the egg dropper can determine the 
 * floor k' - 1 in as few egg drops as possible.
 * 
 * Time Complexity: O(n*k^2)
 * 
 * Auxiliary Space: O(n*k)
 * 
 * Author: Andrin Benz
 */
public class EggDroppingPuzzle {

	public static void main(String[] args) {
		System.out.println(eggDrop(0, 1)); // -1
		System.out.println(eggDrop(1, 2)); // 2
		System.out.println(eggDrop(2, 36)); // 8
	}

	/*
	 * Function to get the minimum number of trials needed in worst case with n eggs
	 * and k floors
	 */
	static int eggDrop(int n, int k) {
		// Check validity of entries
		if (n <= 0 || k <= 1) {
			return -1;
		}
		// Every entry represents the minimum number of trials for i eggs and j floors
		int[][] eggFloor = new int[n + 1][k + 1];

		// Basecase 1: (1 trial for 1 floor; 0 trials for 0 floors)
		for (int i = 0; i <= n; i++) {
			eggFloor[i][0] = 0;
			eggFloor[i][1] = 1;
		}

		// Basecase 2: (j trials for one egg and j floors)
		for (int j = 0; j <= k; j++) {
			eggFloor[1][j] = j;
		}

		// Fill the DP-Table
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= k; j++) {
				eggFloor[i][j] = Integer.MAX_VALUE;
				for (int x = 1; x <= j; x++) {
					int res = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]);
					if (res < eggFloor[i][j]) {
						eggFloor[i][j] = res;
					}
				}
			}
		}

		return eggFloor[n][k];
	}

}
