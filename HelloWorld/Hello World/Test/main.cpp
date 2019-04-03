//
//  main.cpp
//  Test
//
//  Created by Song, Jian on 3/29/19.
//  Copyright © 2019 Jian. All rights reserved.
//

#include <iostream>
#include <chrono>
#include <string>
#include <unordered_map>
#include <vector>

class Info
{
public:
    static const std::string& getStringRef()
    {
        static std::string INFO = "INFO";
        return INFO;
    }
    
    static std::string getStringCopy()
    {
        static std::string INFO = "INFO";
        return INFO;
    }
};

int main()
{
 const auto ref_start = std::chrono::high_resolution_clock::now();
    const auto& ref = Info::getStringRef();
//    std::cout << ref << std::endl;

    const auto ref_end = std::chrono::high_resolution_clock::now();
    const auto ref_duration = std::chrono::duration_cast<std::chrono::nanoseconds>(ref_end - ref_start);
//    std::cout << "reference access takes " << ref_duration.count() << " nanoseconds" << std::endl;
    printf("1: %lld \n", ref_duration.count());
    
    auto copy_start = std::chrono::high_resolution_clock::now();
//    copy_start = std::chrono::high_resolution_clock::now();
    const auto copy = Info::getStringCopy();
//    std::cout << copy << std::endl;
    const auto copy_end = std::chrono::high_resolution_clock::now();
    const auto copy_duration = std::chrono::duration_cast<std::chrono::nanoseconds>(copy_end - copy_start);
//    std::cout << "copy access takes " << copy_duration.count() << " nanoseconds" << std::endl;
    printf("2: %lld \n", copy_duration.count());
    using namespace std;
    vector<int> v = {1, 2, 3};
    unordered_map<int, vector<int>> map;
    map[v[0]].push_back(0);

    if (map.count(v[0]))
    {
        map[v[0]].push_back(0);
    }
    else
    {
        map[v[0]].push_back(0);
    }
    
    vector<int> vr = map[1];
    if (vr.size() > 0)
    {
        std::cout << "size: " << vr.size() << "\n";
    }
    
    std::vector<int> v = {1, 2, 3};
    for (int i = 0; i < v.size(); i++)
    {
        std::cout << "i: " << i << v[i] << "\n";
    }

    return 0;
}
