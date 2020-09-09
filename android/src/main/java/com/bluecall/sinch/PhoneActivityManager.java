package com.bluecall.sinch;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.sinch.android.rtc.calling.Call;

/**
 * Created by tamarabernad on 2017-09-07.
 */

public class PhoneActivityManager implements ServiceConnection {

    private SinchService.SinchServiceInterface mSinchServiceInterface;
    public CallDelegate mCallDelegate;
    public MessageDelegate mMessageDelegate;

    private static PhoneActivityManager instance = null;
    protected PhoneActivityManager() {
        // Exists only to defeat instantiation.
    }
    public void setContext(Context context){

        if(mSinchServiceInterface != null){
            context.unbindService(this);
        }
        context.bindService(new Intent(context, SinchService.class), this,
                Context.BIND_AUTO_CREATE);
    }
    public static PhoneActivityManager getInstance() {
        if(instance == null) {
            instance = new PhoneActivityManager();
        }
        return instance;
    }
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (SinchService.class.getName().equals(componentName.getClassName())) {
            mSinchServiceInterface = (SinchService.SinchServiceInterface) iBinder;
            mSinchServiceInterface.setCallDelegate(mCallDelegate);
            mSinchServiceInterface.setMessageDelegate(mMessageDelegate);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        if (SinchService.class.getName().equals(componentName.getClassName())) {
            mSinchServiceInterface = null;
        }
    }

    public SinchService.SinchServiceInterface getSinchServiceInterface() {
        return mSinchServiceInterface;
    }

    public void login(String username){
        if (!getSinchServiceInterface().isStarted()) {
            getSinchServiceInterface().startClient(username);
        }
    }
    public void setDisplayName(String displayName){getSinchServiceInterface().setDisplayName(displayName);}
    public void call(String username, Callback callback){
        SinchService.SinchServiceInterface sinchServiceInt = getSinchServiceInterface();


        WritableMap map = Arguments.createMap();
        map.putBoolean("isError", true);
        map.putString("message", "Sinch service was not ready");

        if(sinchServiceInt == null){
            callback.invoke(map);
            return;
        }

        Call call = sinchServiceInt.callUser(username);
        if(call == null){
            callback.invoke(map);
            return;
        }

        String callId = call.getCallId();

        map.putBoolean("isError", false);
        map.putString("message", "");
        map.putString("callId", callId);

        callback.invoke(map);
    }

    public void answer() {
        getSinchServiceInterface().answer();
    }

    public void hangup() {
        getSinchServiceInterface().hangup();
    }

    public void terminate() {
        getSinchServiceInterface().terminate();
    }
    public void terminateGracefully() {
        getSinchServiceInterface().terminateGracefully();
    }

    public void sendMessage(String recipientUserId, String textBody, ReadableMap headers, Callback callback) {
        getSinchServiceInterface().sendMessage(recipientUserId, textBody,headers, callback);
    }
}
