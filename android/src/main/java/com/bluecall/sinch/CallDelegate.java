package com.bluecall.sinch;

/**
 * Created by tamarabernad on 2017-09-11.
 */

public interface CallDelegate {
    void didReceiveCall(String callId);
    void callEndedWithReason(String reason, int duration);
    void callDidEstablish();
    void callDidProgress();
    void callServiceStartupDidFailWithMessage(String message);
    void callServiceStartupDidSucceed();
    void callDidChangeStatus(String status);
}
