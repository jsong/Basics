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

// 244. Shortest Word Distance II


// 245. Shortest Word Distance III, words1 and words2 are allow to be same.
int shortestWordDistanceIII(vector<string>& words, string word1, string word2) {
    return 0;
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

    return 0;
}
