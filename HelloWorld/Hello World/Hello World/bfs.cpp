//
//  bfs.cpp
//  Hello World
//
//  Created by Song, Jian on 3/1/21.
//  Copyright © 2021 Jian. All rights reserved.
//

#include <stdio.h>
#include<iostream>
#include <vector>
#include <queue>

using namespace std;


int helper(vector<vector<int>>& forest, int row, int col, int treeRow, int treeCol) {
        if (row == treeRow && col == treeCol) return 0;
        int m = forest.size(), n = forest[0].size(), cnt = 0;
        queue<int> q{{row * n + col}};
        vector<vector<int>> visited(m, vector<int>(n));
        vector<int> dir{-1, 0, 1, 0, -1};
        while (!q.empty()) {
            ++cnt;
            for (int i = q.size(); i > 0; --i) {
                std::cout << "top:" << q.front() << "\n";
                int r = q.front() / n, c = q.front() % n; q.pop();
                std::cout << "r: " << r << ",c: " << c << "\n";
                for (int k = 0; k < 4; ++k) {
                    int x = r + dir[k], y = c + dir[k + 1];
                    std::cout << "x: " << x << ",y: " << y << "\n";
                    if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] == 1 || forest[x][y] == 0) continue;
                    std::cout << "forest[x,y] " << forest[x][y] << "\n";
                    if (x == treeRow && y == treeCol) return cnt;
                    visited[x][y] = 1;
                    q.push(x * n + y);
                }
            }
        }
        return -1;
    }
    
 int cutOffTree(vector<vector<int>>& forest) {
        int m = forest.size(), n = forest[0].size(), res = 0, row = 0, col = 0;
        vector<vector<int>> trees;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (forest[i][j] > 1) trees.push_back({forest[i][j], i, j});
            }
        }
        sort(trees.begin(), trees.end());
        for (int i = 0; i < trees.size(); ++i) {
            std::cout << "treeRow: " <<trees[i][1] << ", treeCol: " << trees[i][2] << "tree: " << trees[i][0] << "\n";
            int cnt = helper(forest, row, col, trees[i][1], trees[i][2]);
            if (cnt == -1) return -1;
            res += cnt;
            row = trees[i][1];
            col = trees[i][2];
        }
        return res;
    }
    
    

int main()
{
    vector<vector<int>> v{{3,1,2},{0,0,4},{7,6,5}};
    int r = cutOffTree(v);
    std::cout << "Hello BFS\n";
}
