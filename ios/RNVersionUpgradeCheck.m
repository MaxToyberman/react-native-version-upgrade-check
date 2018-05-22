
#import "RNVersionUpgradeCheck.h"

@implementation RNVersionUpgradeCheck

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()


RCT_EXPORT_METHOD(getVersionData:(RCTResponseSenderBlock)callback) {
    
    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
    
    NSString *currentAppVersion = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleShortVersionString"];
    NSString *previousVersion = [defaults objectForKey:@"appVersion"];
    
    NSMutableDictionary *dict = [NSMutableDictionary dictionary];

    
    if (!previousVersion) {
        
        [defaults setObject:currentAppVersion forKey:@"appVersion"];
        [defaults synchronize];
        [dict setObject:[NSNumber numberWithBool:YES] forKey:@"firstLaunch"];

    } else if ([previousVersion isEqualToString:currentAppVersion]) {
        // same version
        [dict setObject:[NSNumber numberWithBool:YES] forKey:@"sameVersion"];

    } else {
        // other version
        [defaults setObject:currentAppVersion forKey:@"appVersion"];
        [defaults synchronize];
        [dict setObject:[NSNumber numberWithBool:YES] forKey:@"isUpdated"];

    }
    callback(@[[NSDictionary dictionaryWithObjects:dict.allValues
                                           forKeys:dict.allKeys]]);

    
}


@end
  
