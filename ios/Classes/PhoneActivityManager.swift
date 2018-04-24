//
//  PhoneActivityManager.swift
//  Blue Call
//
//  Created by Tamara Bernad on 2017-06-16.
//  Copyright © 2017 Blue Call. All rights reserved.
//

import Foundation
import UIKit

class PhoneActivityManager:NSObject{
    static let instance = PhoneActivityManager()
    var callManager:CallManageable?;
  
    private override init() {}
    
    func initialize(props: [String : Any]){
        self.callManager = SinchCallManager(props: props)
    }
  
    var inCall:Bool{
        get{
            guard let _inCall = callManager?.inCall else {return false}
            return _inCall
        }
        set(_inCall){
            callManager?.inCall = _inCall
        }
    }
    func login(_ userId:String){
        self.callManager?.login(userId)
    }
    func logout(){
        self.callManager?.logout()
    }
    func setDisplayName(_ name:String){
        self.callManager?.setDisplayName(name)
    }
    func answer(){
        self.callManager?.answer()
    }
    func hangup(){
        self.callManager?.hangup()
    }
    func sendMessage(params:MessageParams)->MessageParams?{
        return self.callManager?.sendMessage(params: params)
    }
    func terminate(){
        self.callManager?.terminate()
    }
    func terminateGracefully(){
        self.callManager?.terminateGracefully()
    }
  
    func application(_ application: UIApplication,
                     didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data){
        self.callManager?.application(application, didRegisterForRemoteNotificationsWithDeviceToken: deviceToken)
    }
    func application(_ application: UIApplication,
                     didReceiveRemoteNotification userInfo: [AnyHashable : Any]){
        self.callManager?.application(application, didReceiveRemoteNotification: userInfo)
    }
    func application(_ application: UIApplication,
                     didReceiveRemoteNotification userInfo: [AnyHashable : Any],
                     fetchCompletionHandler completionHandler: @escaping (UIBackgroundFetchResult) -> Void){
        self.callManager?.application(application, didReceiveRemoteNotification: userInfo, fetchCompletionHandler: completionHandler)
    }
    
    func call(with callParams:CallParams)->String?{
        return self.callManager?.call(with: callParams)
    }
   
}
