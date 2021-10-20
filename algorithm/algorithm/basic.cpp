#include <iostream>
#include <stack>
#include <vector>
#include <queue>
#include <unordered_set>
#include <set>
#include <algorithm> 
#include <unordered_map>
#include <bitset>

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

int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
}

int find(vector<int>& root, int x) {
    return root[x] == x ? x : (root[x] = find(root, root[x]));
}

int find2(vector<int>& root, int x) {
    if (root[x] == x) {
        return x;
    }
    
    int v = find2(root, root[x]);
    root[x] = v;
    
    return v;
    //    return root[x] = find2(root, root[x]);
}

int largestComponentSize(vector<int>& A) {
    int n = 0, mx = 0, res = 0;
    unordered_map<int, int> m;
    for (int num : A) mx = max(mx, num);
    vector<int> root(mx + 1);
    for (int i = 1; i <= mx; ++i) root[i] = i;
    for (int num : A) {
        for (int d = sqrt(num); d >= 2; --d) {
            if (num % d == 0) {
                root[find2(root, num)] = root[find2(root, d)];
                root[find2(root, num)] = root[find2(root, num / d)];
            }
        }
    }
    int rr = find2(root, 4);
    
    for (int num : A) {
        res = max(res, ++m[find2(root, num)]);
    }
    return res;
}

// TODO: implement the GCD algorithm.
string gcdOfString(string str1, string str2) {
    if (str1 + str2 != str2 + str1 ) return "";
    return str1.substr(0, gcd(str1.length(), str2.length()));
}

int getRoot(vector<int>& root, int i) {
    return i == root[i] ? i : getRoot(root, root[i]);
}

vector<int> findRedundantDirectedConnection(vector<vector<int>>& edges) {
    int n = edges.size();
    vector<int> root(n + 1, 0), first, second;
    for (auto& edge : edges) {
        if (root[edge[1]] == 0) {
            root[edge[1]] = edge[0];
        } else {
            first = {root[edge[1]], edge[1]};
            second = edge;
            // used to break the circle. otherwise for 1->2, 1->3, 2->3, it will consider it as circle.
            edge[1] = 0;
        }
    }
    // helper
    for (auto& edge : edges) {
        cout << edge[0] << "->" << edge[1] << "\n";
    }
    
    for (int i = 0; i <= n; ++i) root[i] = i;
    for (auto& edge : edges) {
        //            if (edge[1] == 0) continue;
        int x = getRoot(root, edge[0]), y = getRoot(root, edge[1]);
        if (x == y) return first.empty() ? edge : first;
        root[x] = y;
    }
    return second;
}

std::string bitRep(int a) {
    string res;
    while (a != 0) {
        res = to_string(a % 2) + res;
        a = a / 2;
    }
    
    return res;
}

//string shortestPalindrome(string s) {
//    int i = 0, n = s.size();
//    for (int j = n - 1; j >= 0; --j) {
//        if (s[i] == s[j]) ++i;
//    }
//    if (i == n) return s;
//    string rem = s.substr(i);
//    reverse(rem.begin(), rem.end());
//    return rem + shortestPalindrome(s.substr(0, i)) + s.substr(i);
//}

std::string opEqual(string a) {
    string s = "hello";
    s = a + s;
    cout << "s: " << s << "\n";
    return s;
}

int lengthOfLongestSubstring(string s) {
    unordered_map<char, int> map;
    int i = 0, j = 0, result = 0;
    while (j < s.length()) {
        cout << s[j];
        if (map.find(s[j]) == map.end())
        {
            map[s[j]] = j;
            result = max(result, j - i + 1);
            j++;
        }
        else
        {
            map.erase(s[i++]);
        }
    }
    
    return result;
}

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

int counter = 0;
void helper(const vector<int>& nums, int target, int start) {
    if (target == 0) {
        counter++;
        return;
    }
    if (target < 0) return;
    if (start >= nums.size()) return;
    
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

bool greaterString (string second, string first, unordered_map<char, int> dict) {
    int m = second.size(); int n = first.size();
    int len = m > n ? n : m;
    int i = 0;
    while (i < len) {
        if (dict[second[i]] > dict[first[i]]) return true;
        else if (dict[second[i]] < dict[first[i]]) return false;
        i++;
    }
    // len of chars are equal,
    if (m < n) return false;
    return true;
}

bool isAlienSorted(vector<string>& words, string order) {
    unordered_map<char, int> dict;
    
    for (int i = 0; i < 26; i++) {
        dict[order[i]] = i;
    }
    
    for (int i = 1; i < words.size(); i++) {
        if (!greaterString(words[i], words[i - 1], dict))
            return false;
    }
    
    return true;
}


string findReplaceString(string s, vector<int>& indexes, vector<string>& sources, vector<string>& targets) {
    unordered_map<int, int> m;
    string res = "";
    for (int i = 0; i < indexes.size(); i++) {
        if (s.substr(indexes[i], sources[i].size()) == sources[i]) {
            m[indexes[i]] = i;
        }
    }
    
    for (int i = 0; i < s.size();) {
        if (m.count(i)) {
            res += targets[m[i]];
            i += sources[i].size();
        } else {
            res += s[i];
            i++;
        }
    }
    
    return res;
}

 vector<int> sortedSquares(vector<int>& nums) {
        int n = nums.size();
        vector<int> res(n);
        int i = 0, j = n - 1;
        for (int k = n - 1; k >= 0; k--) {
            if (abs(nums[i]) >= abs(nums[j])) {
                res[k] = abs(nums[i]) * abs(nums[i]);
                i++;
            } else {
                res[k] = abs(nums[j]) * abs(nums[j]);
                j--;
            }
        }
        
        return res;
    }


vector<int> findSubstring(string s, vector<string>& words) {
    vector<int> res;
    int n = words.size();
    int len = 0;
    // make sure it contains any word
    if (n > 0) {
        len = words[0].size();
    }
    unordered_map<string, int> m;
    // actual words;
    int act = n;
    for (auto w: words) {
        if (m.find(w) != m.end()) {
            act--;
        }
        m[w]++;
    }
    
    unordered_map<string, int> copy = m;
    int loop = s.size() - n * len;
    
    for (int i = 0; i <= loop; i++) {
        string sub = s.substr(i, n * len);
        cout << "sub: " << sub << "len: " << sub.size() << "\n";
        
        m = copy;
        int counter = act;
        for (int j = 0; j < sub.size(); j += len) {
            string ss = sub.substr(j, len);
            if (m.find(ss) != m.end()) {
                m[ss]--;
                
                if (m[ss] == 0) {
                    counter--;
                    if (counter == 0) {
                        // we found a match.
                        res.push_back(i);
                    }
                }
            }
        }
    }
    
    return res;
}

/*
int main()
{
    vector<string> ssr {"a", "a"};
    // {"fooo","barr","wing","ding","wing"};
    string ssss = "a";
    // "lingmindraboofooowingdingbarrwingmonkeypoundcake";
    vector<int> rrr = findSubstring(ssss, ssr);
    
    vector<int> sq {-4, -1, 0, 3, 10};
    sortedSquares(sq);
	vector<int> indexes {0, 2};
	vector<string> sources {"ab", "cd"};
	vector<string> targets {"eee", "fff"};
	string sss = findReplaceString("abcd", indexes, sources, targets);
	cout << sss << "\n";
 
    vector<int> twoSum {3, 3};
	unordered_map<int, int> mTwo;
	for (int i = 0; i < twoSum.size(); i++) {
		mTwo[twoSum[i]] = i;
	}

	cout << mTwo.size() << ": map size\n";
 
    vector<string> words = {"apple", "app"};
    string order = "worldabcefghijkmnpqstuvxyz";
    bool al = isAlienSorted(words, order);
    
    unordered_map<int, int> m{{0, 1}};
    if (m.count(0)) {
        cout << "has value:" << m[0];
    }
    
    vector<int> a = {1, 2, 3};
    int r = subarraySum(a, 3);
    
    cout << longestSubWithAtLeast2DistinctChars("eceba");
    string lols = "abcabcbb";
    lengthOfLongestSubstring(lols);
    
    
    cout << shortestPalindrome("abbac");
    string bb = "123";
    cout << bb.substr(0, 1) + bb.substr(1);
    opEqual("world");
    string input = "0110";
    for (int i = 1; i <=3; i++) {
        cout << bitRep(i) << "\n";
        cout << ":" << input.find(bitRep(i)) <<"\n";
    }
    
    
    cout << bitRep(17);
    cout << bitset<2>(6).to_string() << "\n";
    vector<vector<int>> rc {{1, 2}, {1, 3}, {2, 3}};
    vector<int> rcv = findRedundantDirectedConnection(rc);
    
    
    vector<vector<int>> matrix {{1,2,3,4}, {2,3,4,5}, {3,4,5,6}};
    int row = matrix.size();
    int column = matrix[1].size();
    cout << "row:" << row << " column: " << column << "\n";
    
    vector<int> vv {4, 6, 9, 10};
    //    vv = {2,3,6,7,4,12,21,39};
    int lar = largestComponentSize(vv);
    cout << "Largest: " << lar;
    
    
    string as = "abcabc";
    string bs = "abc";
    string rr = gcdOfString(as, bs);
    
    cout << "GCD: " << rr << "\n";
    
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
    
    // for (auto a: q) {
    // 	cout << a << "\n";
    // }
    
    string aaa = "Hello";
    string b = aaa.substr(0, 0) + aaa.substr(1);
    cout << b << "\n";
    
    cout << "Hello DFS\n";
    vector<int> dist(10, 5);
    for (auto a: dist) {
        cout << a;
    }
    cout << "vector size:" << dist.size() << "\n";
    int aa = 2;
    int target = 0;
    for (int i = 0; i < 4; i++) {
        int vv = 1 << i;
        target |= vv;
        cout <<  i << ": " << vv << "\n";
        cout << "target: " << target << "\n";
    }
    
    for (int i = dist.size(); i > 0; i--) {
        if (i == 5) {
            // cout << " >>> 5: " << "\n";
            // dist.push_back(55);
            break;
        }
        cout << "i: " << i << "\n";
    }
    
    cout << "dist: " << dist.size() << "\n";
    
    // bit
    int ddd = 2;
    cout << (3 << ddd) << "\n"; // 3 * 2^2
    
    int aaaa = 6;
    int bbb = 1;
    int ccc = aaaa | bbb;
    cout << "|: " << ccc << "\n";
    
    // shift operands
    int so = 1 >> 3;
    cout << "shift: " << so << "\n";
    
    so = 4;
    int bo = 6;
    cout << "mod: " << so % bo << "\n";
    
    
    // cout << "bit mask: " << vv << "\n";
    return 0;
}
 */
