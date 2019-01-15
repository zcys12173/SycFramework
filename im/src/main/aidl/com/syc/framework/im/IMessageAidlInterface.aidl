// IMessageAidlInterface.aidl
package com.syc.framework.im;

// Declare any non-default types here with import statements
import com.syc.framework.im.bean.Message;
interface IMessageAidlInterface {


    void sendMessage(inout Message msg);
}
