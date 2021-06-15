//
//  bfs.cpp
//  Hello World
//
//  Created by Song, Jian on 3/1/21.
//  Copyright © 2021 Jian. All rights reserved.
//

#include <stdio.h>
#include <iostream>
#include <vector>
#include <queue>
#include <map>
#include <unordered_set>

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
            // std::cout << "top:" << q.front() << "\n";
            int r = q.front() / n, c = q.front() % n; q.pop();
            // std::cout << "r: " << r << ",c: " << c << "\n";
            for (int k = 0; k < 4; ++k) {
                int x = r + dir[k], y = c + dir[k + 1];
                // std::cout << "x: " << x << ",y: " << y << "\n";
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] == 1 || forest[x][y] == 0) continue;
                // std::cout << "forest[x,y] " << forest[x][y] << "\n";
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
        // std::cout << "treeRow: " <<trees[i][1] << ", treeCol: " << trees[i][2] << "tree: " << trees[i][0] << "\n";
        int cnt = helper(forest, row, col, trees[i][1], trees[i][2]);
        if (cnt == -1) return -1;
        res += cnt;
        row = trees[i][1];
        col = trees[i][2];
    }
    return res;
}




void bfsConnectedGraph(const vector<vector<int>>& g, vector<bool>& v, int i) {
    queue<int> q;
    q.push(i);
    v[i] = true;
    while (!q.empty()) {
        int key = q.front(); q.pop();
        vector<int> edges = g[key];
        for (int j = 0; j < edges.size(); j++) {
            if (!v[edges[j]]) {
                q.push(edges[j]);
                v[edges[j]] = true;
            }
        }
    }
}

void dfsConnectedGraph(const vector<vector<int>>& g, vector<bool>& visited, int i) {
    if (visited[i]) return;
    visited[i] = true;
    for (auto j: g[i]) {
        dfsConnectedGraph(g, visited, j);
    }
}

// 323 Number of Connected Components in an Undirected Graph
// 1. always construct the adjcent matrix. 
// 2. visit the unvisited nodes, through either bfs or dfs. 
int countComponents(int n, vector<pair<int, int> >& edges) {
    // construct adjuscent matrix;
    vector<vector<int>> g(n);
    vector<bool> visited(n, false);
    int res = 0;
    for (auto p: edges) {
        g[p.first].push_back(p.second);
        g[p.second].push_back(p.first);
    }
    
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            ++res;
            bfsConnectedGraph(g, visited, i);
        }
    }
    
    return res;
}


// 207. Course Schedule
// dfs solution
bool dfsCourses(vector<int> visited, const vector<vector<int>>& g, int i) {
    if (visited[i] == -1) return false;
    visited[i] = -1;
    for (auto course: g[i]) {
        if (!dfsCourses(visited, g, course)) return false;
    }
    
    visited[i] = 1;
    return true;
}

bool bfsCourses(vector<int>& in, const vector<vector<int>>& g, int numCourses) {
    queue<int> q;
    for (int i = 0; i < numCourses; i++) {
        if (in[i] == 0) {
            q.push(i);
        }
    }

    while(!q.empty()) {
        int p = q.front();
        q.pop();
        vector<int> neighbours = g[p];
        for (auto a: neighbours) {
            --in[a];
            // MARK: this is important, draw a graph. 2->1-><-0,
            if (in[a] == 0) q.push(a);
        }
    }

    for (auto a: in) {
        if (a != 0) return false;
    }

    return true;
}

// detect rings in the directed graph, use in to track the 0
bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
    // adjacent matrix
    vector<vector<int>> g (numCourses);
    vector<int> in(numCourses, 0); // bfs will use
    vector<int> visited(numCourses, 0);
    
    for (auto v: prerequisites) {
        g[v[1]].push_back(v[0]);
        ++in[v[0]];
    }

//    return bfsCourses(in, g, numCourses);
    return dfsCourses(visited, g, 0);
}

// 210. Course Schedule II
vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
    vector<int> v;
    vector<int> in(numCourses, 0);
    vector<vector<int>> g(numCourses);

    for (auto cources: prerequisites) {
        g[cources[1]].push_back(cources[0]);
        ++in[cources[0]];
    }

    queue<int> q;

    for (int i = 0; i < numCourses; i++) {
        if (in[i] == 0) q.push(i);
    }

    while (!q.empty()) {
        int n = q.front(); q.pop();
        v.push_back(n);
        for (auto v: g[n]) {
            if (--in[v] == 0) q.push(v);
        }
    }

    for (int i = 0; i < numCourses; i++) {
        if (in[i] != 0) return {};
    }

    return v;
}

// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> neighbors;
    Node() {
        val = 0;
        neighbors = vector<Node*>();
    }
    Node(int _val) {
        val = _val;
        neighbors = vector<Node*>();
    }
    Node(int _val, vector<Node*> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};

Node* bfsCloneGraph(map<Node*, Node*>& m, Node* node) {
 Node* clone = new Node(node->val);    
    m[node] = clone;
    queue<Node*> q;
    q.push(node);

    while (!q.empty()) {
        Node* n = q.front(); q.pop();
        for (auto i: n->neighbors) {
            if (!m.count(i)) {
                // MARK: Draw graph, avoid lock reference.
                q.push(i);
                Node* c = new Node(i->val);
                m[i] = c;
            }
            m[n]->neighbors.push_back(m[i]);
        }
    }

    return clone;
}

Node* dfsCloneGraph(map<Node*, Node*>& m, Node*& current) {
    if (!current) return nullptr;
    if (m.count(current)) return m[current];
    Node* clone = new Node(current->val);
    m[current] = clone;
    for (Node* neighbor: current->neighbors) {
        clone->neighbors.push_back(dfsCloneGraph(m, neighbor));
    }

    return clone;
}

// 133. Clone Graph
Node* cloneGraph(Node* node) {
    if (!node) return nullptr;
    map<Node*, Node*> m;
    return bfsCloneGraph(m, node);
    // return dfs
}

 int shortestPathLength(vector<vector<int>>& graph) {
        int n = graph.size(), target = 0, res = 0;
        unordered_set<string> visited;
        queue<pair<int, int>> q;
        for (int i = 0; i < n; ++i) {
        	int mask = (1 << i);
        	target |= mask;
        	visited.insert(to_string(mask) + "-" + to_string(i));
        	q.push({mask, i});
        }
        
        while (!q.empty()) {
        	for (int i = q.size(); i > 0; --i) {
                cout << "i:" << i << "\n";
        		auto cur = q.front(); q.pop();
                cout << "cur: " << cur.first << ": " << cur.second << "\n";
        		if (cur.first == target) return res;
        		for (int next : graph[cur.second]) {
        			int path = cur.first | (1 << next);
        			string str = to_string(path) + "-" + to_string(next);
                    cout << "visited: " << str << "\n";
        			if (visited.count(str)) continue;
        			visited.insert(str);
        			q.push({path, next});
        		}
        	}
            cout << "queue: " << q.size() << "\n";
        	++res;
        }
        return -1;
    }


/*
int main()
{
    // matrix test
    vector<vector<int>> vv (5, vector<int>(6,1));
    cout << "matrix size: " << vv.size() << " col size: " << vv[0].size() << " elem: "<< vv[0][5] << "\n";
    
    vector<vector<int>> v{{3,1,2},{0,0,4},{7,6,5}};
    int r = cutOffTree(v);
    //vector<pair<int, int>> p = {{0, 1}, {1,2}, {3,4}};
    vector<pair<int, int>> p = {{0, 1}, {1, 2}, {2, 3}, {2, 4}};
    int c = countComponents(5, p);
    cout << "connected Graph: " << c << "\n";
    
    // Course schedule
    vector<vector<int>> courses {{1, 0},{1, 2}, {0, 1}};
    vector<vector<int>> failedCourses {{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}};
    bool f = canFinish(20, failedCourses);
    courses = {{1,0}};
    // Course shedule II
    vector<int> order = findOrder(2, courses);
    cout << "Course Schedule finish: " << f << "\n";
    std::cout << "Hello BFS\n";
    
    vector<vector<int>> graph {{1, 2, 3}, {0}, {0}, {0}};
    vector<vector<int>> sgraph {{1}, {0, 2} , {1}};
    int rr = shortestPathLength(sgraph);
    cout << "Shortest path all nodes: " << rr << "\n";
    vector <int> init {1, 2, 3};
    for (int i = 0; i < init.size(); i++) {
        cout << "i: " << init[i] << "\n";
        if (i == 1) {
            init.push_back(4);
        }
    }
}
*/
