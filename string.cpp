#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

vector<int> findSubstring(string s, vector<string>& words) {
        vector<int> res; 
        int n = words.size();
        int len = 0;
        // make sure it contains any word
        if (n > 0) {
            len = words[0].size();
        }
        unordered_map<string, int> m;
        for (string w: words) {
            m[w]++;
        }
        
        unordered_map<string, int> copy = m;
        for (int i = 0; i <= s.size() - n * len; i += len) {
            string sub = s.substr(i, n * len);
            m = copy;
            int counter = n;
            for (int j = 0; j < sub.size(); j += len) {
                string ss = sub.substr(j, len);
                if (m.find(ss) != m.end()) {
                    m[ss]--;
                    
                    if (m[ss] == 0) {
                        counter--;
                        if (counter == 0) {
                            // we found a match.
                            res.push_back(i);
                        }
                    }
                }
            }
        }
        
        return res;
}

#include <iostream>
using namespace std;

int main() {
    string s = "wordgoodgoodgoodbestword";
    vector<string> v {"word", "good", "best", "good"};
    vector<int> res = findSubstring(s, v);

}