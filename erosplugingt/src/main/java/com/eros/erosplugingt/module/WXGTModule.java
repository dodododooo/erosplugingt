package com.eros.erosplugingt.module;


import android.util.Log;

import com.alibaba.weex.plugin.annotation.WeexModule;
import com.eros.erosplugingt.manager.GetuiManager;
import com.eros.framework.constant.WXEventCenter;
import com.eros.framework.manager.ManagerFactory;
import com.eros.framework.manager.impl.dispatcher.DispatchEventManager;
import com.eros.framework.model.WeexEventBean;
import com.eros.framework.utils.JsPoster;
import com.igexin.sdk.PushManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.Map;

import static com.eros.framework.constant.WXEventCenter.EVENT_GETCID;

/**
 * Created by liuyuanxiao on 18/4/10.
 */

@WeexModule(name = "bmPush", lazyLoad = true)
public class WXGTModule extends WXModule {
    private static final String TAG = "GPush";
    @JSMethod
    public void initPush(String parms) {
        Log.d(TAG, "initPush: " + parms);
        GetuiManager.pushInit(mWXSDKInstance.getContext());
    }

    /**
     * 获取个推的Cid
     */
    @JSMethod
    public void getCid(JSCallback callback) {

        WeexEventBean weexEventBean = new WeexEventBean();
        weexEventBean.setKey(EVENT_GETCID);
        weexEventBean.setContext(mWXSDKInstance.getContext());
        weexEventBean.setJscallback(callback);
        ManagerFactory.getManagerService(DispatchEventManager.class).getBus().post(weexEventBean);
    }

    @JSMethod
    public void turnOffPush () {
        PushManager.getInstance().turnOffPush(mWXSDKInstance.getContext());
    }
    @JSMethod
    public void turnOnPush () {
        PushManager.getInstance().turnOnPush(mWXSDKInstance.getContext());
    }
    @JSMethod
    public void isPushTurnedOn (JSCallback callback) {
        try {
            Boolean isOn = PushManager.getInstance().isPushTurnedOn(mWXSDKInstance.getContext());
            JsPoster.postSuccess(isOn, callback);
        } catch (Exception e) {
            JsPoster.postFailed(callback);
        }

    }
}
