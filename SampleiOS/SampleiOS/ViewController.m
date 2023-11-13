//
//  ViewController.m
//  SampleiOS
//
//  Created by Song, Jian on 8/28/21.
//

#import "ViewController.h"
#import "Person.h"

@interface ViewController ()
@property Person *manInCharge;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    // iOS collection retains NOT COPY the elements.
    NSMutableArray *array = [NSMutableArray new];
   
    self.manInCharge =  [[Person alloc] initWithName:@"John" age:32];
    [self checkJob];
    
//    [array addObject:john];
//    // one year later
//    john.age = 33;
//    for (Person *p in array) {
//        NSLog(@"Name: %@ -> Age: %d", p.name, p.age);
//    }
    NSInteger timeInterval = 60;
    for (int i = 0; i < timeInterval; i++) {
        [NSThread sleepForTimeInterval:1.0f];
    }
    
    NSLog(@"Come to an end, check job");
    NSLog(@"%@", self.manInCharge.job.title);
}

- (void)checkJob {
    self.manInCharge.job.title = @"Senior";
    NSLog(@"check job done");
}

@end
