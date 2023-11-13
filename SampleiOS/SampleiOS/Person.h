//
//  Person.m
//  SampleiOS
//
//  Created by Song, Jian on 3/31/23.
//

#import <Foundation/Foundation.h>

@interface Job : NSObject
@property NSString *title;
- (instancetype)initWithTitle:(NSString *)title;
@end


@interface Person : NSObject
@property NSString *name;
@property int age;
@property (readonly, retain) Job *job;
- (instancetype)initWithName:(NSString *)name age:(int)age;
@end


