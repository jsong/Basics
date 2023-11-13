//
//  main.cpp
//  Hello World
//
//  Created by Jian on 3/11/19.
//  Copyright © 2019 Jian. All rights reserved.
//

#include <iostream>
#include <vector>
#include <string>
#include <stack>
#include <unordered_map>
#include <queue>
#include <sstream>      // std::istringstream
#include "common.h"
#include <unordered_set>
#include <map>

using namespace std;

vector<int> findSubstring2(string s, vector<string>& words) {
    vector<int> res;
    if (s.empty() || words.empty()) return res;
    int n = words.size(), m = words[0].size();
    unordered_map<string, int> m1;
    for (auto &a : words) ++m1[a];
    for (int i = 0; i <= (int)s.size() - n * m; ++i) {
        unordered_map<string, int> m2;
        int j = 0;
        for (j = 0; j < n; ++j) {
            string t = s.substr(i + j * m, m);
            if (m1.find(t) == m1.end()) break;
            ++m2[t];
            if (m2[t] > m1[t]) break;
        }
        if (j == n) res.push_back(i);
    }
    return res;
}

vector<int> getRow(int rowIndex) {
    vector<int> array;
    for (int i = 0; i <= rowIndex; i++) {
        for (int j = i - 1; j > 0; j--){
            array[j] = array[j - 1] + array[j];
        }
        array.push_back(1);
    }
    return array;
}

vector<int> spiralOrder(vector<vector<int>>& matrix) {
    if (matrix.empty() || matrix[0].empty()) return {};
    int m = matrix.size(), n = matrix[0].size();
    vector<int> res;
    int up = 0, down = m - 1, left = 0, right = n - 1;
    while (true) {
        for (int j = left; j <= right; ++j) res.push_back(matrix[up][j]);
        if (++up > down) break;
        for (int i = up; i <= down; ++i) res.push_back(matrix[i][right]);
        if (--right < left) break;
        for (int j = right; j >= left; --j) res.push_back(matrix[down][j]);
        if (--down < up) break;
        for (int i = down; i >= up; --i) res.push_back(matrix[i][left]);
        if (++left > right) break;
    }
    return res;
}

int divide(int dividend, int divisor) {
    if (divisor == 0 || (dividend == INT_MIN && divisor == -1)) return INT_MAX;
    long long m = abs((long long)dividend), n = abs((long long)divisor), res = 0;
    int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
    if (n == 1) return sign == 1 ? m : -m;
    while (m >= n) {
        long long t = n, p = 1;
        while (m >= (t << 1)) {
            t <<= 1;
            p <<= 1;
        }
        res += p;
        m -= t;
    }
    return sign == 1 ? res : -res;
}

vector<string> fullJustify(vector<string> &words, int L) {
    vector<string> res;
    int i = 0;
    while (i < words.size()) {
        int j = i, len = 0;
        // minimum one space?
        while (j < words.size() && len + words[j].size() + j - i <= L) {
            len += words[j++].size();
        }
        
        string out;
        // spaces in a line.
        int space = L - len;
        // k, start word, j, end word.
        for (int k = i; k < j; ++k) {
            out += words[k];
            if (space > 0) {
                int tmp;
                if (j == words.size()) // last line.
                {
                    if (j - k == 1) tmp = space; // last word use all the left spaces.
                    else tmp = 1; // not the last word, just one empty space.
                }
                else
                {
                    if (j - k - 1 > 0)
                    {
                        if (space % (j - k - 1) == 0)
                        {
                            tmp = space / (j - k - 1);
                        }
                        else
                        {
                            tmp = space / (j - k - 1) + 1;
                        }
                    }
                    else
                    {
                        tmp = space; // last word, use all left spaces.
                    }
                }
                out.append(tmp, ' ');
                space -= tmp;
            }
        }
        res.push_back(out);
        i = j;
    }
    return res;
}

//int gcd(int m, int n)
//{
//    return n == 0? m: gcd(n, m % n);
//}

// 151. Reverse Words in a String
// Solution 1:
void reverseWordsI(string &s) {
    istringstream is(s);
    string tmp;
    is >> s;
    while(is >> tmp) s = tmp + " " + s;
    if(!s.empty() && s[0] == ' ') s = "";
}

// C++ no extra cost on space.
// Solution 2
void reverseWords(string s) {
    int storeIndex = 0, n = s.size();
    reverse(s.begin(), s.end());
    std::cout << "s: " << s << "\n";
    for (int i = 0; i < n; ++i) {
        if (s[i] != ' ') {
            if (storeIndex != 0) s[storeIndex++] = ' '; // make sure we only add one space in between.
            int j = i; // i when the words starts.
            while (j < n && s[j] != ' ') s[storeIndex++] = s[j++]; // j is the end of the words index.
            // i may not be zero, due to the leading zeroes. so storeIndex - (j - i);
            reverse(s.begin() + storeIndex - (j - i), s.begin() + storeIndex);
            i = j;
        }
    }
    s.resize(storeIndex);
}

void reverseManual(int left, int right, string& s)
{
    while (left < right)
    {
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        left++;
        right--;
    }
}

// 186 Reverse Words in a String II
string reverseWordsII(string &str) {
    reverse(str.begin(), str.end());
    int n = str.size();
    for (int i = 0, j = 0; i < n; i = j + 1)
    {
        for (j = i; j < n; j++)
        {
            if (str[j] == ' ' ) break;
        }
        
        reverse(str.begin() + i, str.begin() + j);
    }
    
    return str;
}

// 215. Kth Largest Element in an Array
int findKthLargest(vector<int>& nums, int k) {
    // C++ p_q largest on top.
    // Java p_q smallest on top.
    priority_queue<int, vector<int>, greater<int>> q;
    
    for (int i = 0; i < nums.size(); i++)
    {
        if (q.size() < k)
        {
            q.push(nums[i]);
        }
        else
        {
            if (q.top() < nums[i])
            {
                q.pop();
                q.push(nums[i]);
            }
        }
    }
    
    return q.top();
}

// 658. Find K Closest Elements
vector<int> findClosestElements(vector<int>& arr, int k, int x) {
    while (arr.size() > k)
    {
        if (x - arr.front() <= arr.back() - x)
        {
            arr.pop_back();
        }
        else
        {
            arr.erase(arr.begin());
        }
    }
    
    return arr;
}

// 34. Find First and Last Position of Element in Sorted Array
vector<int> searchRange(vector<int>& nums, int target) {
    int left = 0;
    int right = nums.size() - 1;
    int index = -1;
    // find the match
    while (left <= right)
    {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target)
        {
            index = mid;
            break;
        }
        
        if (nums[mid] < target)
        {
            left = mid + 1;
        }
        else
        {
            right = mid - 1;
        }
    }
    
    if (index == -1)
    {
        return {-1, -1};
    }
    
    // expand range with two pointer.
    int lower = index;
    int upper = index;
    
    while (nums[lower] == target) {
        lower--;
        if (lower < 0) break;
    }
    
    while (nums[upper] == target) {
        upper++;
        if (upper >= nums.size()) break;
    }
    
    return {lower + 1, upper - 1};
}

int lower_bound(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = (r-l)/2+l;
        if (nums[mid] < target)
            l = mid+1;
        else
            r = mid-1;
    }
    return l;
}

// 34. Find First and Last Position of Element in Sorted Array
// 2nd solution
vector<int> searchRange2(vector<int>& nums, int target) {
    int idx1 = lower_bound(nums, target);
    int idx2 = lower_bound(nums, target+1)-1;
    if (idx1 < nums.size() && nums[idx1] == target)
        return {idx1, idx2};
    else
        return {-1, -1};
}


// 161. One Edit Distance
bool isOneEditDistance(string s, string t) {
    return false;
}

// 286. Walls and Gates
void wallsAndGates(vector<vector<int>>& rooms) {
    
}

// ####
// 325. Maximum Size Subarray Sum Equals k
int maxSubArrayLen(vector<int> &nums, int k) {
    return 0;
}

// Above Core Algorithm similar to 303. Range Sum Query - Immutable

// #######
void helper(int sum, vector<int>& nums, int target, int& count) {
    if (sum == target)
    {
        count++;
        return;
    }
    else if (sum > target)
    {
        return;
    }
    
    for (int i = 0; i < nums.size(); i++)
    {
        helper(nums[i] + sum, nums, target, count);
    }
}


// 377. Combination Sum IV
int combinationSum4(vector<int>& nums, int target) {
    int count = 0;
    helper(0, nums, target, count);
    
    return count;
}

// ######

// 243. Shortest Word Distance
int shortestWordDistance(vector<string>& words, string word1, string word2) {
    int pos1 = -1, pos2 = -1;
    int distance = INT_MAX;
    
    for (int i = 0; i < words.size(); i++)
    {
        if (words[i] == word1)
        {
            pos1 = i;
        }
        
        if (words[i] == word2)
        {
            pos2 = i;
        }
        
        if (pos1 != -1 && pos2 != -1)
        {
            distance = min(distance, abs(pos1 - pos2));
        }
    }
    
    return distance;
}

// 244. Shortest Word Distance II, query may be used as much as possible.
int shortestWordDistanceII(vector<string>& words, string word1, string word2)
{
    int distance = INT_MAX;
    unordered_map<string, vector<int>> map;
    
    for (int i = 0; i < words.size(); i++)
    {
        map[words[i]].push_back(i);
    }
    
    vector<int> pos1 = map[word1];
    vector<int> pos2 = map[word2];
    int m = 0, n = 0;
    
    while(m < pos1.size() && n < pos2.size())
    {
        distance = min(distance, abs(pos1[m] - pos2[n]));
        pos1[m] < pos2[n] ? m++ : n++;
    }
    
    return distance;
}

// 245. Shortest Word Distance III, words1 and words2 are allow to be same.
int shortestWordDistanceIII(vector<string>& words, string word1, string word2)
{
    int p1 = -1, p2 = -1, t = -1, distance = INT_MAX;
    
    for (int i = 0; i < words.size(); i++)
    {
        t = p1;
        if (words[i] == word1)
        {
            p1 = i;
        }
        if (words[i] == word2)
        {
            p2 = i;
        }
        
        if (p1 != -1 && p2 != -1)
        {
            if (word1 != word2) //
            {
                distance = min(distance, abs(p1 - p2));
            }
            else
            {
                if (t != -1 && t != p1) // make, tree... , after first iteration, t == p1, when at tree.
                {
                    distance = min(distance, abs(p1 - t));
                }
            }
        }
    }
    
    return distance;
}

// 398. Random Pick Index
// 1. unordered_map to store the nums with index, find the key with all possible indexes. Rand pick
// 2. vector solution, TODO
class Solution {
private:
    unordered_map<int, vector<int>> vmap = {};
public:
    Solution(vector<int>& nums) {
        for (int i = 0; i < nums.size(); i++)
        {
            {
                vmap[nums[i]].push_back(i);
            }
        }
    }
    
    int pick(int target) {
        const vector<int>& candidates = vmap[target];
        int randIndex = rand() % candidates.size();
        return candidates[randIndex];
    }
};

// 339. Nested List Weight Sum
class NestedInteger // avoid compile error, fake class.
{
public:
    bool isInteger(){return 0;};
    vector<NestedInteger> getList(){vector<NestedInteger> r; return r;};
    int getInteger(){return 0;};
};

int helper(const vector<NestedInteger>& nestedList, int level)
{
    int res = 0;
    for (NestedInteger a: nestedList)
    {
        res += a.isInteger()? a.getInteger() * level: helper(a.getList(), level + 1);
    }
    
    return res;
}

int depthSum(const vector<NestedInteger>& nestedList) {
    // Write your code here
    return helper(nestedList, 1);
}

// 364. Nested List Weight Sum II -- Revisit BFS, in-order tranverse.

// 396. Rotate Function

// 464. Can I Win -- Recursive

// 515. Find Largest Value in Each Tree Row  -- BFS
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};


struct TrieNode {
    vector<TrieNode *> nodes;
    unordered_map<char, TrieNode *> children;
    bool wordEnded;
    //    TrieNode() :
};


vector<int> largestValues(TreeNode* root) {
    queue<TreeNode*> q;
    vector<int> level;
    if (root == nullptr)
    {
        return level;
    }
    
    q.push(root);
    
    while (!q.empty())
    {
        int size = q.size();
        int largest = INT_MIN;
        for (int i = 0; i < size; i++)
        {
            TreeNode* node = q.front();
            q.pop();
            largest = max(largest, node->val);
            if (node->left != nullptr)
            {
                q.push(node->left);
            }
            
            if (node->right != nullptr)
            {
                q.push(node->right);
            }
        }
        
        level.push_back(largest);
    }
    
    return level;
}

vector<TreeNode*> unihelper(int start, int end) {
    if (start > end) return {nullptr};
    vector<TreeNode*> res;
    for (int i = start; i <= end; ++i) {
        auto left = unihelper(start, i - 1), right = unihelper(i + 1, end);
        for (auto a : left) {
            for (auto b : right) {
                TreeNode *node = new TreeNode(i);
                node->left = a;
                node->right = b;
                res.push_back(node);
            }
        }
    }
    return res;
}

vector<TreeNode*> generateTrees(int n) {
    if (n == 0) return {};
    return unihelper(1, n);
}


// 525. Contiguous Array. if left...mid...right, if right == left, means the mid sums up to 0, which we could calculate what's the length.
int findMaxLength(vector<int>& nums) {
    unordered_map<int, int> sum_index = {{0, -1}};
    int res = 0;
    int sum = 0;
    for (int i = 0; i < nums.size(); i++)
    {
        sum += nums[i] == 0 ? -1: 1;
        if (sum_index.count(sum))
        {
            res = max(res, i - sum_index[sum]);
        }
        else
        {
            sum_index[sum] = i;
        }
    }
    
    return res;
}

// 529. Minesweeper
vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
    vector<vector<char>> res;
    return res;
}

// 537. Complex Number Multiplication
string complexNumberMultiply(string a, string b) {
    return "";
}

// 553. Optimal Division
string optimalDivision(vector<int>& nums) {
    string res;
    
    if (nums.size() == 0)
    {
        return "";
    }
    
    res = nums[0];
    
    if (nums.size() == 1)
    {
        return res;
    }
    
    if (nums.size() == 2)
    {
        return res + "/" + to_string(nums[1]);
    }
    
    res += "/(";
    
    for (int i = 1; i < nums.size(); i++)
    {
        res += to_string(nums[i]);
        res +="/";
    }
    
    res +=")";
    return res;
}

// 554. Brick Wall
int leastBricks(vector<vector<int>>& wall) {
    return 0;
}

// 636. Exclusive Time of Functions
vector<int> exclusiveTime(int n, vector<string>& logs) {
    vector<int> res(n, 0);
    stack<int> funcs;
    int preTime = 0;
    
    for (string log: logs)
    {
        int found1 = log.find(":");
        int found2 = log.find_last_of(":");
        int idx = stoi(log.substr(0, found1));
        string type = log.substr(found1 + 1, found2 - found1 - 1);
        int time = stoi(log.substr(found2 + 1));
        
        if (funcs.size() > 0)
        {
            res[funcs.top()] += time - preTime;
        }
        preTime = time;
        
        if (type == "start")
        {
            funcs.push(idx);
        }
        else
        {
            int t = funcs.top();
            funcs.pop();
            res[t]++;
            preTime++;
        }
    }
    
    return res;
}

// 640. Solve the Equation
string solveEquation(string equation) {
    
    return "";
}

// 646. Maximum Length of Pair Chain
// pairs could be in any order. *Sort it first.
// Solution 1: Time Limit exceeds. Two inner & outter loop
// Solution 2: Greedy, sort and use stack.
int findLongestChain2(vector<vector<int>>& pairs) {
    sort(pairs.begin(), pairs.end(), [](vector<int>& a, vector<int>& b) {
        return a[1] < b[1];
    });
    stack<vector<int>> s;
    
    for (auto pair: pairs)
    {
        if (s.empty())
        {
            s.push(pair);
        }
        else
        {
            if (s.top()[1] < pair[0])
            {
                s.push(pair);
            }
        }
    }
    
    return s.size();
}

int findLongestChain1(vector<vector<int>>& pairs) {
    int res = 0;
    sort(pairs.begin(), pairs.end(), [](vector<int>& a, vector<int>& b) {
        return a[1] < b[1];
    });
    
    for (int i = 0; i < pairs.size(); i++)
    {
        int cur = 0;
        vector<int> pair = pairs[i];
        for (int j = i + 1; j < pairs.size(); j++)
        {
            vector<int> pointer = pairs[j];
            if (pointer[0] > pair[1])
            {
                cur++;
                pair = pointer;
            }
        }
        
        res = max(res, cur);
    }
    
    return res;
}


// 670. Maximum Swap
int maximumSwap(int num)
{
    string s = to_string(num);
    int maxDigit = -1;
    int maxPos = -1;
    int swapPos = -1;
    for (int i = 0; i < s.size(); i++)
    {
        maxDigit = s[i];
        for (int j = i + 1; j < s.size(); j++)
        {
            if (s[j] >= maxDigit) // find the last index which could be greater than the firstmost one.
            {
                maxDigit = s[j];
                maxPos = j;
            }
        }
        
        if (maxDigit != s[i])
        {
            swapPos = i;
            break;
        }
    }
    
    // swap the index.
    if (swapPos != -1)
        swap(s[swapPos], s[maxPos]);
    
    return atoi(s.c_str());
}

int maximumSwap2(int num) {
    string s = to_string(num);
    priority_queue<int> q(s.begin(),s.end());
    
    string res;
    while (!q.empty())
    {
        res.append(to_string(q.top() - 48));
        q.pop();
    }
    
    return atoi(res.c_str());
}

// 663. Equal Tree Partition
bool checkEqualTree(TreeNode* root) {
    
    return false;
}

// 673. Number of Longest Increasing Subsequence
int findNumberOfLIS(vector<int>& nums) {
    int res = 0, mx = 0, n = nums.size();
    vector<int> len(n, 1), cnt(n, 1);
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < i; ++j) {
            if (nums[i] <= nums[j]) continue;
            std::cout << "i:" << i << ":" << nums[i] << "\n";
            std::cout << "j:" << j << ":" << nums[j] << "\n";
            if (len[i] == len[j] + 1) cnt[i] += cnt[j];
            else if (len[i] < len[j] + 1) {
                len[i] = len[j] + 1;
                cnt[i] = cnt[j];
            }
        }
        if (mx == len[i]) res += cnt[i];
        else if (mx < len[i]) {
            mx = len[i];
            res = cnt[i];
        }
    }
    return res;
}

// 300. Longest Increasing Subsequence
int lengthOfLIS(vector<int>& nums) {
    
    return 0;
}

// 674. Longest Continuous Increasing Subsequence
int findLengthOfLCIS(vector<int>& nums) {
    int res = 1;
    int counter = 1;
    
    for (int i = 0; i < nums.size() - 1; i++)
    {
        if (nums[i] < nums[i + 1])
        {
            counter++;
            res = max(counter, res);
        }
        else
        {
            counter = 1;
        }
    }
    
    return res;
}


// 694. Number of Distinct Islands
// number of islands

//
// 714. Best Time to Buy and Sell Stock with Transaction Fee
// DP, need to consider hold as well.

// 721. Accounts Merge
// Adjust matrix

// 725. Split Linked List in Parts
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(NULL) {}
};

vector<ListNode*> splitListToParts(ListNode* root, int k) {
    int count = 0;
    vector<ListNode*> res;
    ListNode *head = root; // copy or
    
    if (root == nullptr)
    {
        while (k-- > 0) {
            res.push_back(nullptr);
        }
        
        return res;
    }
    
    while (head != nullptr)
    {
        ++count;
        head = head->next;
    }
    head = root;
    
    if (count < k)
    {
        while (k > 0)
        {
            if (count > 0)
            {
                ListNode *temp = new ListNode(head->val);
                //                ListNode node(2);  // C++ object re-use?
                res.push_back(temp);
                head = head->next;
            }
            else
            {
                res.push_back(nullptr);
            }
            count--;
            k--;
        }
    }
    else
    {
        int firstPart = count / k + count - (count / k) * k;
        ListNode *root1 = new ListNode(0);
        root1->next = root;
        
        while (firstPart > 0)
        {
            firstPart--;
            root1 = root1->next;
        }
        head = root1->next; // validate
        root1->next = nullptr;
        res.push_back(root);
        
        while (head != nullptr)
        {
            ListNode *temp = head;
            int kCount = count / k;
            while (--kCount > 0)
            {
                temp = temp->next;
            }
            
            ListNode *next = temp->next;
            temp->next = nullptr;
            res.push_back(head);
            head = next;
        }
    }
    
    return res;
}


// whether target is anagram
bool isAnagrams(unordered_map<char, int> table, string target)
{
    int count = target.size();
    for (int i = 0; i < target.size(); i++)
    {
        if (table.count(target[i]) > 0)
        {
            table[target[i]]--;
            if (table[target[i]] < 0)
            {
                break;
            }
            
            count--;
        }
    }
    
    return count == 0;
}

vector<int> findAnagrams(string s, string p) {
    unordered_map<char, int> table;
    vector<int> indexes;
    for (int i = 0; i < p.size(); i++)
    {
        table[p[i]]++;
    }
    
    for (int i = 0; i <= s.size() - p.size(); i++)
    {
        if (isAnagrams(table, s.substr(i, p.size())))
        {
            indexes.push_back(i);
        }
    }
    
    return indexes;
}

/**
 *  Two pointers problem.
 */

// 3. Longest Substring Without Repeating Characters
int lengthOfLongestSubstring2(string s) {
    unordered_map<char, int> map;
    int i = 0, j = 0, result = 0;
    while (j < s.length()) {
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

int lengthOfLongestSubstringTwoDistinct(string s) {
    int res = 0, left = 0;
    unordered_map<char, int> m;
    for (int i = 0; i < s.size(); ++i) {
        ++m[s[i]];
        while (m.size() > 2) {
            if (--m[s[left]] == 0) m.erase(s[left]);
            ++left;
        }
        res = max(res, i - left + 1);
    }
    return res;
}

void printV(vector<int>& v) {
    std::cout << "{";
    for (auto i: v) {
        std::cout << i << ",";
    }
    std::cout << "}\n";
}

// key algorithm for quick sort, return the position of pivot
// arr[] = {10, 80, 30, 90, 40, 50, 70}
int partition(vector<int>& v, int low, int high) {
    int pivot = v[high];
    int left = low;
    int right = high - 1;
    
    while (left < right) {
        if (v[left] > pivot && v[right] < pivot) {
            swap(v[left++], v[right--]);
        }
        if (v[left] < pivot) left++;
        if (v[right] > pivot) right--;
        // debug
        printV(v);
    }
    
    swap(v[high], v[left]);
    return left;
}

void quicksort(vector<int>& v, int low, int high) {
    if (low < high) {
        int idx = partition(v, low, high);
        quicksort(v, low, idx -1);
        quicksort(v, idx + 1, high);
    }
}

/**
 *  DP problem.
 */

class abc
{
public:
    int a;
    std::string b;
};


ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
    ListNode *dummy = new ListNode(-1);
    ListNode *dummyHead = dummy;
    while (list1 != nullptr && list2 != nullptr) {
        if (list1->val <= list2->val) {
            dummy->next = new ListNode(list1->val);
            list1 = list1->next;
            
        } else {
            dummy->next = new ListNode(list2->val);
            list2 = list2->next;
        }
        
        dummy = dummy->next;
    }
    
    while (list1 != nullptr) {
        dummy->next = new ListNode(list1->val);
        dummy = dummy->next;
        list1 = list1->next;
    }
    
    while (list2 != nullptr){
        dummy->next = new ListNode(list2->val);
        dummy = dummy->next;
        list2 = list2->next;
    }
    
    return dummyHead->next;
}


// TODO: copy the node's value
// TODO: return a new node?
// TODO: verify it
void modifyList(ListNode* head, int nth) {
    int counter = 1;
    ListNode *iter = head;
    while (counter < nth - 1) {
        iter = iter->next;
        counter++;
    }
    // remove the node
    iter->next = iter->next->next;
}



vector<vector<int>> twoSum (const vector<int>& v, int target, int start) {
    int l = start;
    int r = v.size() - 1;
    vector<vector<int>> pairs;
    while (l < r) {
        int sum = v[l] + v[r];
        if (sum > target) {
            r--;
        } else if (sum < target) {
            l++;
            
        } else {  // equal
            vector<int> pair = {v[l], v[r]};
            pairs.push_back(pair);
            l++;
            r--;
        }
        
        while (v[r] == v[r + 1]) {
            r--;
        }
        while (v[l] == v[l - 1]) {
            l++;
        }
    }
    
    return pairs;
}


vector<int> dailyTemperatures(vector<int>& temperatures) {
    stack<pair<int, int>> prevTemp; // temp, index
    vector<int> res(temperatures.size(), 0); // default not initialized
    for (int i = 0; i < temperatures.size(); i++) {
        if (i == 0) {
            prevTemp.push(std::make_pair(temperatures[i], i));
        } else {
            while (prevTemp.size() > 0 && prevTemp.top().first < temperatures[i]) {
                pair<int, int> temp = prevTemp.top();
                res[temp.second] = i - temp.second;
                prevTemp.pop();
            }
            prevTemp.push(std::make_pair(temperatures[i], i));
        }
    }
    
    return res;
}

double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
    int l1 = nums1.size();
    int l2 = nums2.size();
    bool even = (l1 + l2) % 2 == 0;
    int mid = (l1 + l2) / 2;
    // num1 should always be the smaller one.
    if (l1 > l2) {
        nums1.swap(nums2);
    }
    
    int l = 0;
    int r = nums1.size() - 1;
    int m = 0;
    while (l < r) {
        m = l + (r - l) / 2;
        // merge part II;
        int m2 = mid - m - 1;
        // whether we should take more elements from array 1.
        if (nums1[m + 1] < nums2[m2 - 1]) {
            l = m + 1;
        } else if (nums2[m2] < nums1[m]) {
            r = m - 1;
        } else {
            break;   // check break condition
        }
    }
    
    //
    if (even) {
        
    } else {
        
    }
    
    return 0.0;
}

int lengthOfLongestSubstring3(string s) {
    unordered_set<char> characters;
    int length = 0;
    int l = 0;
    for (int r = 0; r < s.size(); r++) {
        while (characters.count(s[r])) {
            characters.erase(s[l++]);
        }
        characters.insert(s[r]);
        length = max(length, r - l + 1);
    }
    
    return length;
}


vector<vector<int>> threeSum(vector<int>& nums) {
    vector<vector<int>> res;
    // avoid to have duplicate numbers;
    std::sort(nums.begin(), nums.end());
    for (int i = 0; i < nums.size() - 2; i++) {
        if ( i > 0 && nums[i - 1] == nums[i]) { // skip duplicates
            continue;
        }
        vector<vector<int>> pairs = twoSum(nums, -nums[i], i + 1);
        if (pairs.size() > 0) {
            for (int j = 0; j < pairs.size(); j++) {
                vector<int> level;
                // append another vector into first.
                level.insert(level.end(), pairs[j].begin(), pairs[j].end());
                // level.push_back(pairs[);
                level.push_back(nums[i]);
                res.push_back(level);
            }
        }
    }
    
    return res;
}


bool checkInclusion2(string s1, string s2) {
    unordered_map<char, int> dict;
    for (auto c: s1) {
        dict[c]++;
    }
    
    int left = 0, right = 0;
    while (right < s2.size()) {
        unordered_map<char, int> copy = dict;
        while (copy.count(s2[right])) {
            copy[s2[right]]--;
            if (copy[s2[right]] == 0) {
                copy.erase(s2[right]);
            }
            if (copy.size() == 0 && right - left + 1 == s1.size()) {
                return true;
            }
            right++;
        }
        if (right > 0) { //
            left = right + 1;
            right = left;
        } else {
            left++;
            right = left;
        }
        
        //            left++;
        //            right = left;
    }
    
    return false;
}

bool sourceCharGreaterThanTargetChar(unordered_map<char, int> s, unordered_map<char, int> t) {
    for (auto kv: t) {
        if (!s.count(kv.first)) { // s doesn't have all the keys needed in target
            return false;
        } else {
            if (s[kv.first] < t[kv.first]) {
                return false;
            }
        }
    }
    
    return true;
}

// find the most appears of the element.
// 26 alphabets A -> Z
int mostFreqElement(const vector<int>& alpha) {
    int ret = 0;
    for (int i = 0; i < alpha.size(); i++) {
        ret = max(ret, alpha[i]);
    }
    std::cout << "most: " << ret << "\n";
    return ret;
}

int characterReplacement(string s, int k) {
    int res = 0;
    int l = 0;
    int r = 0;
    vector<int> alpha(26, 0);
    int windowSize = 0;
    while (r < s.size()) {
        do {
            alpha[s[r] - 'A']++;
            windowSize = r - l + 1;
            res = max(res, windowSize);
            r++;
        } while(r < s.size() && windowSize - mostFreqElement(alpha) <= k);
        // shift left pointer;
        alpha[s[l] - 'A']--;
        l++;
    }
    
    return res;
}




string minWindow(string s, string t) {
    unordered_map<char, int> tc = {};
    unordered_map<char, int> sc = {};
    for (auto c: t) {
        tc[c]++;
    }
    
    int l = 0;
    int r = 0;
    int res = INT_MAX;
    int leftIndex = 0;
    int rightIndex = 0;
    while (r < s.size()) {
        if (tc.count(s[r])) {
            sc[s[r]]++;
        }
        
        while (sourceCharGreaterThanTargetChar(sc, tc)) {
            if (res > r - l + 1) {
                res = r - l + 1;
                leftIndex = l;
                rightIndex = r;
            }
            
            if (sc.count(s[l])) {
                sc[s[l]]--;
            }
            
            l++;
        }
        
        r++;
    }
    
    return s.substr(leftIndex, rightIndex - leftIndex + 1);
}


ListNode *copyNode(ListNode *head) {
    ListNode *iter = head;
    unordered_map<ListNode*, ListNode *> map;
    
    // 1 - > 1'
    while (iter) {
        // visit iter->val;
        map[iter] = new ListNode(iter->val);
        iter = iter->next;
    }
    
    iter = head;
    while (iter && iter->next) {
        map[iter]->next = map[iter->next];
        iter = iter->next;
    }
    
    return map[head];
}

bool hasCycle(ListNode *head) {
    ListNode *slow = head;
    ListNode *fast = head;
    while (slow) {
        slow = slow->next;
        fast = fast->next->next;
        if (slow == fast) {
            return true;
        }
    }
    
    return false;
}

ListNode *sum(ListNode *l1, ListNode *l2) {
    ListNode *newHead = nullptr;
    ListNode *iter = nullptr;
    while (l1 && l2) {
        iter->next = new ListNode(l1->val + l2->val);
        iter = iter->next;
    }
    
    return newHead;
}


// [left, right] is valid range
bool validateBST(TreeNode *node, long left, long right) {
    // null node is absolutely a valid bst
    if (node == nullptr) {
        return true;
    }
    
    if (node->val <= left || node->val >= right) {
        return false;
    }
    
    bool leftValid = validateBST(node->left, left, node->val);
    bool rightValid = validateBST(node->right, node->val, right);
    
    return leftValid && rightValid;
}

bool isValidBST(TreeNode* root) {
    return validateBST(root, LONG_MIN, LONG_MAX);
}


int i = 0;

string serialize(TreeNode* root) {
    if (root == nullptr) {
        return "N";
    }
    string rootVal = to_string(root->val);
    string leftNode = serialize(root->left);
    string rightNode = serialize(root->right);
    
    return rootVal + "," + leftNode + "," + rightNode;
}

TreeNode *dfsT(const vector<string>& nodes) {
    string val = nodes[i];
    
    if (val == "N") {
        return nullptr;
    }
    
    TreeNode *root = new TreeNode(stoi(val));
    i++;
    root->left = dfsT(nodes);
    i++;
    root->right = dfsT(nodes);
    
    return root;
}

TreeNode* deserialize(string data) {
    vector<string> nodes;
    stringstream ss(data);
    string tmp;
    while (getline(ss, tmp, ',')) {
        nodes.push_back(tmp);
    }
    // debug
    //    for (auto c: nodes) {
    //        cout << c << ",";
    //    }
    
    TreeNode *res = dfsT(nodes);
    return res;
}

class Trie {
private:
    TrieNode *root;
public:
    Trie() {
        root = new TrieNode();
    }
    
    
    void insert(string word) {
        TrieNode *itor = root;
        for (auto c: word) {
            if (!itor->children.count(c)) {
                itor->children[c] = new TrieNode();
            }
            itor = itor->children[c];
        }
        // end of word.
        itor->wordEnded = true;
    }
    
    bool search(string word) {
        TrieNode *itor = root;
        for (int i = 0; i < word.size(); i++) {
            if (!itor->children.count(word[i])) {
                return false;
            }
            
            itor = itor->children[word[i]];
        }
        
        return itor->wordEnded;
    }
    
    bool startsWith(string prefix) {
        TrieNode *itor = root;
        
        for (int i = 0; i < prefix.size(); i++) {
            if (!itor->children.count(prefix[i])) {
                return false;
            }
            
            itor = itor->children[prefix[i]];
        }
        
        return true;
    }
    
};

ListNode *mergeList(ListNode *l1, ListNode *l2) {
    ListNode *itor1 = l1;
    ListNode *itor2 = l2;
    while (itor1) {
        ListNode *tmp1 = itor1->next;
        ListNode *tmp2 = itor2->next;
        itor1->next = itor2;
        itor2->next = tmp1;
        itor1 = tmp1;
        itor2 = tmp2;
    }
    
    return l1;
}

// reverse the list and return the new head node
ListNode *reverse(ListNode *head) {
    ListNode *cur = head;
    ListNode * pre = nullptr;
    while (cur) {
        ListNode *tmp = cur->next;
        cur->next = pre;
        pre = cur;
        cur = tmp;
    }
    
    return pre;
}

void reorderList(ListNode* head) {
    ListNode *dummy = new ListNode(-1);
    dummy->next = head;
    
    ListNode *slow = dummy;
    ListNode *fast = dummy;
    
    while (fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;
    }
    
    // at the end, slow -> next will be the second half head, draw a picture.
    // reverse the second half.
    ListNode * secondHalfHead = slow->next;
    slow->next = nullptr; // cut the first half with second
    ListNode * newSecondHalfHead = reverse(secondHalfHead);
    ListNode *newMergedList = mergeList(head, newSecondHalfHead);
    
    // return newMergedList;
}



ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
    ListNode *itor1 = l1;
    ListNode *itor2 = l2;
    int carry = 0;
    ListNode *dummy = new ListNode();
    ListNode *cur = dummy;
    
    while (itor1 && itor2) {
        int val = itor1->val + itor2->val + carry;
        carry = val / 10;
        int nVal = val % 10; // node val;
        cur->next = new ListNode(nVal);
        itor1 = itor1->next;
        itor2 = itor2->next;
        cur = cur->next;
    }
    
    if (itor1) {
        int val = itor1->val + carry;
        carry = val / 10;
        int nVal = val % 10;
        cur->next = new ListNode(nVal);
        itor1 = itor1->next;
        cur = cur->next;
    }
    
    if (itor2) {
        int val = itor2->val + carry;
        carry = val / 10;
        int nVal = val % 10;
        cur->next = new ListNode(nVal);
        itor2 = itor2->next;
        cur = cur->next;
    }
    
    return dummy->next;
}

// 26 characters, uses vector to store the freq of each char.
int maxFreq(string s, const vector<int>& freq) {
    int res = 0;
    for (int i = 0; i < 26; i++) {
        res = max(res, freq[i]);
    }
    cout << "res: " << res << "\n";
    return res;
}

int characterReplacement2(string s, int k) {
    int left = 0;
    int right = 0;
    int res = 0;
    vector<int> freq(26, 0);
    while (right < s.size()) {
        // window <= k
        while (right - left + 1 - maxFreq(s.substr(left, right - left + 1), freq) <= k) {
            res = max(res, right - left + 1);
            freq[s[right++] - 'A']++;
        }
        
        freq[s[left++] - 'A']--;
    }
    
    return res;
}


bool isAnagram(const vector<int>& v1, const vector<int>& v2) {
    for (int i = 0; i < 26; i++) {
        if (v1[i] != v2[i]) {
            return false;
        }
    }
    
    return true;
}

bool checkInclusion(string s1, string s2) {
    // two hashmap store
    int l1 = s1.size();
    int l2 = s2.size();
    if (l1 > l2) return false;
    
    vector<int> v1(26, 0);
    for (auto c: s1) {
        v1[c - 'a']++;
    }
    
    vector<int> v2(26, 0);
    
    int l = 0;
    int r = 0;
    while (r < s2.size()) {
        while (r - l + 1 <= l1) {
            v2[s2[r] - 'a']++;
            r++;
        }
        
        if (isAnagram(v1, v2)) {
            return true;
        }
        
        v2[s2[l] - 'a']--;
        l++;
    }
    
    return false;
}

bool isPalindrome(const string& s) {
    if (s.empty()) {
        return false;
    }
    
    int l = 0;
    int r = s.size() - 1;
    while (l < r) {
        if (s[l] != s[r]) {
            return false;
        }
        l++;
        r--;
    }
    
    return true;
}

void dfs(int start, string s, vector<string>& path, vector<vector<string>>& res) {
    if (start == s.size()) {
        if (path.size() > 0) {
            res.push_back(path);
        }
        
        return;
    }
    
    for (int i = start; i < s.size(); i++) {
        string s1 = s.substr(start, i - start + 1);
        if (!isPalindrome(s1)) {
            continue;
        }
        
        path.push_back(s1);
        dfs(i + 1, s, path, res);
        path.pop_back();
    }
}

vector<vector<string>> partition(string s) {
    vector<vector<string>> res;
    vector<string> path;
    dfs(0, s, path, res);
    
    return res;
}

void dfs(int depth, string digits, vector<string>& res, string cur, unordered_map<char, vector<char>>& dict) {
    if (depth == digits.size()) {
        res.push_back(cur);
        return;
    }
    
    //    for (int i = depth; i < digits.size(); i++) {
    char c = digits[depth];
    for (int j = 0; j < 3; j++) {
        auto a = dict[c][j];
        cur = cur + a;
        dfs(depth + 1, digits, res, cur, dict);
        cur.pop_back();
    }
    //    }
}

vector<string> letterCombinations(string digits) {
    unordered_map<char, vector<char>> dict = {{'2', {'a', 'b', 'c'}}, {'3', {'d', 'e', 'f'}}};
    vector<string> res;
    string cur;
    
    dfs(0, digits, res, cur, dict);
    
    return res;
}

bool dfs(vector<vector<char>>& board, string word, int i, int j, vector<vector<bool>> visited, int pos) {
    if (pos == word.size()) {
        return true;
    }
    
    //    if (path == word) {
    //        return true;
    //    }
    
    if (i < 0 || i >= board.size() || j < 0 || j >= board[0].size() || visited[i][j] || word[pos] != board[i][j]) {
        return false;
    }
    // extend to four directions if possible. up, down, left, right
    // within boundry
    visited[i][j] = true;
    //    if (board[i][j] == 'S') {
    //        cout << "log here";
    //    }
    bool up = dfs(board, word, i - 1, j, visited, pos + 1);
    bool down = dfs(board, word, i + 1, j, visited, pos + 1);
    bool left = dfs(board, word, i, j - 1, visited, pos + 1);
    bool right = dfs(board, word, i, j + 1, visited, pos + 1);
    visited[i][j] = false;
    
    return up || down || left || right;
}

bool exist(vector<vector<char>>& board, string word) {
    string path;
    // assume board is non empty.
    vector<vector<bool>> visited(board.size(), vector<bool>(board[0].size(), false));
    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board[0].size(); j++) {
            if (dfs(board, word, i, j, visited, 0)) {
                return true;
            }
        }
    }
    
    return false;
}

// similar to island problem
// fill row & col with valid Q position.
void dfs(vector<vector<string>>& board, int r, vector<string>& level, unordered_set<int>& cols, unordered_set<int>& rows, unordered_set<int>& nDiag, unordered_set<int>& pDiag, int n) {
    // if row reaches last row, which means we reach the bottom.
    if (r == n) {
        // if there's any then add to the results;
        board.push_back(level);
        
        return;
    }
    
    string curRow = string(n, '.');
    
    for (int j = 0; j < n; j++) {
        if (cols.count(j) || rows.count(r) || nDiag.count(r - j) || pDiag.count(r + j)) {
            continue;
        }
        
        cols.insert(j);
        rows.insert(r);
        nDiag.insert(r - j);
        pDiag.insert(r + j);
        curRow[j] = 'Q';
        level[r] = curRow;
        dfs(board, r + 1, level, cols, rows, nDiag, pDiag, n); // scan next row.
        cols.erase(j);
        rows.erase(r);
        nDiag.erase(r - j);
        pDiag.erase(r + j);
        curRow[j] = '.';
        level[r] = curRow; // backtrack, restore previous state.
    }
}

vector<vector<string>> solveNQueens(int n) {
    // generate the board first
    
    vector<vector<string>> board;
    //TODO: generate wrong answers board(n, vector<string>(n, "")); cause size of board keeps changing.
    unordered_set<int> cols;
    unordered_set<int> rows;
    unordered_set<int> nDiag;
    unordered_set<int> pDiag;
    
    string rowStr = string(board.size(), '.');
    vector<string> level(n, rowStr);
    dfs(board, 0, level, cols, rows, nDiag, pDiag, n);
    
    return board;
}


typedef pair<int, int> pts;
struct Compare {
    bool operator()(pts p1, pts p2) {
        int v1 = p1.first * p1.first + p1.second * p1.second;
        int v2 = p2.first * p2.first + p2.second * p2.second;
        return v1 <= v2;
    }
};


struct Tweet {
    int tweetId;
    int timestamp;
    // comparator:
    //    bool operator() (Tweet t1, Tweet t2) {
    //        return t1.timestamp <= t2.timestamp;
    //    }
    Tweet(int id, int ts): tweetId(id), timestamp(ts){};
    bool operator < (const Tweet& other) const {
        return this->timestamp < other.timestamp;
    }
    
    // minheap.
    bool operator > (const Tweet& other) const {
        return this->timestamp > other.timestamp;
    }
};

// TODO: why put inside will cause error?
//bool operator<(Tweet a, Tweet b) {
//    return a.timestamp < b.timestamp;
//}
    
void testGeneric() {
    int id = 1;
    int ts = 2;
    Tweet t1(id, ts);
    Tweet t2(id + 1, ts + 1);
    // minHeap, ascending order
    priority_queue<Tweet, vector<Tweet>, greater<Tweet>> q;
//    priority_queue<Tweet> q;

    vector<Tweet> v;
//    v.push_back(t);
    cout << v.size() << "\n";
     q.push(t1);
     q.push(t2);
     while (!q.empty()) {
        cout << q.top().timestamp << ",";
        q.pop();
     }
     
    cout << q.size() << "\n";
}


vector<vector<int>> kClosest(vector<vector<int>>& points, int k) {
    vector<vector<int>> res;
    priority_queue<pts, vector<pts>, Compare> q;
    
    for (auto p: points) {
        if (q.size() < k) {
            q.push(std::make_pair(p[0], p[1]));
        } else {
            // q equals k;
            int curVal = p[0] * p[0] + p[1] * p[1];
            int curTop = q.top().first * q.top().first + q.top().second * q.top().second;
            
            if (curVal < curTop) {
                q.pop();
                q.push(std::make_pair(p[0], p[1]));
            }
        }
        
    }
    
    while(!q.empty()) {
        pair<int, int> top = q.top();
        q.pop();
        vector<int> temp = {top.first, top.second};
        res.push_back(temp);
    }
    
    return res;
}


// return pth element, where left is less than p, right is larger than p.
int quickSelect(vector<int>& v, int l, int r, int k) {
    int piviot = v[r];
    int p = l;
    for (int i = l; i < r; i++) {
        if (v[i] < piviot) {
            swap(v[i], v[p]);
            p++;
        }
    }
    
    swap(v[p], v[r]);
    if (k > p) {
        return quickSelect(v, p + 1, r, k);
    } else if (k < p) {
        return quickSelect(v, l, p - 1, k);
    } else {
        return v[p];
    }
}


int findKthLargest2(vector<int>& nums, int k) {
    // index of biggest element.
    k = nums.size() - k;
    int res = quickSelect(nums, 0, nums.size() - 1, k);
    return 0;
}

// 621. Task Scheduler
 int leastInterval(vector<char>& tasks, int n) {
        priority_queue<int> pq;
        int time = 0;
        vector<int> characters(26, 0);
        // pair<tasks, time>
        queue<pair<int, int>> q;
        for (auto c: tasks) {
            characters[c - 'A']++;
        }

        for (auto c: characters) {
            if (c != 0) {
                pq.push(c);
            }
        }
        // max heap has the count;
        while (!pq.empty()) {
            int top = pq.top();
            time++;
            top = top - 1;
            pq.pop();
            if (top > 0) {
                q.push({top, time + n}); // until new time we can add it back to the max heap.
            }

            if (q.size() > 0 && time >= q.front().second) {
                pq.push(q.front().first);
                q.pop();
            }
        }

        return time;
    }


class MedianFinder {
private:
    priority_queue<int> pq;
public:
    MedianFinder() {
        
    }
    
    void addNum(int num) {
        pq.push(num);
    }
    
    double findMedian() {
        double res = 0;
        priority_queue<int> q = pq;
        int size = q.size();
        //even 1 2 3 4
        if (size % 2 == 0) {
            int midIndex = (size + 1) / 2 ;
            int preNum = 0;
            while (q.size() != midIndex) {
                preNum = q.top();
                q.pop();
            }
            res = double(q.top() + preNum) / 2;
        } else {
            int midIndex = size / 2;
            int preNum = 0;
            while (q.size() != midIndex) {
                preNum = q.top();
                q.pop();
            }

            res = preNum;
        }

        return res;
    }
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder* obj = new MedianFinder();
 * obj->addNum(num);
 * double param_2 = obj->findMedian();
 */
 

// C++ program to use priority_queue to implement Min Heap
// for user defined class
using namespace std;

// User defined class, Point
class Point
{
    int x;
    int y;
public:
    Point(int x, int y)
    {
        this->x = x;
        this->y = y;
    }
    int getX() const { return x; }
    int getY() const { return y; }
    
    bool operator < (const Point& p1) const {
        return x > p1.x;
    }
};

// To compare two points
//class myComparator
//{
//public:
//	int operator() (const Point& p1, const Point& p2)
//	{
//		return p1.getX() > p2.getX();
//	}
//};

// Driver code
//int main ()
//{
	// Creates a Min heap of points (order by x coordinate)
//	priority_queue <Point> pq;
//
//	// Insert points into the min heap
//	pq.push(Point(10, 2));
//	pq.push(Point(2, 1));
//	pq.push(Point(1, 5));
//
//	// One by one extract items from min heap
//	while (pq.empty() == false)
//	{
//		Point p = pq.top();
//		cout << "(" << p.getX() << ", " << p.getY() << ")";
//		cout << endl;
//		pq.pop();
//	}
//
//	return 0;
//}
void dfs(vector<vector<char>>& grid, int i, int j, vector<vector<bool>>& visited) {
    if (i >= grid.size() || i < 0 || j >= grid[0].size() || j < 0 || grid[i][j] == '0' || visited[i][j]) {
        return;
    }
    
    if (grid[i][j] == '1') {
        visited[i][j] = true;
    }
    // four direction
    
    dfs(grid, i + 0, j + 1, visited);
    dfs(grid, i + 0, j - 1, visited);
    dfs(grid, i + 1, j + 0, visited);
    dfs(grid, i - 1, j + 0, visited);
}

int numIslands(vector<vector<char>>& grid) {
    vector<vector<bool>> visited(grid.size(), vector<bool>(grid[0].size(), false));
    int count = 0;
    for (int i = 0; i < grid.size(); i++) {
        for (int j = 0; j < grid[0].size(); j++) {
            if (!visited[i][j] && grid[i][j] == '1') {
                count++;
                dfs(grid, i, j, visited);
            } // else continue;
        }
    }
    
    return count;
}

vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
    vector<int> res;
    unordered_set<int> visited;
    // adjust lists
    unordered_map<int, vector<int>> edges;
    
    for (int i = 0; i < numCourses; i++) {
        edges[i] = {};
    }
    
    for (auto v: prerequisites) {
        edges[v[0]].push_back(v[1]);
    }
    
    
    
    return res;
}

// find the parent of the node
int findR(vector<int>& par, int node) {
    int res = node;
    while (res != par[res]) {
        // TODO: without compression algo?
        // compression algo
        par[res] = par[par[res]];
        res = par[res];
    }
    
    return res;
}

int unionFind(int node1, int node2, vector<int>& rank, vector<int>& par) {
    int p1 = findR(par, node1);
    int p2 = findR(par, node2);
    
    // means already unioned
    if (p1 == p2) {
        return 0;
    }
    // parent always has larger rank
    if (rank[p1] > rank[p2]) {
        par[p2] = p1;
        rank[p1] += rank[p2];
    } else {
        par[p1] = p2;
        rank[p2] += rank[p1];
    }
    
    return 1;
}

bool validTree(int n, vector<vector<int>> &edges) {
    // write your code here
    // edges  vertices - 1
    if (n != edges.size() + 1) {
        return false;
    }
    
    // detect cycle.
    vector<int> rank(n, 1);
    vector<int> par(n, 0);
    for (int i = 0; i < n; i++) {
        // initialize parent as itself.
        par[i] = i;
    }
    
    // rank of root node must equal to edges
    for (auto e: edges) {
        unionFind(e[0], e[1], rank, par);
    }
    
    int leadRank = 0;
    for (auto c: rank) {
        leadRank = max(leadRank, c);
    }
    
    return leadRank == edges.size();
}

int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
    // build adjacence lists;
    unordered_map<string, vector<string>> edges;
    for (auto s: wordList) {
        for (int i = 0; i < s.size(); i++) {
            string pattern = s;
            pattern[i] = '*';
            edges[pattern].push_back(s);
        }
    }
    
    queue<string> q;
    unordered_set<string> visited;
    // use BFS to find the shortest distance.
    q.push(beginWord);
    visited.insert(beginWord);
    
    int res = 1;
    
    while (!q.empty()) {
        int len = q.size();
        for (int i = 0; i < len; i++) {
            string top = q.front();
            q.pop();
            if (top == endWord) {
                return res;
            }
            
            for (int j = 0; j < top.size(); j++) {
                string pattern = top;
                pattern[j] = '*';
                vector<string> neighbors = edges[pattern];
                for (auto v: neighbors) {
                    if (!visited.count(v)) {
                        q.push(v);
                        visited.insert(v);
                    }
                }
            }
        }
        
        res++;
    }
    
    return 0;
}
    
void testStrings() {
    vector<string> lists = {"hello", "world"};
    for (auto s: lists) {
        for (int i = 0; i < s.size(); i++) {
            string temp = s;
            temp[i] = '*';
            cout << s << ", " << temp << "\n";
        }
    }
}


bool dfs(string cur, vector<string>& path, unordered_map<string, vector<string>>& edges, vector<vector<string>>& tickets) {
    if (path.size() == 1 + tickets.size()) {
        return true;
    }
    
    // cannot construct iternary, no connected graph
    if (edges[cur].empty()) {
        return false;
    }
    
    vector<string> neigh = edges[cur];
    
    for (int i = 0; i < neigh.size(); i++) {
        // nextNode is the node to be deleted from the edges
        string nextNode = neigh[i];
        path.push_back(nextNode);
        edges[cur].erase(edges[cur].begin() + i);
        // remove the element from the edges.
        if (dfs(nextNode, path, edges, tickets)) {
            return true;
        }
        // add the element back to the edges.
        edges[cur].push_back(nextNode);
        // path not connected, we need to backtrack.
        path.pop_back();
    }
    
    return false;
}

vector<string> findItinerary(vector<vector<string>>& tickets) {
    // build adjuscency list;
    unordered_map<string, vector<string>> edges;
    
    for (auto v: tickets) {
        edges[v[0]].push_back(v[1]);
    }
    
    for (auto i: edges) {
        sort(edges[i.first].begin(), edges[i.first].end());
    }
    
    vector<string> res;
    // unordered_set<string> visited;
    // always starts from "JFK"
    res.push_back("JFK");
    dfs("JFK", res, edges, tickets);
    
    return res;
}


int minCostConnectPoints(vector<vector<int>>& points) {
   // visited to keep the track of nodes;
        unordered_set<int> visited;
        unordered_map<int, vector<pair<int, int>>> edges;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int distance = abs(points[i][0] - points[j][0]) + abs(points[i][1] - points[j][1]);
                
                edges[i].push_back({distance, j});
                edges[j].push_back({distance, i});
            }
        }

        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        // build adjusence lists;
        // calculate distance between each of the nodes?
        // starts from the first node;
        pq.push({0, 0});
        int res = 0;
        while (visited.size() < points.size()) {
            // distance, nodeid
            pair<int, int> cur = pq.top();
            pq.pop();
            if (visited.count(cur.second)) {
                continue;
            }

            visited.insert(cur.second);
            cout << "visit: " << cur.second << ", " << cur.first << "\n";
            res += cur.first;

            for (auto neigh: edges[cur.second]) {
                if (!visited.count(neigh.second)) {
                    pq.push({neigh.first, neigh.second});
                }
            }
        }

        return res;
}

int networkDelayTime(vector<vector<int>>& times, int n, int k) {
    // build adjacency lists
    unordered_map<int, vector<pair<int, int>>> edges;
    
    for (auto c: times) {
        edges[c[0]].push_back({c[1], c[2]});
    }
    
    queue<int> q;
    q.push(k);
    int cost = 0;
    unordered_set<int> visited;
    visited.insert(k);
    while (!q.empty()) {
        int size = q.size();
        int maxCost = 0;
        for (int i = 0; i < size; i++) {
            int top = q.front();
            q.pop();
            for (auto p: edges[top]) {
                if (!visited.count(p.first)) {
                    cout << ">>>>: " << p.first << "\n";
                    q.push(p.first);
                    visited.insert(p.first);
                    maxCost = max(maxCost, p.second);
                } else {
                    // detect circle
                    break;
                }
                
            }
        }
        // find max cost for each level;
        cost += maxCost;
    }
    
    return visited.size() == n ? cost: -1;
}

vector<vector<int>> insert(vector<vector<int>>& intervals, vector<int>& newInterval) {
    vector<vector<int>> res;
    
    for (int i = 0; i < intervals.size(); i++) {
        vector<int> cur = intervals[i];
        // left || cur || right;
        if (newInterval[1] < cur[0]) {
            res.push_back(newInterval);
            copy(intervals.begin() + i, intervals.end(), back_inserter(res));
            return res;
        } else if (cur[1] < newInterval[0]) {
            res.push_back(cur); // right, we shouldn't add newIntervals yet;
        } else {
            newInterval = {min(cur[0], newInterval[0]), max(cur[1], newInterval[1])};
        }
    }
    
    res.push_back(newInterval);
    
    return res;
}

int eraseOverlapIntervals(vector<vector<int>>& intervals) {
    // keep track of the lastEnd value,
    sort(intervals.begin(), intervals.end(), [](const vector<int>& v1, const vector<int>& v2){
        return v1[0] < v2[0]; // ascending ordere
    });
    int res = 0;
    int lastEnd = intervals[0][1];
    for (int i = 1; i < intervals.size(); i++) {
        if (intervals[i][0] < lastEnd) {
            res++;
            // remove the one with larger end value,
            // since it has more chances of overlapping.
            lastEnd = min(lastEnd, intervals[i][1]);
        } else {
            lastEnd = intervals[i][1];
        }
    }
    
    return res;
}
    
struct Interval2 {
    int start;
    int end;
    Interval2(int start, int end): start(start), end(end){}
};

int minMeetingRooms(vector<Interval2> &intervals) {
    // Write your code here
    int endTime = intervals[0].end;
    int res = 0;
    for (int i = 1; i < intervals.size(); i++) {
        if (intervals[i].start < endTime) {
            res++;
            endTime = min(endTime, intervals[i].end);
        } else {
            endTime = intervals[i].end;
        }
    }
    
    return res;
}

int minMeetingRooms2(vector<Interval2> &intervals) {
    // Write your code here
    
    sort(intervals.begin(), intervals.end(), [](const Interval2& v1, const Interval2& v2) {
        // ascending order
        return v1.start < v2.start;
    });
    
    vector<int> startArray;
    vector<int> endArray;
    
    int res = 0;
    for (int i = 0; i < intervals.size(); i++) {
        startArray.push_back(intervals[i].start);
        endArray.push_back(intervals[i].end);
    }
    
    // sort end
    sort(endArray.begin(), endArray.end());
    int j = 0; // endArray
    int meetingRoom = 0;
    for (int i = 0; i < intervals.size(); i++) {
        if (startArray[i] < endArray[j]) {
            meetingRoom++; // need more meeting room, since the meeting not ended
            res = max(res, meetingRoom);
        } else {
            j++;
            meetingRoom--;
        }
    }
    
    return res;
}

bool dfsA(char c, unordered_map<char, bool>& visit, vector<char>& res, unordered_map<char, unordered_set<char>>& edges) {
    if (visit.count(c)) {
        return visit[c];
    }
    
    // starts of the dfs path
    visit[c] = true;
    for (auto i: edges[c]) {
        if (dfsA(i, visit, res, edges)) {
            return true;
        }
    }
    
    visit[c] = false;
    res.push_back(c);
    
    return false;
}


string alienOrder2(vector<string> &words) {
    // Write your code here
    
    // build adjacency list
    unordered_map<char, unordered_set<char>> edges;
    
    for (int i = 1; i < words.size(); i++) {
        string w1 = words[i - 1];
        string w2 = words[i];
        int len = min(w1.size(), w2.size());
        for (int j = 0; j < len; j++) {
            if (w1[j] != w2[j]) {
                edges[w1[j]].insert(w2[j]);
                break;
            } else {
                edges[w1[j]] = {};
            }
        }
    }
    
    vector<char> res;
    // 26 alphabet?
    unordered_map<char, bool> visit;
    for (auto i: edges) {
        cout << "i: " << i.first << "\n";
        if (dfsA(i.first, visit, res, edges)) {
            return "";
        }
    }
    
    string seq;
    for (int i = res.size() - 1; i >=0; i--) {
        seq += res[i];
    }
    
    return seq;
}

struct MyClass {
    int age;
    string name;
    MyClass(int a, string n): age(a), name(n){}
};


bool isNStraightHand(vector<int>& hand, int groupSize) {
    int size = hand.size();
    
    if (size % groupSize != 0) {
        return false;
    }
    
    unordered_map<int, int> m;
    for (auto i: hand) {
        m[i]++;
    }
    // min heap, for each group, we should only starts
    // with smallest value possible
    priority_queue<int, vector<int>, greater<int>> pq;
    for (auto pair: m) {
        pq.push(pair.first);
    }
    
    int numberOfGroups = hand.size() / groupSize;
    while (!pq.empty()) {
        for (int i = 0; i < numberOfGroups; i++) {
            int j = 0; // counter
            int startValue = 0;
            while (j < groupSize) {
                if (j == 0) {
                    startValue = pq.top();
                }
                
                if (!m.count(startValue) || m[startValue] == 0) {
                    return false;
                }
                
                m[startValue]--;
                if (m[startValue] == 0) {
                    pq.pop();
                }
                
                j++;
                startValue++;
            }
        }
    }
    
    return true;
}

struct Entity
{
    int val;
    Entity(int val): val(val){};
    void changeVal(int val) {
        this->val = val;
    }
    void printVal() {
        cout << "val: " << this->val << "\n";
    }
    ~Entity() {cout << "Destructor called on " << this << endl;}
};

int rob(vector<int>& nums) {
    int len = nums.size();
    vector<int> most(len + 1, 0);
    for (int i = 2; i < len; i++) {
        nums[i] = max(nums[i - 2] + nums[i], nums[i - 1]);
    }
    
    return nums.back();
}


string longestPalindrome(string s) {
    // loop each character
    // extend from center to check whether it's palidrome.
    int l = 0;
    int r = 0;
    int resLen = 0;
    string res;
    
    for (int i = 0; i < s.size(); i++) {
        l = i;
        r = i;
        while (l >= 0 && r < s.size() && s[l] == s[r]) {
            if (r - l + 1 > resLen) {
                resLen = r - l + 1;
                res = s.substr(l, r - l + 1);
            }
            
            // time complexity
            l--;
            r++;
        }
        
        l = i;
        r = i + 1;
        while (l >= 0 && r < s.size() && s[l] == s[r]) {
            if (r - l + 1 > resLen) {
                resLen = r - l + 1;
                res = s.substr(l, r - l + 1);
            }
            
            // time complexity
            l--;
            r++;
        }
    }
    
    return res;
}


int countSubstrings(string s) {
    vector<string> res;
    int l, r = 0;
    for (int i = 0; i < s.size(); i++) {
//        string temp = temp + s[i];
//        res.push_back(temp);
        
        l = i;
        r = i;
        while (l >= 0 && r < s.size() && s[l] == s[r]) {
            string p = s.substr(l, r - l + 1);
            res.push_back(p);
            l--;
            r++;
        }
        // handle even number of elements
        l = i;
        r = i + 1;
         while (l >= 0 && r < s.size() && s[l] == s[r]) {
            string p = s.substr(l, r - l + 1);
            res.push_back(p);
            l--;
            r++;
        }
    }
    
    
    return res.size();
}
    
// method starts with test is just driver code
void testBreak() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 2; j++) {
            if (j == 0) {
                continue;
            } else if (j == 1) {
                break;
            }
            cout << "inner loop\n";
        }
        
        cout << "outter loop\n";
    }
}

void testSharedPtr() {
    Entity *ptr = new Entity(5);
    shared_ptr<Entity> sptr_1(ptr);
    cout << sptr_1.use_count() << "\n";
    shared_ptr<Entity> sptr_2 = sptr_1;
    sptr_2->changeVal(2);
    sptr_1->printVal();
    weak_ptr<Entity> wptr_1 = sptr_2;
    cout << wptr_1.use_count() << "\n";
    std::shared_ptr<Entity> new_shared = wptr_1.lock();
    cout << sptr_2.use_count() << "\n";
}


int coinChange(vector<int>& coins, int amount) {
    vector<int> dp(amount + 1, amount + 1);
    dp[0] = 0;
    
    for (int i = 1; i <= amount; i++) {
        for (int j = 0; j < coins.size(); j++) {
            if (i - coins[j] >= 0) {
                dp[i] = min(dp[i], 1+ dp[i - coins[j]]);
            }
        }
    }
    
    return dp[amount] == amount + 1 ? -1 : dp[amount];
}

int maxProduct(vector<int>& nums) {
    // maxCur, minCur, n, res;
    int maxCur = 1;
    int minCur = 1;
    int res = INT_MIN;
    
    for (auto i: nums) {
        // if (i == 0) {
        //     maxCur = 1;
        //     minCur = 1;
        //     continue;
        // }
        int temp = maxCur * i;
        maxCur = max(max(maxCur * i, minCur * i), i);
        minCur = min(min(temp, minCur * i), i);
        res = max(res, maxCur);
    }
    
    return res;
}

bool wordBreak(string s, vector<string>& wordDict) {
    vector<bool> dp(s.size() + 1, 0);
    dp[s.size()] = true; // base case
    // bottom up, from bottom
    for (int i = s.size() - 1; i >= 0; i--) {
        for (auto w: wordDict) {
            if (s.substr(i, w.size()) == w) {
                // if dp[i] been validated before, we shouldn't override it.
                dp[i] = dp[i] || dp[i + w.size()];
            }
        }
    }
    
    return dp[0];
}

bool dfs(string& s, int i, vector<string>& wordDict, vector<bool>& cache) {
    if (cache[i]) {
        return cache[i];
    }
    
    for (auto w: wordDict) {
        if (s.substr(i, w.size()) == w) {
            if (dfs(s, i + w.size(), wordDict, cache)) {
                return true;
            }
        }
    }
    
    cache[i] = false;
    
    return false;
}

// TODO: validate the dfs solution of cache.
bool wordBreak2(string s, vector<string>& wordDict) {
    unordered_set<string> dict;
    vector<bool> cache(s.size() + 1, false); // reaches last char
    cache[s.size()] = true;
    for (auto w: wordDict) {
        dict.insert(w);
    }
    
    return dfs(s, 0, wordDict, cache);
}

int lengthOfLIS2(vector<int>& nums) {
    vector<int> dp(nums.size(), 1);
    int res = 1;
    for (int i = nums.size() - 1; i >= 0; i--) {
        for (int j = i; j < nums.size(); j++) {
            if (nums[i] < nums[j]) {
                dp[i] = max(dp[i], dp[j] + 1);
                res = max(res, dp[i]);
            }
        }
    }
    
    return res;
}


bool canPartition(vector<int>& nums) {
    int sum = 0;
    for (auto i: nums) {
        sum += i;
    }
    
    if (sum % 2 != 0) {
        return false;
    }
    
    int target = sum / 2;
    
    unordered_set<int> values; // possible values from decision tree, pick or not pick
    values.insert(0);
    for (int i = nums.size() - 1; i >= 0; i--) {
        vector<int> temp(values.begin(), values.end());
        for (int j = 0; j < temp.size(); j++) {
            int value = nums[i] + temp[j]; // all possible values, by saying pick or not pick.
            if (value == target) {
                return true;
            }
            
            values.insert(value);
        }
    }
    
    return false;
}

// return how manys ways can we compose at index i
int dfs(string& s, int i, vector<int>& dp) {
    if (dp[i]) {
        return dp[i];
    }
    
    if (s[i] == '0') {
        return 0;
    }
    
    int res = dfs(s, i + 1, dp);
    if (i + 1 < s.size() && (s[i] == '1' || (s[i] == '2' && s[i + 1] <= '6')) ) {
        res += dfs(s, i + 2, dp);
    }
    
    dp[i] = res;
    
    return res;
}

int numDecodings(string s) {
    vector<int> dp(s.size() + 1, 0);
    dp[s.size() + 1] = 1; // base case
    int res = dfs(s, 0, dp);
    
    return res;
//    return dfs(s, 0, dp);
}

void testItrator() {
    unordered_set<int> v;
    
    v.insert(1);
    v.insert(5);
    unordered_set<int> copy = v;
    
    for (auto i: v) {
        copy.insert(i + 3);
        cout << i << "\n";
    }
    
    for (auto i: copy) {
        cout << i << "\n";
    }
    
    cout << copy.size() << "\n";
}

// TODO: use template

void printMap(unordered_map<char, int>&map) {
    for (auto c: map) {
        cout << c.first << ", " << c.second << "\n";
    }
}

vector<int> partitionLabels(string s) {
    // record each character's last position
    unordered_map<char, int> positions;
    for (int i = 0; i < s.size(); i++) {
        positions[s[i]] = i;
    }
    printMap(positions);
    
    vector<int> res;
    int maxRight = 0;
    int left = 0;
    for (int i = 0; i < s.size(); i++) {
        int p = positions[s[i]];
        if (p > maxRight) {
            maxRight = p;
        }
        
        if (i == maxRight) {
            res.push_back(maxRight - left + 1);
            left = maxRight + 1; // if left and right at the same position, size should be 1
        }
    }
    
    return res;
}

bool dfsJump(vector<int>& nums, int i, vector<int>& cache) {
    if (i == nums.size() - 1) {// reaches last element
        return true;
    }
    
    if (cache[i] != -1) {
        return cache[i];
    }
    
    // starts from i, maximum can jump
    for (int j = 1; j <= nums[i]; j++) {
        if (dfsJump(nums, i + j, cache)) {
            return true;
        }
    }
     
    cache[i] = false;
    return false;
}

bool canJump(vector<int>& nums) {
    vector<int> dp(nums.size(), -1);
    
    return dfsJump(nums, 0, dp);
}


void testRemoveElementWhileiterate() {
    
}

void testMapErase() {
    unordered_map<string, string> unorderMap;
    map<string, string> orderedMap;
    
    string key = "France";
    unorderMap[key] = "pairs";
    orderedMap[key] = "china";
    orderedMap.erase(key);
    
    unorderMap.erase(key);
    if (orderedMap.count(key)) {
        cout << "has?" << orderedMap.size();
    } else {
        cout << "has!: " << orderedMap.size();
    }
    
    if (unorderMap.count(key)) {
        cout << "erase? " << unorderMap.size();
    } else {
        cout << "erase!" << unorderMap.size();
    }
//    cout << map[key] << "\n";
}


bool isNStraightHand2(vector<int>& hand, int groupSize) {
    if (hand.size() % groupSize != 0) {
        return false;
    }
    
    map<int, int> m;
    for (auto i: hand) {
        m[i]++;
    }
    
    while (!m.empty()) {
        int key = m.begin()->first; // least element.
        for (int i = 0; i < groupSize; i++) {
            if (!m.count(key + i)) {
                return false;
            } else {
                m[key + i]--;
                if (m[key + i] == 0) {
                    m.erase(key + i); // remove element if reaches 0.
                }
            }
        }
    }
    
    return true;
}


int coinChange2(vector<int>& coins, int amount) {
    // bottom up solution:
    // thought process, desicision tree,
    // for each level of tree, loop our choices(coins[i]), amount - coin[i],
    // until we reach the bottom level, amount = 0;
    // if we do bottom up, then we starts with dp[0], which requires 0 coin
    // dp[i] means, the minimum amount coins we need to reach the value i;
    vector<int> dp(amount + 1, INT_MAX);
    dp[0] = 0;
    for (auto c: coins) {
        dp[c] = 1; // initialize count for each coin to reach amount c;
    }
    
    for (int i = 0; i <= amount; i++) {
        for (auto c: coins) {
            if (i - c >= 0) {
                dp[i] = min(dp[i - c] + 1, dp[i]);
            }
        }
    }
    
    return dp[amount];
}

int maxSubArray(vector<int>& nums) {
    // f[i] indicates the maximum at index i;
    if (nums.size() == 1) {
        return nums[0];
    }
    
    vector<int> f(nums.size(), 0);
    f[0] = nums[0];
    for (int i = 1; i < nums.size(); i++) {
        f[i] = max(f[i - 1] + nums[i], nums[i]);
//        if (f[i] < 0) { // we don't need the negative feedback
//            f[i] = 0;
//        }
    }
    
    return f[nums.size() - 1];
}


int maxProduct2(vector<int>& nums) {
    int maxP = 1;
    int minP = 1;
    int res = nums[0]; // if only contains 1 element, or uses INT_MIN;
    
    for (auto i: nums) {
        int temp = maxP;
        maxP = max(i, max(maxP * i, minP * i));
        minP = min(i, min(temp * i, minP * i));
        res = max(res, maxP);
    }
    
    return maxP;
}

int uniquePaths(int m, int n) {
    // two dimention array
    vector<vector<int>> dp(m, vector<int>(n, 1));
//    for (int i = 0; i < m; i++) {
//        for (int j = 0; j < n; j++) {
//            cout << dp[i][j] << ",";
//        }
//        cout << "\n";
//    }
    // last column & last row always be 1, since it only can move down and right.
    
    for (int j = n - 2; j >= 0; j--) {
        for (int i = m - 2; i >= 0; i--) {
            dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
        }
    }
    
    return dp[0][0];
}

// #1 uses decision tree for brutal force.
// return how many ways can we reach to amount
// #2 uses cache to profile performance
//
int dfs(int start, int amount, const vector<int>& coins, vector<int>& cache) {
    if (amount < 0) {
        return 0;
    }
    
     if (cache[amount] != -1) {
        return cache[amount];
    }
    
     if (amount == 0) {
         return 1;
     }
    
    int res = 0;
    // start to avoid the duplicates
    for (int i = start; i < coins.size(); i++) {
        if (dfs(i, amount - coins[i], coins, cache)) {
            res += dfs(i, amount - coins[i], coins, cache);
        }
    }
    
    cache[amount] = res;
    return res;
}


int change(int amount, vector<int>& coins) {
    vector<int> cache(amount + 1,  -1);
    dfs(0, amount, coins, cache);
    
    return cache[amount];
}

typedef pair<int, int> Position;
struct hashFunction
{
  size_t operator()(const Position &x) const
  {
    return x.first * 32 +  x.second;
  }
};

bool dfs(unordered_map<Position, bool, hashFunction>& m, int i, int j, string s1, string s2, string s3) {
    // return last position of both strings.
    if (i == s1.size() && j == s2.size()) {
        return true;
    }
    
    if (m.count(std::make_pair(i, j))) {
        return m[{i, j}];
    }
    
    if (i < s1.size() && s1[i] == s3[i + j] && dfs(m, i + 1, j, s1, s2, s3)) {
        return true;
    }
    
    if (j < s2.size() && s2[j] == s3[i + j] && dfs(m, i, j + 1, s1, s2, s3)) {
        return true;
    }
    
    m[{i, j}] = false;
    return false;
}


bool isInterleave(string s1, string s2, string s3) {
    unordered_map<Position, bool, hashFunction> cache;
    
    return dfs(cache, 0, 0, s1, s2, s3);
}

struct pairHash {
    size_t operator() (const pair<int, int> p) const{
        return p.first * 31 + p.second;
    }
};

// return 1 if we find the sum equals to target
int dfs(int i, int target, vector<int>& nums, int sum, unordered_map<pair<int, int>, int, pairHash>& dp) {
    if (i == nums.size()) {
        return 1 ? sum == target : 0;
    }
    
    if (dp.count({i, sum})) {
        return dp[{i, sum}];
    }
    
    dp[{i, sum}] = dfs(i + 1, target, nums, sum + nums[i], dp) + dfs(i + 1, target, nums, sum - nums[i], dp);
    
    return dp[{i, sum}];
}


int findTargetSumWays(vector<int>& nums, int target) {
    unordered_map<pair<int, int>, int, pairHash> dp;
    
    dfs(0, target, nums, 0, dp);
    cout << dp[{0, target}];
    
    return 0;
}

// return max value within l, r boundary
int dfs(int l, int r, vector<int>& nums, unordered_map<pair<int, int>, int, pairHash>& dp) {
    if (l > r) {
        return 0;
    }
    
    if (dp.count({l, r})) {
        return dp[{l, r}];
    }
    
    dp[{l, r}] = 0;
    for (int i = l; i <= r; i++) {
        int coins = nums[l - 1] * nums[i] * nums[r + 1];
        dp[{l, r}] = max(dp[{l, r}], dfs(l, i - 1, nums, dp) + dfs(i + 1, r, nums, dp) + coins);
    }
    
    return dp[{l, r}];
}

int maxCoins(vector<int>& nums) {
    // dp solution with l, r boundary
    // besides, for each element at i, we're poping it last.
    unordered_map<pair<int, int>, int, pairHash> dp;
    nums.push_back(1);
    vector<int> v = {1};
    v.insert(v.begin() + 1, nums.begin(), nums.end());
    
    return dfs(1, v.size() - 2, v, dp);
}


// if both i, j reaches
bool dfs(int i, int j, string s, string p) {
    if (i == s.size() && j == p.size()) {
        return true;    // we reaches to both end of string
    }
    
    // j reaches end, while i still has character left, means we don't have a match
    if (j == p.size()) {
        return false;
    }
    
    bool match = (s[i] == p[j] || p[j] == '.');
    
    if (match) {
        if (j + 1 < p.size() && p[j + 1] == '*') {
            // we use the '*'
            bool a1 = dfs(i, j + 2, s, p);
            // or we don't use it
            bool a2 = dfs(i + 1, j, s, p);
            return a1 or a2;
        }
    }
    
    return false;
}

// return minimum distance between two string.
// cache pair i, j, if we have it just return
int dfs(int i, int j, string word1, string word2, unordered_map<Position, int, hashFunction>& cache) {
    if(cache.count({i, j})) {
        return cache[{i, j}];
    }
    
    if (i == word1.size() && j == word2.size()) {
        return 0;
    }
    
    if (i == word1.size()) {
        return word2.size() - j;
    }
    
    if (j == word2.size()) {
        return word1.size() - i;
    }
    
    int distance = 0;
    if (word1[i] == word2[j]) {
        distance = dfs(i + 1, j + 1, word1, word2, cache);
    } else {
        // cases if we need replace, delete, insert
        distance = 1 + min(dfs(i + 1, j + 1, word1, word2, cache), min(dfs(i + 1, j, word1, word2, cache), dfs(i, j + 1, word1, word2, cache)));
    }
    
    cache[{i, j}] = distance;
    
    return distance;
}

int minDistance(string word1, string word2) {
    word1 = "acd";
    word2 = "abd";
    unordered_map<Position, int, hashFunction> cache;
    
    return dfs(0, 0, word1, word2, cache);
}

// return number of ways to form t string
int dfsDistinct(int i, int j, string& s, string& t, unordered_map<pair<int, int>, int, pairHash>& cache) {
    if (j == t.size()) {
        return 1;
    }
    if (i == s.size()) {
        return 0;
    }
    if (cache.count({i, j})) {
        return cache[{i, j}];
    }
    
    int res = 0;
    
    if (s[i] == t[j]) {
        // pick next or not pick
        res = dfsDistinct(i + 1, j + 1, s, t, cache) + dfsDistinct(i + 1, j, s, t, cache);
    } else {
        // skip current s
        res = dfsDistinct(i + 1, j, s, t, cache);
    }
    cache[{i, j}] = res;
    
    return res;
}

int numDistinct2(string s, string t) {
    unordered_map<pair<int, int>, int, pairHash> cache;
    return dfsDistinct(0, 0, s, t, cache);
}
    
bool isMatch(string s, string p) {
    return dfs(0, 0, s, p);
}

int numDistinct(string s, string t) {
    int m = s.size();
    int n = t.size();
    vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
    // base case, if s non empty, t empty, then
    // there's at least 1 way to form t.
    for (int i = 0; i <= m; i++) {
        // last column
        dp[i][n] = 1;
    }
    
    for (int i = m - 1; i >=0; i--) {
        for (int j = n - 1; j >= 0; j--) {
            if (s[i] == t[j]) {
                // if equal, then there'll be two cases:
                // 1. we use i, then we proceed to i + 1
                // 2. we don't use i, we use i + 1;
                dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
            } else {
                // not equal, then we'll have to proceed i to i + 1;
                dp[i][j] = dp[i + 1][j];
            }
        }
    }
    
    return dp[0][0];
}

int dfs(int i, int j, vector<vector<int>>& matrix, vector<vector<int>>& dp, int prev) {
    if (i == matrix.size() || j == matrix[0].size() || i < 0 || j < 0) {
        return 0;
    }
    
    if (matrix[i][j] <= prev ) {
        return 0;
    }
    
    if (dp[i][j] != -1) {
        return dp[i][j];
    }
    // within bound && increasing
    // four directions
    int res = 1;
    
    int up = 1 + dfs(i + 1, j, matrix, dp, matrix[i][j]);
    res = max(up, res);
    int down = 1+ dfs(i - 1, j, matrix, dp, matrix[i][j]);
    res = max(down, res);
    int left = 1 + dfs(i, j + 1, matrix, dp, matrix[i][j]);
    res = max(res, left);
    int right = 1+ dfs(i, j - 1, matrix, dp, matrix[i][j]);
    res = max(right, res);
    dp[i][j] = res;
    
    return dp[i][j];
}

int longestIncreasingPath(vector<vector<int>>& matrix) {
    // dp[i, j] stores each value of max path
    int res = 0;
    vector<vector<int>> dp(matrix.size() + 1, vector<int>(matrix[0].size() + 1, -1));
    for (int i = 0; i < matrix.size(); i++) {
        for (int j = 0; j < matrix[0].size(); j++) {
            dfs(i, j, matrix, dp, -1);
        }
    }
    
    for (int i = 0; i < matrix.size(); i++) {
        for (int j = 0; j < matrix[0].size(); j++) {
            res = max(res, dp[i][j]);
        }
    }
    
    return res;
}

bool isInterleaveDP(string s1, string s2, string s3) {
    if (s1.size() + s2.size() != s3.size()) {
        return false;
    }
    
    vector<vector<bool>> dp(s1.size() + 1, vector<bool>(s2.size() + 1, 0));
    dp[s1.size()][s2.size()] = true; // base case for each string reaches to the end;
    //
    for (int i = s1.size() - 1; i >= 0; i--) {
        if (s1[i] == s3[i + s2.size() ]) {
            dp[i][s2.size()] = true;
        }
    }
    
    for (int j = s2.size() - 1; j >= 0; j--) {
        if (s2[j] == s3[s1.size() + j]) {
            dp[s1.size()][j] = true;
        }
    }
    
    for (int i = s1.size() - 1; i >= 0; i--) {
        for (int j = s2.size() - 1; j >= 0; j--) {
            if (s1[i] == s3[i + j]) {
                dp[i][j] = dp[i + 1][j];
            } else if (s2[j] == s3[i + j]) {
                dp[i][j] = dp[i][j + 1];
            }
        }
    }
    
    return dp[0][0];
}

int hammingWeight(uint32_t n) {
    int count = 0;
    while ( n & 1 || n != 0 ) {
        count++;
        n = n >> 1;
    }
    
    return count;
}

int getSum(int a, int b) {
    // iterate through each 32 bits and XOR each, if both are 1, then we need
    //
    int res = 0;
    int carry = 0;
    for (int i = 0; i <= 31; i++) {
        int t1 = 1 & (a >> i);
        int t2 = 1 & (b >> i);
        if (t1 == 1 && t2 == 1) {
            carry = 1;
        } else {
            carry = 0;
        }
        int value = t1 ^ t2 ^ carry;
        if (carry) {
            value = value << (i + 1);
        } else {
            value = value << i;
        }
        
        res = res | value;
    }
    
    return res;
}

int reverse(int x) {
    bool sign = false;
    if (x < 0) {
        sign = true;
        x = -x;
    }
    // always positive num;
    int res = 0;
    
    while (x) {
        int b = x % 10;
        x = x / 10;
        res = res * 10 + b;
        
    }
    
    return res;
}

struct vector_comp
{
    inline bool operator() (const vector<int>& v1, const vector<int>& v2)
    {
        if (v1[0] == v2[0]) {
            return v1[1] <= v2[1];
        }
        
        return v1[0] < v2[0];
    }
};

void printVv(const vector<vector<int>>& v) {
    for (auto a: v) {
        cout << a[0] << ", " << a[1] << ", ";
    }
}

struct Interval {
    int start;
    int end;
    Interval(int startTime, int endTime): start(startTime), end(endTime){
    
    }
};


int minMeetingRooms(vector<Interval> &intervals) {
    // Write your code here
    vector<int> startTime;
    vector<int> endTime;
    
    for (int i = 0; i < intervals.size(); i++) {
        startTime.push_back(intervals[i].start);
        endTime.push_back(intervals[i].end);
    }
    
    std::sort(startTime.begin(), startTime.end());
    std::sort(endTime.begin(), endTime.end());
    int i = 0;
    int j = 0;
    int res = 0;
    int room = 0;
    // start time must finishes first
    while (i < intervals.size()) {
        if (startTime[i] < endTime[j]) {
            room++;
            i++;
        } else {
            j++;
            room--;
        }
        std::cout << startTime[i] << ", " << endTime[j] << ", " << room << "\n";
        res = max(res, room);
    }
    
    return res;
}
    
struct pairType {
	int distance;
	int rightVal;
	pairType(int dis, int right): distance(dis), rightVal(right) {}

	bool operator < (const pairType& other) const {
		// distance asending order
        if (this->distance == other.distance) {
            return this->rightVal < other.rightVal;
        }
        
		return this->distance < other.distance;
	}
	
    // minheap
    // TODO: figure out which should we use
	bool operator > (const pairType& other) const {
		if (this->distance == other.distance) {
            return this->rightVal > other.rightVal;
		}

		return this->distance > other.distance;
	}
};

vector<int> minInterval(vector<vector<int>>& intervals, vector<int>& queries) {
    // queries needs to be sorted.
    unordered_map<int, int> map;
    vector<int> res;
    // rising order
    std::sort(intervals.begin(), intervals.end(), [](const vector<int>& v1, const vector<int>& v2){
        if (v1[0] == v2[0]) {
            return v1[1] < v2[1];
        }
            
        return v1[0] < v2[0];
    });
    
    vector<int> q = queries;
    std::sort(queries.begin(), queries.end());
    
    priority_queue<pairType, vector<pairType>, greater<pairType>> pq;
    
    int j = 0;
    for (int i = 0; i < queries.size(); i++) {
        while (j < intervals.size() && queries[i] >= intervals[j][0]) {
            pairType p(intervals[j][1] - intervals[j][0] + 1, intervals[j][1]);
            pq.push(p);
            j++;
        }
        
        while (!pq.empty()) {
            while (pq.top().rightVal < queries[i]) { // too far
                pq.pop();
            }
            
            map[queries[i]] = pq.empty() ? -1 : pq.top().distance;
//            if (!pq.empty()) {
//                map[queries[i]] = pq.top().distance;
////                res.push_back(pq.top().distance);
//            } else {
//                // no valid value
////                res.push_back(-1);
//                map[queries[i]] = -1;
//            }
        }
        
        for (auto i: q) {
            res.push_back(map[i]);
        }
    }
    
    return res;
}

struct Event
{
    int priority{};
    char data{' '};
 
    friend bool operator<(Event const& lhs, Event const& rhs)
    {
        return lhs.priority < rhs.priority;
    }
 
    friend std::ostream& operator<<(std::ostream& os, Event const& e)
    {
        return os << '{' << e.priority << ", '" << e.data << "'}";
    }
    
    bool operator >(const Event& other) const
    {
        return this->priority > other.priority;
    }
};

/*
 * returns the indices of the pivot
 */
int partition2(vector<int>& nums, int low, int high) {
    int pivot = nums[high];
    int pivotIndex = low; // destination index
    for (int i = low; i < high; i++) {
        if (nums[i] < pivot) {
            swap(nums[i], nums[pivotIndex]);
            pivotIndex++;
        }
    }
    
    swap(nums[high], nums[pivotIndex]);
    
    return pivotIndex;
}

int findKthLargest3(vector<int>& nums, int k) {
    // quick sort's partition algorithm
    // specify a pivot, and partition returns the indexs of
    // the pivot, so that the left are less than the pivot and
    // the right are larger.
    // if size - k == partition index, then we should return.
    int idx = partition2(nums, 0, nums.size() - 1);
    while (idx != nums.size() - k) {
        if (idx < nums.size() - k) {
            idx = partition2(nums, idx + 1, nums.size() - 1);
        } else {
            idx = partition2(nums, 0, idx - 1);
        }
    }
    
    return nums[idx];
    // if (idx == nums.size() - k) {
    //     return nums[idx];
    // } else {
    //     if (idx < nums.size() - k) {
    //         idx = partition(nums, idx + 1, nums.size() - 1);
    //     } else {
    //         idx = partition(nums, 0, idx - 1);
    //     }
    // }
    
    // return nums[idx];
}

void testSwap(vector<int>& v) {
    int l = 0;
    int left = v[l];
    int h = v.size() - 1;
    swap(left, v[h]);
    printV(v);
}
//int main()
//{
 
    // for (auto const e : {Event{6,'L'}, {8,'I'}, {9,'S'}, {1,'T'}, {5,'E'}, {3,'N'}})
    // {
    //     std::cout << e << ' ';
    //     events.push(e);
    // }
 
    // std::cout << "\nProcess events:\t\t";
 
    // for (; !events.empty(); events.pop())
    // {
    //     Event const& e = events.top();
    //     std::cout << e << ' ';
    // }
 
    // std::cout << '\n';
//}


class Twitter2 {
struct Tweet2 {
    int tweetId;
    int timestamp; // used by pq to sort the most 10 recents.
    Tweet2(int id, int ts): tweetId(id), timestamp(ts) {}
    bool operator < (const Tweet2& other) const{  // pq needs this to sort the input.
        return this->timestamp < other.timestamp; // max heap
    }
};

private:
    int ts;
    unordered_map<int, unordered_set<int>> followMap;
    unordered_map<int, vector<Tweet2>> userTweets;

public:
    Twitter2() {
        ts = 0;
    }
    
    void postTweet(int userId, int tweetId) {
        Tweet2 t(tweetId, ts++);
        userTweets[userId].push_back(t);
    }
    
    vector<int> getNewsFeed(int userId) {
        // self posts;
        vector<Tweet2> selfPosts = userTweets[userId];
        // other posts;
        unordered_set<int> followedUsers = followMap[userId];
        for (auto u: followedUsers) {
            vector<Tweet2> otherTweets = userTweets[u];
            selfPosts.insert(selfPosts.end(), otherTweets.begin(), otherTweets.end());
        }

        priority_queue<Tweet2> tweets(selfPosts.begin(), selfPosts.end());
        // most recent 10;
        vector<int> res;
        while (!tweets.empty() && res.size() <= 10) {
            Tweet2 top = tweets.top();
            res.push_back(top.tweetId);
            tweets.pop();
        }

        return res;
    }
    
    void follow(int followerId, int followeeId) {
        followMap[followerId].insert(followeeId);
    }
    
    void unfollow(int followerId, int followeeId) {
        followMap[followerId].erase(followeeId);
    }
};

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter* obj = new Twitter();
 * obj->postTweet(userId,tweetId);
 * vector<int> param_2 = obj->getNewsFeed(userId);
 * obj->follow(followerId,followeeId);
 * obj->unfollow(followerId,followeeId);
 */
 
int main(int argc, const char * argv[]) {
    vector<int> tswap = {1, 1, 2};
    testSwap(tswap);

    vector<int> quickSelect = {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
    cout << findKthLargest3(quickSelect, 20);
    
    std::priority_queue<Event, std::vector<Event>, std::greater<Event>> events;
    Event e1{6, 'L'};
    Event e2{8, 'I'};
    
    events.push(e1);
    events.push(e2);
    
    std::cout << "Fill the events queue:\t";
 

    // driver code for pairType;
    pairType pt1(2, 5);
    pairType pt2(3, 4);
    pairType pt3(3, 5);
//    priority_queue<pairType, vector<pairType>, greater<pairType>> ptPQ;
    priority_queue<pairType> ptPQ;
    
    ptPQ.push(pt1);
    ptPQ.push(pt2);
    ptPQ.push(pt3);
    
    while(!ptPQ.empty()) {
        auto v = ptPQ.top();
        cout << v.distance << ", " << v.rightVal;
        cout << "\n";
        ptPQ.pop();
    }
    
    vector<vector<int>> vcomp = {{1, 3}, {1, 2}, {2, 4}};
    std::sort(vcomp.begin(), vcomp.end(), vector_comp());
    
    printVv(vcomp);
    
    cout << reverse(123);
    
    cout << getSum(20, 30) << "\n";
    
    cout << hammingWeight(11) << ", " << hammingWeight(2);
    
    vector<vector<int>> longIncreasing = {{1, 2}};
    cout << longestIncreasingPath(longIncreasing);
    
   cout << numDistinct("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
   
   cout << "dis: " << minDistance("", "");
   string a1 = "aa";
   string a2 = "a*";
   cout << isMatch(a1, a2);
   
   vector<int> maxCoin = {1, 5};
   cout << maxCoins(maxCoin);
   
   vector<int> targetSums = {1, 1, 1, 1, 1};
   cout << findTargetSumWays(targetSums, 3);
   
    std::cout << isInterleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa", "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab", "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab");
    
    cout << uniquePaths(3, 3) << "\n";
    
    vector<int> maxP = {2,3,-2,4};
    cout << maxProduct2(maxP) << "\n";
    
    vector<int> subarry = {-2,1,-3,4,-1,2,1,-5,4};
    cout << maxSubArray(subarry) << "\n";
    
    vector<int> coins = {1, 2};
    // change is actually coin change II challenge;
    cout << change(3, coins);
    cout << coinChange2(coins, 11);
    
    vector<int> handS = {8, 10, 12};
    cout << isNStraightHand2(handS, 3);
    testMapErase();
    vector<int> canJ = {3, 2, 1, 0, 4};
    cout << canJump(canJ);
    
    string partLabel = "eaaaabaaec";
    cout << partitionLabels(partLabel).size();
    
    string numDec = "06";
    cout << numDecodings(numDec);
    
    vector<int> canP = {1, 5, 11, 5};
    cout << canPartition(canP);
    
    testItrator();
    vector<int> LIS = {10,9,2,5,3,7,101,18};
    cout << lengthOfLIS2(LIS);
    
    string wordB = "abcd";
    wordB = "leetcode";
    vector<string> wordArr = {"a", "abc", "b", "cd"};
    wordArr = {"leet", "code"};
    
    cout << wordBreak2(wordB, wordArr);
    
    vector<int> maxProductSubArray = {-4, -3, -2};
    cout << maxProduct(maxProductSubArray);
    
    vector<int> coinC = {1, 2, 5};
    cout << coinChange(coinC, 11);
    
    string psub = "aaa";
    cout << countSubstrings(psub);
    
    string strLongestP = "babad";
    cout << longestPalindrome(strLongestP);
    
    testSharedPtr();
    // [2,1,1,2]
    vector<int> houses = {2, 1, 1, 2};
    cout << rob(houses);
    
    testBreak();
    
    std::shared_ptr<MyClass> me(new MyClass(1, "Jian"));
    {
        std::shared_ptr<MyClass> mRef = me;
        std::cout << me.use_count() << "\n";
    }
    
    vector<int> straights = {1, 1, 3, 6, 2, 3, 4, 7, 8};
    cout << isNStraightHand(straights, 3);
    
    vector<string> aWords = {"wrt","wrf","er","ett","rftt"};
    aWords = {"zy", "zx"};
    cout << alienOrder2(aWords);
    
    std::cout << me.use_count() << "\n";
    vector<Interval> intervals;
    Interval i1(0, 30);
    Interval i2(5, 10);
    Interval i3(15, 20);
    
    intervals.push_back(i1);
    intervals.push_back(i2);
    intervals.push_back(i3);
    cout << minMeetingRooms(intervals);
    
//    cout << minMeetingRooms2(intervals);
    
    sort(intervals.begin(), intervals.end(), [](const Interval& v1, const Interval& v2){
        return v1.start < v2.start; // ascending order
    });
    
    cout << minMeetingRooms(intervals);
    
    vector<vector<int>> overlap = {{1, 100}, {11, 22}, {1, 11}, {2, 12}};
    cout << eraseOverlapIntervals(overlap);
    
    vector<vector<int>> originV = {{1, 3}, {6, 9}};
    vector<int> nInterval = {2, 5};
    cout << insert(originV, nInterval).size();

    vector<vector<int>> network = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
    cout << networkDelayTime(network, 4, 2);
    
    vector<vector<int>> points1584 = {{0,0},{2,2},{3,10},{5,2},{7,0}};
    points1584 = {{3,12},{-2,5},{-4,1}};
    
    cout << minCostConnectPoints(points1584);
    
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> mst;
    mst.push({3, 4});
    mst.push({1, 5});
    mst.push({2, 6});
    // should be minheap.
    while (!mst.empty()) {
        pair<int, int> p = mst.top();
        cout << p.first << ", " << p.second;
        mst.pop();
    }
    
    
    vector<vector<string>> iternary = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
    cout << findItinerary(iternary).size();
    
    vector<string> dict = {"a", "b", "c"};
    cout << ladderLength("a", "c", dict);
    testStrings();
//    std::unordered_set<std::pair<int, int>> notGTW;
    vector<vector<int>> graphTree = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
    cout << validTree(5, graphTree);
    
    vector<vector<char>> grid = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}};
    cout << numIslands(grid);
    
    priority_queue<int, vector<int>, greater<int>> rightHalf;
    priority_queue <Point> pqPoints;

	// Insert points into the min heap
	pqPoints.push(Point(10, 2));
	pqPoints.push(Point(2, 1));
	pqPoints.push(Point(1, 5));
    
    while (!pqPoints.empty())
	{
		Point p = pqPoints.top();
		cout << "(" << p.getX() << ", " << p.getY() << ")";
		cout << endl;
		pqPoints.pop();
   }
    
    
    MedianFinder mf;
    mf.addNum(1);
    mf.addNum(2);
    cout << mf.findMedian() << "\n";
    
    testGeneric();
    
    vector<char> lin = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C'};
    cout << leastInterval(lin, 1);
    
    vector<int> vvv = {3, 1, 2};
    vector<int> quickS = {2, 1, 3, 6, 5, 4};
    findKthLargest2(quickS, 2);
    
    cout << quickS.size() << "\n";
    
    priority_queue<int> pq(vvv.begin(), vvv.end());
    
    vector<vector<int>> points = {{-5, 4},{-6, -5}, {4, 6}};
    vector<vector<int>> kk = kClosest(points, 2);
    
    for (auto i: kk) {
        cout << i[0] << i[1] << ",";
    }
    
    auto sq = solveNQueens(4);
    
    vector<string> ll = letterCombinations("23");
    vector<vector<char>> board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
    board = {{'A','B','C'},{'D','E','F'}};
    board = {{'b','b','a','a','b','a'}, {'b','b','a','b','a','a'}, {'b','b','b','b','b','b'},{'a','a','a','b','a','a'}, {'a','b','a','a','b','b'}};
    
    
    cout << exist(board, "abbbababaa");
    
    cout << "\n";
    
    auto c = partition("aab");
    
    cout << checkInclusion("ab", "eidbaooo");
    
    cout << characterReplacement2("AABABBA", 1);
    
    ListNode *addL1 = new ListNode(9);
    addL1->next = new ListNode(9);
    addL1->next->next = new ListNode(9);
    addL1->next->next->next = new ListNode(9);
    addL1->next->next->next->next = new ListNode(9);
    
    ListNode *addL2 = new ListNode(9);
    addL2->next = new ListNode(9);
    
    ListNode *sumHead = addTwoNumbers(addL1, addL2);
    
    
    ListNode *ln = new ListNode(1);
    ln->next = new ListNode(2);
    ln->next->next = new ListNode(3);
    ln->next->next->next = new ListNode(4);
    reorderList(ln);
    
    
    Trie tr;
    
    tr.insert("abc");
    tr.startsWith("abcd");
    
    cout << tr.search("abc");
    
    unordered_map<TreeNode *, TreeNode *> m;
    
    unordered_map<TrieNode *, TrieNode *> tireMap;
    
    TreeNode *root = new TreeNode(1);
    m[root] = new TreeNode(2);
    
    cout << m[root]->val << "\n";
    
    root->left = new TreeNode(2);
    root->right = new TreeNode(3);
    //    root->right->right = new TreeNode(5);
    //    root->right->left = new TreeNode(4);
    string ssT = serialize(root);
    TreeNode *dsN = deserialize(ssT);
    
    //    cout << serialize(root);
    
    cout << isValidBST(root);
    
    ListNode *head = new ListNode(1);
    head->next = new ListNode(2);
    head->next->next = new ListNode(3);
    ListNode *copyHead = copyNode(head);
    while (copyHead) {
        cout << copyHead->val << ", ";
        copyHead = copyHead->next;
    }
    
    cout << characterReplacement("AABABBA", 1);
    
    cout << minWindow("ADOBECODEBANC", "ABC");
    
    
    cout << checkInclusion("ab", "eidbaooo");
    
    cout << lengthOfLongestSubstring3("abcabcbb");
    vector<int> num1 {1, 2, 3, 4};
    vector<int> num2 {1, 2, 3, 4, 5, 6};
    
    int rr = findMedianSortedArrays(num1, num2);
    vector<int> temper {30, 40, 50, 60};
    vector<int> tempGreater = dailyTemperatures(temper);
    
    // [-2,0,0,2,2] // [1,2,-2,-1] // [-1,0,1,2,-1,-4,-2,-3,3,0,4]
    vector<int> vv {-1,0,1,2,-1,-4,-2,-3,3,0,4};
    threeSum(vv);
    
    ListNode *t1 = new ListNode(-9);
    t1->next = new ListNode(3);
    
    ListNode *t2 = new ListNode(5);
    t2->next = new ListNode(7);
    t2->next->next = new ListNode(9);
    modifyList(t2, 2);
    
    
    ListNode *mergedList = mergeTwoLists(t1, t2);
    
    
    vector<int> qs = {10, 80, 30, 90, 40, 50, 70};
    
    for (auto i: qs) {
        std::cout << i << "\n";
    }
    
    
    int idx = partition(qs, 0, qs.size() - 1);
    
    
    string longestsubstring = "abcabcbb";
    //    int len = lengthOfLongestSubstring(longestsubstring);
    
    std::vector<abc> vb;
    abc a;
    a.a = 10;
    a.b = "10";
    vb.push_back(a);
    a.a = 20;
    std::cout << vb[0].a << std::endl; // copy of const reference. C++ 11 introduce std::move. https://stackoverflow.com/questions/2275076/is-stdvector-copying-the-objects-with-a-push-back
    std::cout << a.a << std::endl;
    
    string source = "cbaebabacd";
    string t = "abc";
    vector<int> pos = findAnagrams(source, t);
    
    
    // insert code here...
    //    std::cout << "Hello, World!\n";
    vector<string> words = {"foo", "bar"};
    //    vector<int> res1 = findSubstring("barfoothefoobarman", words);
    vector<int> res2 = findSubstring("foofoothefoobarman", words);
    vector<int> row = getRow(3);
    int up = 0, down = 3, left = 0, right = 3;
    vector<int> loop;
    //    vector<vector<int>> vec(m, vector<int> (n, 0));
    vector<string> full = {"What","must","be","acknowledgment","shall","be"};
    
    vector<string> res = fullJustify(full, 16);
    
    vector<vector<int>> matrix(3, vector<int> (3, 0));
    int value = 1;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            matrix[i][j] = value++;
        }
    }
    int div = divide(10, 2);
    std::cout << "div:" << div << std::endl;
    std::cout << "matrix:" << std::endl;
    string s;
    s.append(2, 'a');
    std::cout << "s: " << s << std::endl;
    
    while (true) {
        for (int i = 0; i < 3; i++) loop.push_back(i);
        //        {
        //            std::cout << "i:" << i << std::endl;
        //            loop.push_back(i);
        //
        //        }
        if (loop.size() > 2) break;
        
        std::cout << "up:" << up << std::endl;
        if (++up > down) break;
    }
    
    // 0 and a, a and 0, both gcd are a.
    //    int g = gcd(8, 0);
    
    int k = 2;
    if (--k == 0)
    {
        std::cout << "k " << std::endl;
    }
    
    std::cout << "k updated: " << k << std::endl;
    //    std::cout << "Maximum: GCD" << g << std::endl;
    string h1 = "  hello  world!  ";
    reverseWords(h1);
    
    string h2 = "hello world";
    reverseManual(0, h2.size() - 1, h2);
    
    string rev2 = reverseWordsII(h2);
    
    std::cout << "reverse: " << h1 << "\n";
    vector<int> findK = {3, 2, 1, 5, 6, 4};
    int kth = findKthLargest(findK, 2);
    vector<int> kNearest = {0, 0, 1, 2, 3, 3, 4, 7, 7, 8};
    vector<int> r = findClosestElements(kNearest, 3, 5);
    //{5,7,7,8,8,10}
    vector<int> range = {5,7,7,8,8,10};
    range = {};
    searchRange(range, 0);
    // unordered_map
    using namespace std;
    unordered_map<string, int> map;
    
    vector<int> arr = {1,2,3,3,3};
    int cRes = combinationSum4(arr, 10);
    std::cout << "Combination: " << cRes << "\n";
    
    Solution* obj = new Solution(arr);
    int param_1 = obj->pick(3);
    
    // 9193 will fail.
    int maxi = maximumSwap(9973);
    
    std::cout << "Max:" << maxi << "\n";
    
    std::string test_swap = "1234";
    swap(test_swap[0], test_swap[3]);
    std::cout << "Swap: " << test_swap << "\n";
    
    vector<int> v = {2,5,3};
    int l = findNumberOfLIS(v);
    std::cout << "lis:" << l << "\n";
    
    ListNode headNode(1);
    //    ListNode node1(2);
    //    ListNode node2(3);
    //    ListNode node3(4);
    //    ListNode node4(5);
    //    ListNode node5(6);
    //    ListNode node6(7);
    //    ListNode node7(8);
    //    ListNode node8(9);
    //    ListNode node9(10);
    
    //    root.next = &node1;
    //    root.next->next = &node2;
    //    root.next->next->next = &node3;
    //    root.next->next->next->next = &node4;
    //    root.next->next->next->next->next = &node5;
    //    root.next->next->next->next->next->next = &node6;
    //    root.next->next->next->next->next->next->next = &node7;
    //    root.next->next->next->next->next->next->next->next = &node8;
    //    root.next->next->next->next->next->next->next->next->next = &node9;
    
    splitListToParts(&headNode, 3);
    int i = 3;
    int j = 3 + (i < 3);
    std::cout << "j:" << j << "\n";
    
    unordered_map<char, int> table;
    
    // initialize frequency table for t
    for(char c : t){
        table[c]++;
    }
    
    string sam = "eceba";
    lengthOfLongestSubstringTwoDistinct(sam);
    
    
    generateTrees(3);
    return 0;
}
