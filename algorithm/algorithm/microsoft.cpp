#include <vector>
#include <iostream>
#include <unordered_map>

using namespace std;

vector<string> reorderLogFiles(vector<string>& logs) {
   vector<string> res, digits;
   return res;     
}


vector<vector<int>> threeSum(vector<int>& nums) {
	vector<vector<int>> res;
	// TODO: reverse order how to write
	sort(nums.begin(), nums.end());


   return res; 
}

struct Base
{
    Base() { std::cout << "  Base::Base()\n"; }
    // Note: non-virtual destructor is OK here
    ~Base() { std::cout << "  Base::~Base()\n"; }
};

struct AlertInfo 
{
	std::string houseNumber;
	std::shared_ptr<Base> streetInfo;
};


// 937. Reorder Data in Log Files
int main(int argc, const char * argv[]) {
	vector<int> nums {-1, 0, 1, 2, -1, -4};
	threeSum(nums);
	vector<int> twoSum {3, 3};
	unordered_map<int, int> m;
	for (int i = 0; i < twoSum.size(); i++) {
		m[twoSum[i]] = i;
	}

	cout << m.size() << ": map size\n";

	string digit = "dig1 810 5 2";
	AlertInfo alert;
	if (alert.streetInfo != nullptr) 
	{
		cout << "struct not nullptr";
	}
	auto pos = digit.find(" ");
	cout << pos << "\n";
	cout << digit[pos + 1] << "\n";
	cout << "Welcome to Microsoft!\n";
}