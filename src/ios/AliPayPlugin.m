//
//  AliPayPlugin.m
//  patient
//
//  Created by 郭大鹏 on 16/8/31.
//
//

#import "AliPayPlugin.h"
#import <AlipaySDK/AlipaySDK.h>

@implementation AlipayPlugin

-(void)pluginInitialize{
    CDVViewController *viewController = (CDVViewController *)self.viewController;
    self.partner = [viewController.settings objectForKey:@"partner"];
}

- (void) pay:(CDVInvokedUrlCommand*)command{

}

@end