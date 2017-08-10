//
//  AliPayPlugin.m
//  patient
//
//  Created by 郭大鹏 on 16/8/31.
//
//

#import "AliPayPlugin.h"

@interface AliPayPlugin () {}

@end

@implementation AliPayPlugin

-(void)pluginInitialize {

}

- (void)pay:(CDVInvokedUrlCommand*)command {

    NSString *appScheme = @"com.your.package";

    self.currentCallbackId = command.callbackId;

    NSMutableDictionary *args = [command argumentAtIndex:0];
    NSLog(@"%@",args);

    NSString *orderString= [args objectForKey:@"order"];

    // NOTE: 如果加签成功，则继续执行支付
    if (orderString != nil) {
        //应用注册scheme,在AliSDKDemo-Info.plist定义URL types

        // NOTE: 调用支付结果开始支付
        //        [[AlipaySDK defaultService] payOrder:orderString fromScheme:appScheme callback:^(NSDictionary *resultDic) {
        //            NSLog(@"reslut = %@",resultDic);
        //        }];
        [[AlipaySDK defaultService] payOrder:orderString fromScheme:appScheme callback:^(NSDictionary *resultDic) {
            if ([[resultDic objectForKey:@"resultStatus"]  isEqual: @"9000"]) {
                [self successWithCallbackID:self.currentCallbackId messageAsDictionary:resultDic];
            } else {
                [self failWithCallbackID:self.currentCallbackId messageAsDictionary:resultDic];
            }
            NSLog(@"pay reslut = %@",resultDic);
        }];
    }
}

- (void)successWithCallbackID:(NSString *)callbackID messageAsDictionary:(NSDictionary *)message
{
    CDVPluginResult *commandResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:message];
    [self.commandDelegate sendPluginResult:commandResult callbackId:callbackID];
}

- (void)failWithCallbackID:(NSString *)callbackID messageAsDictionary:(NSDictionary *)message
{
    CDVPluginResult *commandResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsDictionary:message];
    [self.commandDelegate sendPluginResult:commandResult callbackId:callbackID];
}

- (void)handleOpenURL:(NSNotification *)notification {
    NSURL* url = [notification object];
    if ([url.host isEqualToString:@"safepay"]) {
        //跳转支付宝钱包进行支付，处理支付结果
        [[AlipaySDK defaultService] processOrderWithPaymentResult:url standbyCallback:^(NSDictionary *resultDic) {
            if ([[resultDic objectForKey:@"resultStatus"]  isEqual: @"9000"]) {
                [self successWithCallbackID:self.currentCallbackId messageAsDictionary:resultDic];
            } else {
                [self failWithCallbackID:self.currentCallbackId messageAsDictionary:resultDic];
            }
            NSLog(@"handleOpenURL result = %@",resultDic);
        }];
    }
}

- (void)successWithCallbackID:(NSString *)callbackID withMessage:(NSString *)message
{
    CDVPluginResult *commandResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:message];
    [self.commandDelegate sendPluginResult:commandResult callbackId:callbackID];
}

- (void)failWithCallbackID:(NSString *)callbackID withMessage:(NSString *)message
{
    CDVPluginResult *commandResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:message];
    [self.commandDelegate sendPluginResult:commandResult callbackId:callbackID];
}

@end
