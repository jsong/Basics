//
//  Person.m
//  SampleiOS
//
//  Created by Song, Jian on 3/31/23.
//

#import <Foundation/Foundation.h>
#import "Person.h"

@implementation Job

- (instancetype)initWithTitle:(NSString *)title {
    if (self = [super init]) {
        _title = title;
        NSLog(@"memory print1: %@", self);
        return self;
    }
    
    return nil;
}

- (void)dealloc {
    NSLog(@"memory print2 dealloc: %@", self);
}

@end

@implementation Person

- (instancetype)initWithName:(NSString *)name age:(int)age {
    if (self = [super init]) {
        _name = name;
        _age = age;
    }
    
    return self;
}

//
- (Job *)job {
    // based on age?
    NSString *senior = @"S";
    return [[Job alloc] initWithTitle:senior];
}

@end
