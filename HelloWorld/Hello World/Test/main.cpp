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
#include <queue>
#include <memory>
#include <mutex>
#include <thread>
#include <iostream>

/**
* RVO, return value optimization.
**/
struct C {
//  C() = default;
  C() { std:: cout << "Constructor\n"; }
  C(const C&) { std::cout << "A copy was made.\n"; }
};

C F() {
  return C();
}

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


/**
* bit occupation sample.
* undefined will take 4 or full bytes, others if less than 4 or full bytes will be rounded into 4 or full bytes.
**/
struct date
{
    unsigned int d: 1; // 5 bits
    unsigned int m: 7; // 4 bits
    unsigned int y;
};


/**
* C++ Rule of three or five.
**/
class MyObject
{
    public: // %p\n", (void *) &a
        MyObject(){printf("object Alloc...Default constructor %p\n", this);} // why it failed.
        MyObject(int num): num_(num){printf("Alloc...%d, address: %p\n", num_, this);}
    
        MyObject(const MyObject& o) {
            printf("Copy constructor... %d\n", o.num_);
            num_ = o.num_;
            std::cout << "object copied: address" << this << "\n";
        }
        MyObject& operator = (const MyObject& rhs)
        {
            printf("Assignment constructor... %d\n", rhs.num_);
            num_ = rhs.num_ + 5;
            return *this;
        }
    
        // method to test copy constructor for temporary object.
        void add(MyObject o)
        {
            num_ += o.num_;
            std::cout << "Address: " << &o << "\n";
        }
    
        // move constructor, rvalue reference.
//        MyObject(MyObject&& obj)
//        {
//            printf("Move constructor... %d", obj.num_);
//            obj.num_ = 0;
//        }
    
        // move assignment operation, rvalue reference.
//        MyObject& operator = (MyObject&& obj)
//        {
//            printf("Move assignment... %d\n", obj.num_);
//            return *this;
//        }
    
        ~MyObject()
        {
            printf("Dealloc... %d\n", num_);
            std::cout << "object dealloc: address" << this << "\n";
        };
    
        void print() const {std::cout << "Address:" << this << "\n";}
    public:
        int num_;
};

MyObject createObj(int num)
{
    return MyObject(num);
//    return obj; // copy of obj?
}

const MyObject multiply(const MyObject& p){
    MyObject point(10);
    point.num_ *= p.num_;
    //... Do multiplication
    return point;
}


/**
* Sample Thread worker and consumer model.
**/

std::mutex mutex_;
std::condition_variable condVar;

bool dataReady{false};

void waitingForWork(){
    std::cout << "Waiting " << std::endl;
    std::unique_lock<std::mutex> lck(mutex_);
    condVar.wait(lck, []{ return dataReady; });   // (4)
    std::cout << "Running " << std::endl;
}

void setDataReady(){
    {
        std::lock_guard<std::mutex> lck(mutex_);
        dataReady = true;
    }
    std::cout << "Data prepared" << std::endl;
    condVar.notify_one();                        // (3)
}

class mythread: public std::thread
{
    public:
        mythread(){}
        virtual ~mythread(){std::cout << "destructor\n";}
};

void pause_thread(int n)
{
//  std::cout << "start thread: " << n << "\n";
  std::this_thread::sleep_for (std::chrono::seconds(n));
//  std::cout << "pause of " << n << " seconds ended\n";
}

void reference(MyObject& r)
{
    std::cout << "Address:" <<&r << "\n";
}

/**
 * Smart pointer, weak ptr. Before use it, make sure the object is still alive and then copy it to shared_ptr.
 **/

void useMyWeakPointer(std::weak_ptr<int> wp)
{
    if (std::shared_ptr<int> sp = wp.lock())
    {
        // the resource is still here and can be used
    }
    else
    {
        // the resource is no longer here
    }
}


union test {
    int x;
  char y[4];
};

int main()
{
    std::string sa[] = {"a", "b", "c"};
    std::string* sp = sa;
    std::string** ss = &sp;
    
    for (int i= 0; i < 3; i++)
    {
//        ss[i] = new std::string(sa[i]);
        std::cout << *ss[i] << "\n";
    }
    
    union test t;
    
//    t.y = 'usa'; // t.y also gets value 2
    //printf("After making x = 2:\n x = %d, y = %d\n\n",
        //t.x, t.y);
    //std::cout << "y:" << t.y << "\n";
  
  throw 20;
  std::cout << "after exception \n";
  
  std::cout << "Switch Case start!\n";
  int condi = 5;
  switch (condi)
  {
    case 5:
        std::cout << "Switch Case hit!\n";
        break;
    default:
        std::cout << "Switch Case miss, default handle\n";
        break;
  }
  std::cout << "Switch Case end!\n";


  std::cout << "RVO: start!\n"; // -fno-elide-constructors, disable compiler RVO.
  C obj = F();
  std::cout << "RVO: end!\n";

  std::cout << "Bit field: start!\n";
  //struct date dt = {3};
  //std::cout << "Struct bit value: " << dt.d << "\n";
  printf("size of date is %d bytes\n", sizeof(struct date));
  std::cout << "Bit field: end!\n";
 
  
  std::cout << "Spawning 3 threads...\n";
 

 std::thread t1 (pause_thread,1);
 int size = 3;
 int *ptr = new int[size];
 for (int i = 0; i < size; i++)
 {
    ptr[i] = i;
 }
 std::cout << "ptr:" << ptr[2] << "\n";
 
 int **p = new int*[size];
 for (int i = 0; i < size; i++)
 {
    p[i] = new int[1];
 }
 
typedef std::unordered_map<uint32_t, std::string> TilesCache;
//TilesCache cache;
std::shared_ptr<TilesCache> cache(new TilesCache);
//cache[10];
//std::unordered_map<uint32_t, std::string> cache;
//cache[10] = "ten";
//cache[20] = "twenty";
//std::string r = cache[100];

//cache->insert(std::make_pair(10, "ten"));

//if (cache->find(10) != cache->end())
//{
//    std::cout << "key 10: " << cache->at(10) << "\n";
//}

//std::string value = cache->at(10);

//std::string value2 = cache->at(100);
//std::cout << "key 100: " << value2 << "\n";


// share pointer memory
// MyObject obj(5);
// {
//    std::shared_ptr<MyObject> p = std::make_shared<MyObject>(100);
//    std::cout << p.use_count();
// }

 /**
  *  Passin parameter will be copied. Pass by value.
  **/
 MyObject obj1(5);
 MyObject obj2(5);
 obj1.add(obj2);
 std::cout << "obj1: " << obj1.num_ << "\n";
 
 /**
  * Copy constructor,
 **/
 
 MyObject obj3(createObj(10000));
 
 /**
  * Smart pointer
  **/
 std::unique_ptr<int> p1 = std::make_unique<int>(42);
 std::unique_ptr<int> p2 = std::move(p1); // now p2 hold the resource, unique_ptr is not copyable, so direct assignment will fail p2 = p1;
 if (p1 != nullptr)
 {
    std::cout << "smart pointer: " << *p1 << "\n";
 }
 std::cout << *p2 << "\n";
 
// MyObject obj1 = obj; // copy constructor
 // assignment operator, if obj1 declared early.
 // obj1 = obj;
// std::cout << obj1.num_ << "\n";
 
// MyObject *objPtr = new MyObject[size];
 
// for (int i = 0; i < size; i++)
// {
//    MyObject m(i);
//    objPtr[i] = m;
// }
// 
// delete [] objPtr;
// 
// MyObject **objPPtr = new MyObject*[size];
// for (int i = 0; i < size; i++)
// {
//    MyObject* obj = new MyObject[i];
//    objPPtr[i] = obj;
// }
// 
// delete[] objPPtr;
 
 // mythread t2;
 // std::thread t3 (pause_thread,3);
  std::cout << "Done spawning threads. Now waiting for them to join:\n";
 // t1.join();
 // t2.join();
 // t3.join();
  std::cout << "All threads joined!\n";//  std::thread t1(waitingForWork);               // (1)
 // std::thread t2(setDataReady);                 // (2)

//  t1.join();
//  t2.join();
 MyObject bb(10);
 const MyObject& cb = bb;
 
// MyObject mm = multiply(bb);
 
 std::vector<MyObject> array;
// array.emplace_back(cb);
 
 array.push_back(cb);
 
// MyObject bbb = bb.stack();

  std::cout << std::endl;
    for (int i = 3; i < 5; i++)
    {
        std::unordered_map<uint32_t, MyObject> omap;
//        std::vector<MyObject> v;
        MyObject obj(i);
//        MyObject bb = obj.stack();
        
//        v.push_back(obj);
        
//        obj.print();
//        map[i] = MyObject(i);
        std::pair<uint32_t, MyObject> pair(i, obj);
        omap.insert(pair);
        const MyObject& o = omap[i];
        std::cout << "object: address" << &o << "\n";
//        const std::vector<MyObject>* pVec[]= {&o};
//        o.print(); // copied object, already changed.
        std::cout << "end loop\n";
    }
//        omap.clear();

 const auto ref_start = std::chrono::high_resolution_clock::now();
    const auto& ref = Info::getStringRef();
//    std::cout << ref << std::endl;
    std::vector<int> a1 = {3, 2, 1};
    std::vector<int> a2 = {1, 2, 3};
    int v1 = 2048;
    v1 = v1 >> 10;
    
    if (a1 == a2)
    {
        std::cout << "equal" << "\n";
    }
    
    
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
    
    std::vector<int> vv = {1, 2, 3};
    for (int i = 0; i < vv.size(); i++)
    {
        std::cout << "i:" << vv[i] << "\n";
    }

    for (int i = 0; i < 5; i++)
    {
        std::cout << "::" << (1 << 5) << "\n";
    }
    
    int a = 5;
    int b = 3;
    int c = a | b;
    std::cout << "c: " << c << "\n";
    for (int i = 0; i < 6; i++)
    {
        std:: cout << (1 << i) << "\n";
    }
    
    std::string tasks = "ACCCEEE";
    std::vector<char> vect(tasks.begin(), tasks.end());
//    sort(vect.begin(), vect.end());
    vector<int> cnt(26, 0);
    
    for (char task : vect) {
        ++cnt[task - 'A'];
    }
    sort(cnt.begin(), cnt.end());
//    int mx = cnt[25];
    
    unordered_map<char, int> m;
    priority_queue<int> q;
    for (char c : tasks) ++m[c];
    for (auto a : m) q.push(a.second);
    
    vector<int> num;
    for (int i = 0; i < 3; i ++)
    {
        std::cout << "I:" << i << "\n";
        vector<MyObject> vc;
        
        for (int j = 0; j < 10; j++)
        {
            MyObject obj1(j);
            MyObject obj2(j);
//            std::shared_ptr<MyObject> ptr = std::make_shared<MyObject>();
//            vc.push_back(MyObject(j));
            const MyObject p[] = {obj1, obj2};
            
//            for (auto iter: p)
//            {
//                std::cout << "m:" << j << "\n";
//            }
            
//            for (int m = 0; m < sizeof(p) / sizeof(MyObject); m++)
//            {
//                std::cout << "m:" << m << "\n";
//            }
//            std::cout << "obj: " << ptr << "\n";
        }
//        num.push_back(i);
//        std::cout << num[i] << "\n";
    }
    
    MyObject object(5);
    std::cout << "Address2: " << &object << "\n";
    reference(object);
    
    vector<int> origin;
    for (int i = 0; i < 3; i++)
    {
        origin.push_back(i);
    }
    
    vector<int>& originCopy = origin;
    
    for (int i = 0; i < originCopy.size(); i++)
    {
        int& a = originCopy.at(i);
        a += i;
    }
    
    std::cout << "temp\n";
    
    // thread related.
    int counter = 0;
    std::mutex counter_mutex;
    std::vector<std::thread> threads;
 
    auto worker_task = [&](int id) {
        std::unique_lock<std::mutex> lock(counter_mutex);
        ++counter;
        std::cout << id << ", initial counter: " << counter << '\n';
        lock.unlock();
 
        // don't hold the lock while we simulate an expensive operation
        std::this_thread::sleep_for(std::chrono::seconds(1));
        std::cout <<"exit sleep, enter lock" << id << "\n";
        
        lock.lock();
        ++counter;
        std::cout << id << ", final counter: " << counter << '\n';
    };
 
    for (int i = 0; i < 3; ++i) threads.emplace_back(worker_task, i);
 
    for (auto &thread : threads) thread.join();
    
    /**
    * lamda
    */
    {
        char buf[32];
        auto erase_buf = [](char *p) { memset(p, 0, sizeof(buf)); };
        std::unique_ptr<char, decltype(erase_buf)> passwd(buf, erase_buf);
//        get_password(passwd.get());
//        check_password(passwd.get());
    }
}
