#include <iostream>
#include <stack>
#include <vector>
#include <queue>
#include <unordered_set>
#include <set>

using namespace std;

/**
 * Definition for a binary tree node.
  */
struct TreeNode {
	int val;
	TreeNode *left;
	TreeNode *right;
	TreeNode() : val(0), left(nullptr), right(nullptr) {}
	TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
	TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};


// 785. Is Graph Bipartite?
bool isBipartite(vector<vector<int>>& graph) {
	return false;
}


// 301. Remove Invalid Parentheses
vector<string> removeInvalidParentheses(string s) {
	return {};
}

// 105. Construct Binary Tree from Preorder and Inorder Traversal

TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
	TreeNode* node = new TreeNode(0);
	return node;
}


string alienOrder(vector<string>& words) {
	set<pair<char, char>> st;
        unordered_set<char> ch;
        vector<int> in(256);
        queue<char> q;
        string res;
        for (auto a : words) ch.insert(a.begin(), a.end());
        for (int i = 0; i < (int)words.size() - 1; ++i) {
            int mn = min(words[i].size(), words[i + 1].size()), j = 0;
            for (; j < mn; ++j) {
                if (words[i][j] != words[i + 1][j]) {
                    st.insert(make_pair(words[i][j], words[i + 1][j]));
                    break;
                }
            }
            if (j == mn && words[i].size() > words[i + 1].size()) return "";
        }

        return "123";
}

// TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder) {
// 	return buildTree(preorder, 0, preorder.size() - 1, inorder, 0, inorder.size() - 1);
// }
// TreeNode *buildTree(vector<int> &preorder, int pLeft, int pRight, vector<int> &inorder, int iLeft, int iRight) {
// 	if (pLeft > pRight || iLeft > iRight) return NULL;
// 	int i = 0;
// 	for (i = iLeft; i <= iRight; ++i) {
// 		if (preorder[pLeft] == inorder[i]) break;
// 	}
// 	TreeNode *cur = new TreeNode(preorder[pLeft]);
// 	cur->left = buildTree(preorder, pLeft + 1, pLeft + i - iLeft, inorder, iLeft, i - 1);
// 	cur->right = buildTree(preorder, pLeft + i - iLeft + 1, pRight, inorder, i + 1, iRight);
// 	return cur;
// }

/*
int main()
{
	vector<int> v {-1, 0, 1};
	for (auto a: v) {
		if (!a) {
			cout << a << "\n";
		}
	}
	unordered_set<char> ch;
	set<pair<char,char>> st;
	st.insert(make_pair('b','c'));
	st.insert(make_pair('a','d'));

	for (auto a: st) {
		cout << a.first << ":" << a.second << "\n";
	}

	vector<string> ss {"za", "z"};
	for (auto a: ss) {
		ch.insert(a.begin(), a.end());
	}

	for (auto a: ch) {
		cout << a << "\n";
	}

	queue<int> q {{1, 2, 3}};
	queue<string> qs {{"z", "x", "z"}};
    
    string r = alienOrder(ss);

	// for (auto a: q) {
	// 	cout << a << "\n";
	// } 

	string a = "Hello";
	string b = a.substr(0, 0) + a.substr(1);
	cout << b << "\n";

	cout << "Hello DFS\n";	
	return 0;
}
*/
