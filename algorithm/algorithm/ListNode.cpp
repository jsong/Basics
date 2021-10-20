#include <iostream>
#include <vector>
#include <stdio.h>
#include <stdlib.h>
#include <stack>
#include <queue>
#include <set>
#include "common.h"

using namespace std;

ListNode* removeElements(ListNode* head, int val) {
    if (head) {
        // we need the dummy for remove the head node
        ListNode* dummy = new ListNode(-1);
        dummy->next = head;
        ListNode* cur = dummy;
        while (cur->next) {
            if (cur->next->val == val) {
                cur->next = cur->next->next;
            }
            else {
                cur = cur->next;
            }
        }
        
        return dummy->next;
    }
    
    return nullptr;
}


 ListNode* swapPairs(ListNode* head) {
        // This is trying to figure out the head val of the list.        
        if (head != nullptr) {
            ListNode* dummy = new ListNode(0);
            dummy->next = head;
            ListNode* cur = dummy;
            ListNode* next = cur->next;

            while (cur && next) {
                // swap
                ListNode* temp = next;
                cout << "temp: " << temp->val << "\n";
                
                cur->next = next->next;
                if (next->next) {
                    
                }
                ListNode* tmp = next->next->next;
//                cout << "tmp: " << tmp->val << "\n";
                
                next->next->next = temp;
                next->next = tmp;
                // iterate to the next nodes
                next = next->next;
                cur = temp;
//                cout << "cur: " << cur->val << "\n";
//                cout << "next: " << next->val << "\n";
            }
            
            return dummy->next;
        }
        
        return nullptr;
    }


vector<string> summaryRanges(vector<int>& nums) {
    vector<string> res;
    // double pointer, when nums[end + 1]
    int start, end = 0;
    string tmp = "";
    
    while (end < nums.size() - 1) {
        if (nums[end] + 1 == nums[end + 1] ) {
            end++;
        } else {
            if (start == end) {
                string tmp = to_string(nums[end]);
                res.push_back(tmp);
            } else {
                string tmp = to_string(nums[start]) + "->" + to_string(nums[end]);
                res.push_back(tmp);
            }
            end++;
            start = end;
        }
    }

    if (end == nums.size() - 1) {
        res.push_back(to_string(nums[start]) + "->" + to_string(nums[end]));
    }
    
//    std::erase(res, "");
    return res;
}

// 241. Different Ways to Add Parentheses


// 227. Basic calculator II
// scan from left to right,
// when encounter number, we'll need to check its sign, push to the stack,
// until we see the highest priority sign, we'll need to pop the top element and do the math.
int calculate(string s) {
    long res = 0, num = 0, n = s.size();
    char op = '+';
    stack<int> st;
    for (int i = 0; i < n; ++i) {
        cout << s[i] << "\n";
        if (s[i] >= '0') {
            num = num * 10 + s[i] - '0';
        }
        if ((s[i] < '0' && s[i] != ' ') || i == n - 1) {
            if (op == '+') st.push(num);
            if (op == '-') st.push(-num);
            if (op == '*' || op == '/') {
                int tmp = (op == '*') ? st.top() * num : st.top() / num;
                st.pop();
                st.push(tmp);
            }
            op = s[i];
            num = 0;
        }
    }
    while (!st.empty()) {
        res += st.top();
        st.pop();
    }
    return res;
}

// bucket sort
int maximumGap(vector<int>& nums) {
    if (nums.size() <= 1) return 0;
    int mx = INT_MIN, mn = INT_MAX, n = nums.size(), pre = 0, res = 0;
    for (int num : nums) {
        mx = max(mx, num);
        mn = min(mn, num);
    }
    int size = (mx - mn) / n + 1, cnt = (mx - mn) / size + 1;
    vector<int> bucket_min(cnt, INT_MAX), bucket_max(cnt, INT_MIN);
    for (int num : nums) {
        int idx = (num - mn) / size;
        bucket_min[idx] = min(bucket_min[idx], num);
        bucket_max[idx] = max(bucket_max[idx], num);
    }
    for (int i = 1; i < cnt; ++i) {
        if (bucket_min[i] == INT_MAX || bucket_max[i] == INT_MIN) continue;
        res = max(res, bucket_min[i] - bucket_max[pre]);
        pre = i;
    }
    return res;
}

// 224. Basic Calculator
int calculate2(string s) {
    int res = 0;
    stack<int> st;
    long num = 0;
    int sign = 1;
    for (int i = 0; i < s.size(); i++) {
        char a = s[i];
        cout << a;
        if (a >= '0' && a <= '9') {
            num = 10 * num + a - '0';
        } else {
            if (a == '(' ) {
                st.push(res);
                st.push(sign);
                res = 0;
                sign = 1;
            } else if (a == ')') {
                res += num * sign;
                res = res * st.top();
                st.pop();
                res += st.top();
                st.pop();
                num = 0;
            } else if (a == '+') {
                res += num * sign;
                sign = 1;
                num = 0;
            } else if (a == '-') {
                res += num * sign;
                sign = -1;
                num = 0;
            }
        }
    }
    res += num * sign;
    return res;
}


// Basic Calculator III
int calculate3(string s) {
    int n = s.size(), num = 0, curRes = 0, res = 0;
    char op = '+';
    for (int i = 0; i < n; ++i) {
        char c = s[i];
        if (c >= '0' && c <= '9') {
            num = num * 10 + c - '0';
        } else if (c == '(') {
            int j = i, cnt = 0;
            for (; i < n; ++i) {
                if (s[i] == '(') ++cnt;
                if (s[i] == ')') --cnt;
                if (cnt == 0) break;
            }
            num = calculate3(s.substr(j + 1, i - j - 1));
        }
        if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
            switch (op) {
                case '+': curRes += num; break;
                case '-': curRes -= num; break;
                case '*': curRes *= num; break;
                case '/': curRes /= num; break;
            }
            if (c == '+' || c == '-' || i == n - 1) {
                res += curRes;
                curRes = 0;
            }
            op = c;
            num = 0;
        }
    }
    return res;
}

string convertHundred(int num) {
    vector<string> v1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    vector<string> v2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    string res;
    int a = num / 100, b = num % 100, c = num % 10;
    res = b < 20 ? v1[b] : v2[b / 10] + (c ? " " + v1[c] : "");
    if (a > 0) res = v1[a] + " Hundred" + (b ? " " + res : "");
    return res;
}

// TODO: not done
string numberToWords(int num) {
    string res = convertHundred(num % 1000);
    vector<string> v = {"Thousand", "Million", "Billion"};
    for (int i = 0; i < 3; ++i) {
        num /= 1000;
        res = num % 1000 ? convertHundred(num % 1000) + " " + v[i] + " " + res : res;
    }
    while (res.back() == ' ') res.pop_back();
    return res.empty() ? "Zero" : res;
}


string shortestPalindrome(string s) {
    int i = 0, n = s.size();
    for (int j = n - 1; j >= 0; --j) {
        if (s[i] == s[j]) ++i;
    }
    if (i == n) return s;
    string rem = s.substr(i);
    reverse(rem.begin(), rem.end());
    return rem + shortestPalindrome(s.substr(0, i)) + s.substr(i);
}

// 239. Sliding Window Maximum
vector<int> maxSlidingWindow(vector<int>& nums, int k) {
    vector<int> res;
    priority_queue<pair<int, int>> q;
    for (int i = 0; i < nums.size(); ++i) {
        while (!q.empty() && q.top().second <= i - k) {
            q.pop();
        }
        q.push({nums[i], i});
        if (i >= k - 1) res.push_back(q.top().first);
    }
    return res;
}

void printVector(vector<vector<int>>& v) {
    for (auto a: v) {
        cout << "{" << a[0] << ", " << a[1] << "}" << "\n";
    }
}

// 218. The Skyline Problem
vector<vector<int>> getSkyline(vector<vector<int>>& buildings) {
    vector<vector<int>> h, res;
    multiset<int> m;
    int pre = 0, cur = 0;
    for (auto &a : buildings) {
        h.push_back({a[0], -a[2]});
        h.push_back({a[1], a[2]});
    }
    printVector(h);
    sort(h.begin(), h.end());
    cout << "++++++ after sort\n";
    printVector(h);
    m.insert(0);
    for (auto &a : h) {
        if (a[1] < 0) m.insert(-a[1]);
        else m.erase(m.find(a[1]));
        cur = *m.rbegin();
        if (cur != pre) {
            res.push_back({a[0], cur});
            pre = cur;
        }
    }
    return res;
}

// 223. Rectangle Area
int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
    // relative position left, right, upper, lower.
    int sum1 = (ax2 - ax1) * (ay2 - ay1);
    int sum2 = (bx2 - bx1) * (by2 - by1);
    if (bx1 >= ax2 || bx2 <= ax1 || by1 || by1 >= ay2 || by2 <= ay1) return sum1 + sum2;
    
    // have intersection.
    return sum1 + sum2 - (min(bx2, ax2) - max(ax1, bx1)) * (min(ay2, by2) - max(ay1, by1));
}

int main() {
    cout << computeArea(-3, 0, 3, 4, 0, -1, 9, 2);
    // [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ]
    vector<vector<int>> v {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
    getSkyline(v);
    
    vector<int> sliding{1, 3, -1, -3, 5, 3, 6, 7};
    maxSlidingWindow(sliding, 3);
    // init or not
    vector<int> emptyTest;
    emptyTest.push_back(1);
    
    string bb = "123";
    cout << bb.substr(0, 1) + bb.substr(1);
    
    cout << shortestPalindrome("abbac") << "\n";
    
    cout << numberToWords(1234) << "\n";
    
    vector<int> gapV{3, 6, 9, 1};
    int gp = maximumGap(gapV);
    
    string b = "(1+(2+3))";
    cout << calculate2(b) << "\n";
    string c = "3-2*2";
    cout << calculate(c) << "\n";
    string d = "3+(5-2*3)";
    cout << calculate3(d);
    
    int a = 5;
//    string b = itoa(a);
    vector<int> vect{0,2,3,4,6,8,9};
    vector<string> r = summaryRanges(vect);

	ListNode* head = new ListNode(7);
	head->next = new ListNode(7);
	head->next->next = new ListNode(7);
    head->next->next->next = new ListNode(7);
    ListNode* res = removeElements(head, 7);
    
    
    swapPairs(head);
    cout << "Hello ListNode\n";
}
