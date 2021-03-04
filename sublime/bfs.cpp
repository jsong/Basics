

#include<iostream>
#include<vector>
#include<queue>

using namespace std;

void bfs(const vector<vector<int>>& g, vector<bool>& v, int i) {
	queue<int> q;
	q.push(i);
	v[i] = true;
	while (!q.empty()) {
		int key = q.front(); q.pop();
		cout << "key: " << key << "\n";
		vector<int> edges = g[key];
		for (int j = 0; j < edges.size(); j++) {
			if (!v[j]) {
				q.push(j);
				v[j] = true; 
			}
		}
	}
}

// 323    Number of Connected Components in an Undirected Graph
    int countComponents(int n, vector<pair<int, int> >& edges) {
    	// construct adjuscent matrix;
    	vector<vector<int>> g(n);
    	vector<bool> visited(n, false);
    	int res = 0;
    	for (auto p: edges) {
    		g[p.first].push_back(p.second);
    		g[p.second].push_back(p.first);
    	}

    	cout << "adjcent matrix size: " << g.size() << "\n";
    	for (int i = 0; i < n; i++) {
    		if (!visited[i]) {
    			++res;
    			bfs(g, visited, i);
    		}
    	}

    	return res;
    }


int main(int argc, const char * argv[]) {
	vector<vector<int>> g = {{0, 1}, {1,2}, {3,4}};
	vector<pair<int, int>> p = {{0, 1}, {1,2}, {3,4}};
	int c = countComponents(5, p);
	cout << "connected Graph: " << c << "\n";

	int s = g.size();
	cout << "matrix size: " << s << "\n";
	for (int i = 0; i < s; i++) {
		for (int j = 0; j < g[i].size(); j++) {
			cout << g[i][j] << " ";
		}

		cout << "\n";
	}

	cout << "Welcome to BFS\n";
	return 0;
}
