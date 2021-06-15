#include <vector>
#include <iostream>
#include <unordered_map>

// Bubble Sort
// 347. Top K Frequent Elements
using namespace std;

int longestSubWithAtLeast2DistinctChars(string s) {
	unordered_map<char, int> m;
	string r;
	int start = 0; int end = 0; int len = 0;

	while (end < s.size()) {
		++m[s[end]];
		end++;
		// trimming
		while (m.size() > 2) {
			if (m.count(s[start])) {
				if (--m[s[start]] == 0) {
					m.erase(s[start]);
				}
				start++;
			}
		}
		
		len = max(len, end - start);
	}

	return len;
}


void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
	int i = m - 1, j = n - 1, k = m + n - 1;
	while (i >= 0 && j >= 0) {
		if (nums1[i] < nums2[j]) nums1[k--] = nums2[j--];
		else nums1[k--] = nums1[i--];
	}

	while (j >= 0) {
		nums1[k--] = nums2[j--];
	}
}


string findReplaceString(string s, vector<int>& indexes, vector<string>& sources, vector<string>& targets) {
        unordered_map<int, int> m;
        string res = "";
        for (int i = 0; i < s.size(); i++) {
            if (s.substr(indexes[i], sources[i].size()) == sources[i]) {
                m[indexes[i]] = i;
            }
        }
        
        for (int i = 0; i < s.size(); i++) {
            if (m.count(i)) {
                res += targets[i];
            } else {
                res += s[i];
            }
        }
        
        return res;
    }

int main(int argc, const char * argv[]) {
	vector<int> v {1 , 2, 3};
	v.insert(v.begin(), 0);
	for (auto a: v) {
		// cout << a;
	}

	vector<int> indexes {0, 2};
	vector<string> sources {"ab", "cd"}; 
	vector<string> targets {"eee", "fff"};
	string ss = findReplaceString("abcd", indexes, sources, targets);
	cout << ss << "\n";
	string a = "eceba";
	int l = longestSubWithAtLeast2DistinctChars(a);
	unordered_map<char, int> k;
	for (auto c: a) {
		--k[c];
	}

	string b = "456";
	int r = 123 + stoi(b);
	cout << r << "success\n";

	cout << "size: " << k.size();
	cout << "159. longestSubWithAtLeast2DistinctChars :" <<  l << "\n";
	cout << "Welcome to DFS\n";
}