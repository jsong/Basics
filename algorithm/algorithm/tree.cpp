// Tree 
#include <iostream>
#include <stack>
#include <vector>
#include <queue>

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


// Definition for a Node.
struct Node {
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
 

// 589. N-ary Tree Preorder Traversal
// stack push first will visit last.
 vector<int> preorder(Node* root) {
 	if (root == nullptr) return {};
 	stack<Node*> s {{root}};
 	vector<int> v;

 	while (!s.empty()) {
 		Node * t = s.top(); 
 		s.pop();
 		v.push_back(t->val);
 		int size = t->children.size();

 		for (int i = size - 1; i >= 0; i--) {
 			if (t->children[i]) {
 				s.push(t->children[i]);
 			}
 		}
 	}

 	return v;
 }

 // 590. N-ary Tree Postorder Traversal
vector<int> postorder(Node* root) {
	if (root == nullptr) return {};
	stack<Node*> s {{root}};
	vector<int> v;

	while (!s.empty()) {
		Node* t = s.top(); s.pop();
		v.insert(v.begin(), t->val);
		int size = t->children.size();
		for (int i = 0; i < size; i++) {
			if (t->children[i]) {
				s.push(t->children[i]);
			}
		}
	}

	return v;
}

// TODO: 
// 1. We don't need the vector to store the values, we just need the previous value. 
// 2. We don't have to use local variable and break, just return the value.
// 98. Validate Binary Search Tree
class Solution {
public:
    bool isValidBST(TreeNode* root) {
       stack<TreeNode*> s;
       TreeNode* pre;

       while (root != nullptr || !s.empty()) {
       		while (root) {
       			s.push(root);
       			root = root->left;
       		}

       		root = s.top();
       		s.pop();
       		if (pre != nullptr && pre->val >= root->val) {
       			return false;
       		}
       		pre = root;
       	    root = root->right;
       }


       return true; 
    }
};

// 230. Kth Smallest Element in a BST
int kthSmallest(TreeNode* root, int k) {
	stack<TreeNode*> s;
	int counter = 0;
	while (root != nullptr || !s.empty()) {
		while (root != nullptr) {
			s.push(root);
			root = root->left;
		}

		root = s.top();
		s.pop();

		if (++counter == k) {
			return root->val;
		}

		root = root->right;
	}

	return 0;
}


// Preorder traveral using non recursive method.
std::vector<int> preOrder(TreeNode* root) {
	if (!root) return {};
	std::vector<int> v;
	stack<TreeNode*> s {{root}};
	
	while(!s.empty()) {
		TreeNode *t = s.top(); s.pop();
		v.push_back(t->val);
		if (t->right) s.push(t->right);
		if (t->left) s.push(t->left);
	}

	return v;
}

// Postorder traversal using non recursive method.
// 
std::vector<int> postOrder(TreeNode* root) {
	if (!root) return {};
	std::vector<int> v;
	stack<TreeNode*> s {{root}};
	TreeNode* head = root;
	while(!s.empty()) {
		TreeNode *t = s.top();

		if ((!t->right && !t->left)|| head == t->left || head == t->right) {
			s.pop();
			head = t;
			v.push_back(t->val);
		}
		else {
		   if (t->right) s.push(t->right);
		   if (t->left) s.push(t->left);
		}
	}

	return v;
}

// 429. N-ary Tree Level Order Traversal
vector<vector<int>> levelOrder(Node* root) {
	if (root == nullptr) return {};
   	vector<vector<int>> v;

   	queue<Node*> q;
   	q.push(root);

   	while (!q.empty()) {
   		int size = q.size();
   		vector<int> level;
   		for (int i = 0; i < size; i++) {
   			Node* node = q.front(); q.pop();
   			level.push_back(node->val);
   			for (Node* n: node->children) {
   				if (n) {
   					q.push(n);
   				}
   			}
   		}

   		v.push_back(level);
   	}

   	return v;  
}

/**
 *101. Symmetric Tree
 */
bool helper(TreeNode* left, TreeNode* right) {
	if (left == nullptr && right == nullptr) return true;
	if ((left == nullptr && right) || (left && right == nullptr) || left->val != right->val) return false;
	return helper(left->left, right->right) && helper(left->right, right->left);
}

// We also could use 2 queues method to solve it.
bool isSymmetric(TreeNode* root) {
	if (root == nullptr) return true;
	return helper(root->left, root->right);
}

// end of 101. Symmetric Tree.

// 542. 01 Matrix
vector<vector<int>> updateMatrix(vector<vector<int>>& matrix) {
	vector<vector<int>> v;

	return v;
}

// 107. Binary Tree Level Order Traversal II
vector<vector<int>> levelOrderBottom(TreeNode* root) {
	if (!root) return {};

	vector<vector<int>> r;
	queue<TreeNode*> q;
	q.push(root);
	while (!q.empty()) {
		int size = q.size();
		vector<int> level;
		for (int i = 0; i < size; i++) {
			TreeNode *node = q.front(); q.pop();
			level.push_back(node->val);
			if (node->left) q.push(node->left);
			if (node->right) q.push(node->right);
		}

		r.insert(r.begin(), level);
	}

	return r;
}


int main(int argc, const char * argv[]) {
	cout << "Welcome to Tree\n";
	return 0;
}