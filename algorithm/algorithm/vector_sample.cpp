#include <iostream>
#include <vector>
#include <algorithm> 

using namespace std;

int main() {
	cout << "hello vector\n";
	vector<int> v {3, 1, 2};
	std::sort(v.begin(), v.end());

	for (auto a: v) {
		cout << a << ", ";
	}
}
