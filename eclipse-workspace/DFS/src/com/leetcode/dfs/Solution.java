package com.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num = "123456579";
		Solution sl = new Solution();
		boolean addictive = sl.isAdditiveNumber(num);
		System.out.println("is:" + addictive);

		sl.solveNQueens(4);

		int res = sl.totalNQueens(4);
		System.out.println("total: " + res);
	}

	// 200. Number of Islands
	// Description:
	// Company: Amazon Lyft Facebook Google LinkedIn Uber Bloomberg Apple Microsoft
	// Qualtrics Yahoo Twitter Alibaba Yelp Oracle Adobe
	// eBay AppDynamics Goldman Sachs Affirm # DoorDash Pinterest Zillow Palantir
	// Technologies Expedia.
	// Solution: Iterate each node, if node is 1, then DFS it's four directions,
	// make them as 0. Count how many 1s left.
	private int m;
	private int n;

	public int numIslands(char[][] grid) {
		m = grid.length;
		if (m == 0)
			return 0;
		n = grid[0].length;
		if (n == 0)
			return 0;

		int res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] - '0' == 1) {
					dfs(grid, i, j);
					res++;
				}
			}
		}

		return res;
	}

	private void dfs(char[][] grid, int i, int j) {
		if (i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == '0')
			return;
		grid[i][j] = '0';
		dfs(grid, i - 1, j);
		dfs(grid, i + 1, j);
		dfs(grid, i, j - 1);
		dfs(grid, i, j + 1);
	}

	// 306. Additive Number
	// Additive number is a string whose digits can form additive sequence.
	// Company: Epic Systems
	// Solution: Two pointers i, j, partition num into three parts, check whether i, j and j,k could form num.
	public boolean isAdditiveNumber(String num) {
		for (int i = 1; i <= num.length() / 2; ++i) {
			if (num.charAt(0) == '0' && i > 1)
				return false;
			for (int j = i + 1; j < num.length(); ++j) {
				if (num.charAt(i) == '0' && j - i > 1)
					break;
				if (dfs(num, 0, i, j))
					return true;
			}
		}
		return false;
	}

	private boolean dfs(String num, int i, int j, int k) {
		long num1 = Long.parseLong(num.substring(i, j));
		long num2 = Long.parseLong(num.substring(j, k));
		final String addition = String.valueOf(num1 + num2);

		if (!num.substring(k).startsWith(addition)) {
			return false;
		}

		if (k + addition.length() == num.length()) {
			return true;
		}

		return dfs(num, j, k, k + addition.length());
	}

	// 131. Palindrome Partitioning
	// Description: Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 	// Company: Amazon # Uber
	// Solution: DFS, like subset, continous pick the subsring (index, i + 1), leftover starts (i + 1).
	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();

		if (s == null || s.length() == 0) {
			return res;
		}

		dfsP(s, 0, res, new ArrayList<String>());
		return res;
  }

	private void dfsP(String s, int index, List<List<String>> res, List<String> path) {
		if (index == s.length()) {
			res.add(new ArrayList<>(path));
			return;
		}

		for (int i = index; i < s.length(); i++) {
			String sub = s.substring(index, i + 1);
			if (!isPalindrome(sub)) continue;
			path.add(sub);
			dfsP(s, i + 1, res, path);
			path.remove(path.size() - 1);
		}
	}

	private boolean isPalindrome(String s) {
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i++) == s.charAt(j--)) {
				continue;
			} else {
				return false;
			}
		}

		return true;
	}

	// 62. Unique Paths
	// Description: Robert starts from 1,1 to right corner, either move right or move down. How many paths from left corner to right corner.
	// Company: Google Amazon Apple Alibaba # Twitter Bloomberg Facebook Yahoo
	// Solution: 1. Naive DFS. Time Limit Exceeds. 2. DFS with Memo. 3. DP solution.
	// Navive DFS.
	public int uniquePaths(int m, int n) {
			if (m < 1 || n < 1) {
				return 0;
			}

			if (m == 1 || n == 1) {
				return 1;
			}

			return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
	}

	// DFS with Memo
	public int uniquePaths(int m, int n) {
		int[][] memo = new int[m + 1][n + 1];
		memo[0][0] = 1;
		return uniquePathsWithMemo(m, n, memo);
	}

	private int uniquePathsWithMemo(int m, int n, int[][] memo) {
		if (m < 1 || n < 1) {
			return 0;
		}

		if (m == 1 && n == 1) {
			return 1;
		}

		if (memo[m][n] != 0) {
			return memo[m][n];
		}

		int paths = uniquePathsWithMemo(m - 1, n, memo) + uniquePathsWithMemo(m, n - 1, memo);
		memo[m][n] = paths;
		return paths;
	}

	// DP Solution
	public int uniquePaths(int m, int n) {
		int[][] memo = new int[m + 1][n + 1];
		memo[1][1] = 1;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == 1 && j == 1) continue;
				memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
			}
		}

		return memo[m][n];
	}

	// 63. Unique Paths II
	// Description: Unique with obstacles.
	// Company: Amazon Bloomberg # Google Facebook
	// Solution: 1. DP solution. 2. DFS with Memo solution.
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null) {
			return 0;
		}

		int m = obstacleGrid.length;
		if (m == 0 || obstacleGrid[0].length == 0) {
			return 0;
		}
		int n = obstacleGrid[0].length;
		int[][] memo = new int[m + 1][n + 1];

		if (obstacleGrid[i][j] == 0) {
			memo[1][1] = 1;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if ((i == 1 && j == 1) || obstacleGrid[i - 1][j - 1] == 1) continue;
				memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
			}
		}

		return memo[m][n];
  }

	public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
		if (obstacleGrid == null) {
			return 0;
		}

		int m = obstacleGrid.length;
		if (m == 0 || obstacleGrid[0].length == 0) {
			return 0;
		}
		int n = obstacleGrid[0].length;
		int[][] memo = new int[m + 1][n + 1];
		return uniquePathWithObj(m, n, memo, obj);
	}

	private int uniquePathWithObj(int m, int n, int[][] memo, int[][] obj) {
		if (m < 1 || n < 1) {
			return 0;
		}

		if (obj[m - 1][n - 1] == 1) {
			return 0;
		}

		if (m == 1 && n == 1) {
			return 1;
		}

		if (memo[m][n] != 0) {
			return memo[m][n];
		}

		int paths = uniquePathWithObj(m - 1, n, memo, obj) + uniquePathWithObj(m, n - 1, memo, obj);
		memo[m][n] = paths;
		return paths;
	}

	// 51. N-Queens
	// Description: The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
	// Company: Facebook Amazon Alibaba # Apple Microsoft Rubrik Tableau
	// Solution: DFS start for row 0, and then iterate the next col to check whether it's a valid position, if so, record it's col into row. c[row] = col;
	//
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<>();
		int[] c = new int[n];
		dfsQueen(c, 0, res);
		return res;
  }

	// Main
	private void dfsQueen(int[] c, int row, List<List<String>> res) {
		if (row == c.length) {
			sprinf(c, res);
			return;
		}

		for (int i = 0; i < c.length; i++) {
			if (isValid(c, row, i)) {
				c[row] = i;	// chess row -> i, col -> j;
				dfsQueen(c, row + 1, res);
			}
		}
	}

	// Chess always N * N
	private void sprinf(int[] c, List<List<String>> res) {
		List<String> path = new ArrayList<>();
		for (int i = 0; i < c.length; i++) {
			char[] charArray = new char[c.length];
			Arrays.fill(charArray, '.');
			for (int j = 0; j < c.length; j++) {
				if (c[i] == j) {
					charArray[j] = 'Q';
				}
			}
			path.add(new String(charArray));
		}

		res.add(new ArrayList<>(path));
	}

	private boolean isValid2(int[] c, int row, int col) {
		// same col;
		for (int i = 0; i < row; i++) {
			if (c[i] == col || Math.abs(i - row) == Math.abs(c[i] - col)) {
				return false;
			}
		}
		return true;
	}

	// 52. N-Queens II
	// Description: Same as N-Queens I
	// Company: Facebook #
	// Solution: Same as 51. N-Queens.
	private int num = 0;
     public int totalNQueens(int n) {
         // List<List<String>> res = new ArrayList<>();
 		int[] c = new int[n];
 		dfsQueen(c, 0);
 		return num;
         // return res;
     }

  private void dfsQueen(int[] c, int row) {
 		if (row == c.length) {
 			num++;
 			return;
 		}

 		for (int i = 0; i < c.length; i++) {
 			if (isValid(c, row, i)) {
 				c[row] = i;	// chess row -> i, col -> j;
 				dfsQueen(c, row + 1);
 			}
 		}
 	}

 	private boolean isValid(int[] c, int row, int col) {
 		// same col;
 		for (int i = 0; i < row; i++) {
			if (c[i] == col || Math.abs(i - row) == Math.abs(c[i] - col)) {
				return false;
			}
		}

		return true;
 	}
}
