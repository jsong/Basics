#include <vector>
#include <iostream>
#include <unordered_map>

using namespace std;





int counter = 0;
void helper(const vector<int>& nums, int target, int start) {
	if (target < 0) return;
	if (start >= nums.size()) return;
	if (target == 0) {
		counter++;
		return;
	}
	// for (int i = start; i < nums.size(); i++) {
		helper(nums, target - nums[start], start + 1);
	// }
}
int subarraySum(vector<int>& nums, int k) {
 	for (int i = 0; i < nums.size(); i++) {
 		helper(nums, k, i);	
 	}
 	return counter;       
}

bool greaterString (string a, string b, unordered_map<char, int> dict) {
	int m = a.size(); 
	int n = b.size();
	int i = 0;
	if (m < n) {
		while (i < m) {
			if (dict[a[i]] < dict[b[i]]) return false;
			i++;
		}
	} else {
		while (i < n) {
			if (dict[a[i] < dict[b[i]]]) return false;
			i++;
		}
	}
	
	return true;
}

bool isAlienSorted(vector<string>& words, string order) {
        unordered_map<char, int> dict;

        for (int i = 0; i < 26; i++) {
            dict[order[i]] = i;
        }
      
      for (int i = 1; i < words.size(); i++) {
      	 if (greaterString(words[i], words[i - 1], dict))
      	 	return false;
      }

      return true;    
}

int main(int argc, const char * argv[]) {
	vector<int> a = {1, 1, 1};
	unordered_map<int, int> m{{0, 1}};
	unordered_map<int, int> m1;
	cout << m1[2];
	vector<string> b = {"hello", "leetcode"};
	string order = "hlabcdefgijkmnopqrstuvwxyz";
	bool al = isAlienSorted(b, order);
	// TODO: play with char[] 
	cout << "\nSubarray sums equal to target: " << subarraySum(a, 2) << "\n";
}