#include <vector>
#include <iostream>

// Bubble Sort
// 347. Top K Frequent Elements


using namespace std;

// Counting sort
// 1122. Relative Sort Array
vector<int> relativeSortArray(vector<int>& arr1, vector<int>& arr2) {
	std::vector<int> v;

	return v;
}

// Tree sort
// 109. Convert Sorted List to Binary Serach Tree
// Related to construct tree from sorted array?
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
// TreeNode* sortedListToBST(ListNode* head) {

// }

int main(int argc, const char * argv[]) {
	std::cout << "Hello World\n";
	std::vector<int> v(6);
	std::vector<int> s(7, 1);
	int n = 5;
	vector<vector<int> > g(n, v);
	
	for (int i = 0; i < g.size(); i++) {
		cout << "g size: " << g[i].size() << "\n";
	}

	// std::cout << "g size: " << g[0].size() << "\n";

	for (int i = 1; i < 5; i++) {
		v.push_back(i);
	}

	for (auto i : s) {
		std::cout << i << "\n";
	}

	return 0;
}