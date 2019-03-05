package com.leetcode.isprime;

public class Solution {

	public static void main(String[] args) {
		Solution sl = new Solution();
		sl.countPrimes(1);
	}


	public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }

        return count;
    }

		// 202. Happy Number
		// Company:
		// Description:
		// Solution:
		public boolean isHappy(int n) {
			HashSet<Integer> set = new HashSet<>();
			while (n != 1)
			{
				int t = 0;
				while (n != 0)
				{
					t += (n % 10) * (n % 10);
					n /= 10;
				}

				n = t;
				if (set.contains(n)) break;
				else set.add(n);
			}

			return n == 1;
    }
}
