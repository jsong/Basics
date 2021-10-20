#include <iostream>
using namespace std;

// bit wise 
void count() {
	int a = 8;
	while (a != 0) {
		cout << a << "\n";
		a = a >> 1;
	}
}

string binaryStrRep(int a) {
	string temp = "";
	while (a > 0) {
		if (a & 1) {
			temp += "1";
		} else {
			temp += "0";
		}
		a >>= 1;
	}

	std::reverse(temp.begin(), temp.end());
	return temp;
}

int main() {
	
	count();
	cout << "strRep: " << binaryStrRep(8) << "\n";

	for (int i = 0; i < 10; i +=2 ) {
		for (int j = 0; j < 5; j++) {
			// std::cout << "j: " << j;
			if (j == 3) {
				break;
			}
		}

		std::cout << "i: " << i;
	}

	
	string s = "world";
    int loopEnd = s.size() - 10;

	for (int i = 0; i < loopEnd; i++) {
		cout << "shouldn't be here\n";
	}

	std::string a = s.substr(1, 2);
	std::cout << a << "\n";

    cout << "Hello world entry\n";
}