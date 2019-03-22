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


// C++ no extra cost on space.
void reverseWords(string &s) {
    int storeIndex = 0, n = s.size();
    reverse(s.begin(), s.end());
    for (int i = 0; i < n; ++i) {
        if (s[i] != ' ') {
            if (storeIndex != 0) s[storeIndex++] = ' '; // make sure we only add one space in between.
            int j = i; // i when the words starts.
            while (j < n && s[j] != ' ') s[storeIndex++] = s[j++]; // j is the end of the words index.
            reverse(s.begin() + storeIndex - (j - i), s.begin() + storeIndex);
            i = j;
        }
    }
    s.resize(storeIndex);
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
    string h = "hello  world";
    reverseWords(h);
    std::cout << "reverse: " << h << "\n";
    
    return 0;
}
