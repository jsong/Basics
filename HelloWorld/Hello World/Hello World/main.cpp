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


using namespace std;

vector<int> findSubstring(string s, vector<string>& words) {
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

int gcd(int m, int n)
{
    return n == 0? m: gcd(n, m % n);
}

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

// 621. Task Scheduler
int leastInterval(vector<char>& tasks, int n) {
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

// 673. Number of Longest Increasing Subsequence
int findNumberOfLIS(vector<int>& nums) {
    return 0;
}

int main(int argc, const char * argv[]) {
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
    int g = gcd(8, 0);
    
    int k = 2;
    if (--k == 0)
    {
        std::cout << "k " << std::endl;
    }
    
    std::cout << "k updated: " << k << std::endl;
    std::cout << "Maximum: GCD" << g << std::endl;
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
    return 0;
}
