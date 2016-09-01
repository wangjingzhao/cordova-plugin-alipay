//
//  AliPayPlugin.h
//  patient
//
//  Created by 郭大鹏 on 16/8/31.
//
//

#import <Cordova/CDV.h>
#import <Cordova/CDVPlugin.h>
#import <AlipaySDK/AlipaySDK.h>

@interface AliPayPlugin : CDVPlugin {}

@property(nonatomic,strong)NSString *partner;

- (void)pay:(CDVInvokedUrlCommand*)command;

@end