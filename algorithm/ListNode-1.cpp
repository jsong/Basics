#include <iostream>
#include <math.h>
#include <string>
#include <vector>

// TODO: 
// reverse-words-in-a-string-ii
// 
using namespace std;

 struct ListNode {
      int val;
      ListNode *next;
      ListNode() : val(0), next(nullptr) {}
      ListNode(int x) : val(x), next(nullptr) {}
      ListNode(int x, ListNode *next) : val(x), next(next) {}
  };

 ListNode* swapPairs(ListNode* head) {
       ListNode *node = new ListNode(0);
       return node;
    }

// 228. Summary Ranges
vector<string> summaryRanges(vector<int>& nums) {
        vector<string> res;
        int i = 0;
        int n = nums.size();
        while (i < n) {
            int a = nums[i];
            while ((i + 1 < n) && (nums[i] + 1 == nums[i + 1])) {
                i++;
            }
            if (a != nums[i]) {
                res.push_back(to_string(a) + "->" + to_string(nums[i]));
            } else {
                res.push_back(to_string(a));
            }
            i++;
        }

        return res;
    }

    // 258. Add Digits
    int addDigits(int num) {
        if (num >= 0 && num <= 9) return num;
        int sum = 0;
        while (num != 0) {
            int mod = num % 10;
            num = num / 10;
            sum += mod;
        }

        return addDigits(sum);
    }


int main() {
    
    string n = "1234";
    cout << "str to long: " << stoll(n) << "\n";
    cout << "str to integer " << stoi(n) << "\n";
    string ss = "abc012(+-*/) ";
    // print ascii value for a given char
    for (int i = 0; i < ss.size(); i++) {
        cout << ss[i] << "-ascii: " << int(ss[i]) << "\n";
    }

    // print ascii char for a given value
    cout << "ascii: " << char(46) << "\n";

    int res = addDigits(12345);
    cout << "addDigits: " << res;

    vector<int> vect{0,1,2,4,5,7};
    vector<string> r = summaryRanges(vect);

    int a = 2;
    string aa = std::to_string(a);
    cout << "aa: " << aa << "\n";
    int b = a << 1; // left shift 1,
    int d = a >> 1; // right shift 1.
    int e = b & 1;
    int f = a & d; // 10 & 01


    int c = pow(a, 0);

    cout << "b: " << b << "\n";
    cout << "d: " << d << "\n";
    cout << "e: " << e << "\n";
    cout << "f: " << f << "\n"; 
    
	ListNode* head = new ListNode(1);
	head->next = new ListNode(2);
	head->next->next = new ListNode(3);
	head->next->next->next = new ListNode(4);
    swapPairs(head);

    // int n = grid.size();
        // int m = grid[0].size();
        
        // vector<vector<int> > sum(n, vector<int> m);

    cout << "Hello ListNode\n";
}