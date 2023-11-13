#include<stack>
#include<iostream>
#include<vector>
#include<cmath>
#include<map>
#include<unordered_map>
#include <string>     // std::string, std::stoi

using namespace std;

class MinStack {
private:
    std::stack<int> all;
    std::stack<int> min;

public:
    MinStack() {

    }
    
    void push(int val) {
        all.push(val);
        if ( min.size() == 0 || (min.size() > 0 && min.top() >= val) ) {
            min.push(val);
        }
    }
    
    void pop() {
        if (all.top() == min.top()) {
            all.pop();
            min.pop();
        } else {
            all.pop();
        }
    }
    
    int top() {
        return all.top();
    }
    
    int getMin() {
        return min.top();
    }
};

 int operator2(int a, int b, string op) {
        int res = 0;
        char o = op[0];
        switch(o) {
        case '+':
            res = a + b;
            break;
        case '-':
            res = a - b;
            break;
        case '*':
            res = a * b;
            break;
        case '/':
            res = a / b;
            break;
        }

        return res;
    }

  int evalRPN(vector<string>& tokens) {
        stack<int> results;
        for (auto c: tokens) {
            // numbers in stack
            if (c >= "0" && c <= "9") {
                results.push(stoi(c));
            } else if (c == "+" || c == "-" || c == "*" || c == "/") {
                int op1 = results.top();
                results.pop();
                int op2 = results.top();
                results.pop();
                int res = operator2(op2, op1, c);
                results.push(res);
            }
        }

        return results.top();
    }


 int largestRectangleArea(vector<int>& heights) {
        // stack stores index, height pair
        stack<pair<int, int>> s;
        int maxArea = 0;
        for (int i = 0; i < heights.size(); i++) {
            if (s.size() == 0 || heights[i] >= s.top().second) {
                s.push(make_pair(i, heights[i]));
            } else {
                // cannot extends, need to calculate the previous area.
                int start = 0;
                while (s.size() > 0 && heights[i] < s.top().second) {
                    maxArea = max(maxArea, s.top().second * (i - s.top().first));
                    start = s.top().first;
                    s.pop();
                }

                s.push(make_pair(start, heights[i]));
            }
        }
        
        int length = heights.size();
        while (s.size() != 0) {
            // extends all the way to the end.
            maxArea = max(maxArea, s.top().second * (length - s.top().first));
            s.pop();
        }

        return maxArea;
    }
    
 bool searchMatrix(vector<vector<int>>& matrix, int target) {
        // each row is sorted &
        // each column is sorted as well
       int m = matrix.size();
        int n = matrix[0].size();
        // first use binary search on the row, then column
        int top = 0;
        int bottom = m - 1;
        while (top <= bottom) {
            int mid = top + (bottom - top) / 2;
            // target larger than last element in the row.
            if (matrix[mid][n - 1] < target) {
                top = mid + 1;
            } else if (matrix[mid][0] > target){
                bottom = mid - 1;
            } else {
                // found row
                break;
            }
        }

        if (top > bottom) {
            // nothing found
            return false;
        }

        // uses binary search for the current column;
        
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[top][mid] < target) {
                left = mid + 1;
            } else if (matrix[top][mid] > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }
    
     int etaCost(vector<int>& piles, int speed) {
        int time = 0;
        for (int i = 0; i < piles.size(); i++) {
            time += (piles[i] + speed - 1) / speed; // fast ceiling in c++;
            // std::ceil(float(piles[i]) / speed);
            // time = time + temp;
        }

        return time;
    }
    
    int minEatingSpeed(vector<int>& piles, int h) {
        sort(piles.begin(), piles.end());
        int lower = 1;
        int upper = piles.back();
        while (lower < upper) {
            int mid = lower + (upper - lower) / 2, cnt = 0;
//            for (int pile : piles) {
//                cnt += (pile + mid - 1) / mid;
//            }
            int cost = etaCost(piles, mid);
            if (cost > h) {
                lower = mid + 1;
            }  else {
                upper = mid;
            }
        }
        
        return upper;
    }
    
     int search(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                if (target < nums[left]) {
                    left = mid + 1;
                } else if (target > nums[left]){
                    right = mid;
                } else {
                    return mid;
                }
            } else if (target < nums[mid]) {
               if (target >= nums[left]) {
                   right = mid;
               } else {
                   left = mid + 1;
               }
            } else {
                return mid;
            }
        }

        return -1;
    }

 int minEatingSpeed2(vector<int>& piles, int H) {
        int left = 1, right = 1e9;
        while (left < right) {
            int mid = left + (right - left) / 2, cnt = 0;
            for (int pile : piles) cnt += (pile + mid - 1) / mid;
            if (cnt > H) left = mid + 1;
            else right = mid;
        }
        return right;
    }
    
class TimeMap {
private:
        std::map<string, vector<pair<int, string>>> m;
public:
    TimeMap() {
    }
    
    void set(string key, string value, int timestamp) {
        // if (m.count(key)) {
            m[key].push_back(std::make_pair(timestamp, value));
        // } else {
            // m[key]
        // }
    }
    
    void printDebug(const vector<pair<int, string>> v) {
        for (auto a: v) {
            cout << a.first << ", " << a.second << "\n";
        }
    }

    string get(string key, int timestamp) {
        if (m.count(key)) {
            vector<pair<int, string>> values = m[key];
            sort(values.begin(), values.end());
            // printDebug(values);

            int l = 0, r = values.size() - 1;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (values[mid].first < timestamp) {
                    l = mid + 1;
                } else if (values[mid].first > timestamp) {
                    r = mid - 1;
                } else {
                    return values[mid].second;
                }
            }
            // l should equals r here
            if (values[l].first <= timestamp) {
                return values[l].second;
            } else {
                return "";
            }

        } else {
            return "";
        }
    }
};


bool isAnagram(string s, string t) {
        unordered_map<char, int> m;
        for (auto a: s) {
            m[a]++;
        }

        for (auto a: t) {
            if (!m.count(a)) {
                return false;
            } else {
                m[a]--;
                if (m[a] == 0) {
                    m.erase(a);
                }
            }
        }

        return m.size() == 0;
    }


vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> m;
        vector<int> res;

        for (auto i: nums) {
            m[i]++; // num, count;
        }

        vector<pair<int, int>> v;
        for (auto p: m) {
            v.push_back(make_pair(p.first, p.second));
        }

        sort(v.begin(), v.end(), [](pair<int, int>a, pair<int, int>b){
            return a.second > b.second;  // descending order
        });

        for (int i = 0; i < k; i++) {
            res.push_back(v[i].first);
        }

        return res;
    }

 string encode(vector<string> &strs) {
        // write your code here
        string res;
        
        for (auto s: strs) {
            string temp = to_string(s.size()) + "#" + s;
            res += temp;
        }

        return res;
    }

vector<string> decode(string &str) {
    vector<string> res;
    int i = 0;
    while (i < str.size()) {
        int j = i; // find the delimiter;
        while (str[j] != '#') {
            j++;
        }
        int len = stoi(str.substr(i, j - i));
        string temp = str.substr(j + 1, len); // starts after '#'
        res.push_back(temp);
        i = j + len + 1;
    }
    
    return res;
}



// double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
//        // uses binary search on the array.
//        int m = nums1.size();
//        int n = nums2.size();
//        if (m > n) {
//            nums1.swap(nums2);
//        }
//        // nums1 should always be smaller.
//        int midSize = (m + n) / 2;
//        int l = 0, r = m - 1;
//        int lMid = 0;
//        while (l < r) {
//            int m = l + (r - l) / 2;
//            int rSize = midSize - m - 1;  // merged second part from another array
//            if (nums1[m] > nums2[rSize]) {
//                r = m - 1;
//            } else if (nums1[m + 1] < nums2[rSize - 1]) {
//                l = m + 1;
//            } else {
//                lMid = m;
//                break;
//            }
//        }
//        bool isEven = ((m + n) % 2 == 0);
//
//        if (!isEven) {
//            int median = min(nums1[lMid + 1], nums2[midSize - lMid]);
//            return median;
//        } else {
//            int median = max(nums1[lMid], nums2[midSize - lMid]) + min(nums1[lMid + 1], nums2[midSize - lMid + 1]) / 2;
//            return median;
//        }
//
//        return 0.0;
//    }

/*
int main() {
    vector<int> a1 {1, 2};
    vector<int> a2 {1, 2, 3, 4};
    cout << findMedianSortedArrays(a1, a2);
    
    vector<string> strs{"lint","code","love","you"};
    cout << "Encode: " << encode(strs) << "\n";
    string encoded = encode(strs);
    vector<string> res = decode(encoded);
    for (auto s:res) {
        cout << s << ",";
    }
    
    unordered_map<string, vector<char>> mm;

    vector<int> vvv {1,1,1,2,2,3};
    topKFrequent(vvv, 2);
    
    
    cout << "ana: " << isAnagram("abc", "bac") << "\n";

    vector<int> piles{1000000000};
    std::cout << minEatingSpeed(piles, 2);
    vector<int> d{5, 1, 3};
    TimeMap tm;
    // "love","high",10
    tm.set("love", "high", 10);
    tm.set("love", "low", 20);
    cout << tm.get("love", 5);
    cout << tm.get("love", 10);
    
    
    vector<int> v{2, 1, 5, 6, 2, 3};
    int r = largestRectangleArea(v);
    vector<vector<int>> vv {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
    vv = {{1}, {3}, {5}};
    searchMatrix(vv, 3);
    
    MinStack m;
    m.push(-2);
    m.push(0);
    m.push(-3);
    // "4","13","5","/","+"
//    vector<string> v = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
//    int r = evalRPN(v);
    std::cout << "Hello MinStack: " <<  m.getMin() << "\n";
}
*/
