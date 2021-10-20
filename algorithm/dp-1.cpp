#include <iostream>
#include <vector>

using namespace std;

//    2
//  3   4
// 6  5  7

// DP and DFS 
int minimumTotal(vector<vector<int> >& triangle) {
	return 0;
}


int maxProfit(vector<int>& prices) {
        // find the minimum first.
        int minimum = INT_MAX;
        int minIdx = 0;
        int res = 0;
        for (int i = 0; i < prices.size(); i++) {
            if (prices[i] < minimum) {
                minimum = prices[i];
                minIdx = i;
            }   
        }
        
        // starts from the minimum, find the largest.
        // this is wrong, we shouldn't just start from the minimum
        
        for (int i = minIdx + 1; i < prices.size(); i++) {
            res = max(res, prices[i] - minimum);
        }
        
        return res;
    }

// 174. Dungeon Game  // Minimum health required to save the prince.
int calculateMinimumHP(vector<vector<int> >& dungeon) {
        int n = dungeon.size();
        int m = dungeon[0].size();
        // maximum path sum, so that the required hp are the least
        // dp[i][j] means when it reaches i or j, the maximum sum right now.
        vector<vector<int> > dp(n, vector<int>(m, INT_MAX));
        dp[0][0] = dungeon[0][0];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) dp[i][j] = min(dp[i][j - 1], dp[i][j]) + dungeon[i][j];
                else if (j == 0) dp[i][j] = min(dp[i - 1][j], dp[i][j]) + dungeon[i][j];
                else dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + dungeon[i][j];
            }
        }
        cout << "dp: " << dp[n - 1][m - 1];
        if (dp[n -1][m -1] >=0) return 1;
        else return (0 - dp[n - 1][m - 1] + 1);
}

int main() {
	int n = 3;
	int m = 4;

	vector<vector<int> > v (n, vector<int> (m, INT_MIN));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << v[i][j] << " ";
		}

		cout << "\n";
	}

	cout << "Hello DP\n";
}