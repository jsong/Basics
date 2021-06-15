#include <vector>
#include <iostream>
#include <stack>

using namespace std;


int mctFromLeafValues(vector<int>& arr) {
	int res = 0;
	stack<int> s;
	s.push(INT_MAX);
	
	for (int i = 0; i < arr.size(); i++) {
		while (s.top() < arr[i]) {
			int t = s.top();
			s.pop();
			res += t * min(s.top(), arr[i]);
		}
		s.push(arr[i]);
	}

	while (s.size() > 2) {
		int t = s.top();
		s.pop();
		res += t * s.top();
	}

	return res;
}


int leastInterval(vector<char>& tasks, int n) {
        vector<int> cnt(26, 0);
        for (char task : tasks) {
            ++cnt[task - 'A'];
        }
        sort(cnt.begin(), cnt.end());
        int i = 25, mx = cnt[25], len = tasks.size();
        cout << "mx:" << mx << "\n";

        while (i >= 0 && cnt[i] == mx) --i;
        return max(len, (mx - 1) * (n + 1) + 25 - i);
 }



int main(int argc, const char * argv[]) {
	vector<int> v = {8, 6, 5, 2, 7};
	string s = "AAAABBBEEFFGG";
	std::vector<char> vc(s.begin(), s.end());
	int l = leastInterval(vc, 3);

    int r = mctFromLeafValues(v);
    std::cout << r << "\n";
	return 0;
}