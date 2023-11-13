//
//  AppDelegate.m
//  SampleiOS
//
//  Created by Song, Jian on 8/28/21.
//

#import "AppDelegate.h"

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
    NSString* abc = @"abc";
    NSString* abcNew = [[NSString alloc]initWithString:@"abc"];
    NSLog(@"hash1: %d", [abc hash]);
    NSLog(@"hash2: %d", [abcNew hash]);
    
    //
    NSMutableString *a = [NSMutableString stringWithString:@"Blue"];
    NSMutableString *b = [NSMutableString stringWithString:@"Red"];
    NSMutableString *c = [b copy];
    NSString *d = nil;
    
    NSLog(@"Address: b: %p, c: %p, equal: %d", b, c, [b isEqualToString:d]);
    
    // TODO: how about the customized obj.
    NSMutableArray *arr1 = @[a, b];
//  po CFGetRetainCount(a); get reference count on the console.
    a = nil;
    // shallow copy.
//    NSArray *arr2 = [arr1 copy];
    // deep copy.
    [self printNSArray: arr1];
    NSData *buffer;
    buffer = [NSKeyedArchiver archivedDataWithRootObject: arr1];
    NSArray* arr2 = [NSKeyedUnarchiver unarchiveObjectWithData: buffer];
    
    [self printNSArray: arr2];
    NSMutableString *temp = arr1[0];
    [temp setString:@"Black"];
    NSLog(@"After shallow copy.");
    [self printNSArray: arr1];
    NSString *mesg = nil;
    [self modifyString:&mesg];
    NSLog(@"++++++: %@", mesg);
    int tempint = 6;
    NSString *versionInReceipt = @"20";
    NSString *bundleVersion = @"3.77.20";
    BOOL isValid = NO;
    NSString *validStr = [@(isValid) stringValue];
    NSLog(@"++++++V: %@", validStr);

    NSRange range = [bundleVersion rangeOfString:@"." options:NSBackwardsSearch];
    NSString *sub = [bundleVersion substringWithRange: NSMakeRange(range.location + 1, bundleVersion.length - range.location - 1)];
    BOOL versionEqual = [bundleVersion rangeOfString:versionInReceipt].length > 0;

    int *aa = &tempint;
    [self changeInt:aa];
    NSLog(@"%d", *aa);
    NSMutableDictionary *dict = [NSMutableDictionary new];
    NSString *mesg1 = [dict description];
    if (mesg1) {
        NSLog(@"123");
    }
//    dict[@"key"] = @"123";

    for (int i = 0; i < 4; i ++) {
        for (int j = 0; j < 3; j++) {
            if (j == 2) break;
        }
        NSLog(@"i: %d", i);
    }
    
    NSLog(@"j break out");
    
    return YES;
}

- (void)changeInt:(int *)val {
    *val = 5;
}

- (void)modifyString:(NSString **)mesg {
    *mesg = @"changed";
}

- (void)printNSArray:(NSArray *)array {
    for (int i = 0; i < array.count; i++) {
        NSLog(@"%@", array[i]);
    }
}

#pragma mark - UISceneSession lifecycle


- (UISceneConfiguration *)application:(UIApplication *)application configurationForConnectingSceneSession:(UISceneSession *)connectingSceneSession options:(UISceneConnectionOptions *)options {
    // Called when a new scene session is being created.
    // Use this method to select a configuration to create the new scene with.
    return [[UISceneConfiguration alloc] initWithName:@"Default Configuration" sessionRole:connectingSceneSession.role];
}


- (void)application:(UIApplication *)application didDiscardSceneSessions:(NSSet<UISceneSession *> *)sceneSessions {
    // Called when the user discards a scene session.
    // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
    // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
}


#pragma mark - Core Data stack

@synthesize persistentContainer = _persistentContainer;

- (NSPersistentContainer *)persistentContainer {
    // The persistent container for the application. This implementation creates and returns a container, having loaded the store for the application to it.
    @synchronized (self) {
        if (_persistentContainer == nil) {
            _persistentContainer = [[NSPersistentContainer alloc] initWithName:@"SampleiOS"];
            [_persistentContainer loadPersistentStoresWithCompletionHandler:^(NSPersistentStoreDescription *storeDescription, NSError *error) {
                if (error != nil) {
                    // Replace this implementation with code to handle the error appropriately.
                    // abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
                    
                    /*
                     Typical reasons for an error here include:
                     * The parent directory does not exist, cannot be created, or disallows writing.
                     * The persistent store is not accessible, due to permissions or data protection when the device is locked.
                     * The device is out of space.
                     * The store could not be migrated to the current model version.
                     Check the error message to determine what the actual problem was.
                    */
                    NSLog(@"Unresolved error %@, %@", error, error.userInfo);
                    abort();
                }
            }];
        }
    }
    
    return _persistentContainer;
}

#pragma mark - Core Data Saving support

- (void)saveContext {
    NSManagedObjectContext *context = self.persistentContainer.viewContext;
    NSError *error = nil;
    if ([context hasChanges] && ![context save:&error]) {
        // Replace this implementation with code to handle the error appropriately.
        // abort() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
        NSLog(@"Unresolved error %@, %@", error, error.userInfo);
        abort();
    }
}

@end
