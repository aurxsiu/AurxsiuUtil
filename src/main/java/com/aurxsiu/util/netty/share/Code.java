package com.aurxsiu.util.netty.share;

import com.aurxsiu.util.netty.handler.ManagerInHandler;
import com.aurxsiu.util.netty.handler.action.Action;
import com.aurxsiu.util.netty.handler.action.CountIndexAction;
import com.aurxsiu.util.netty.handler.action.DelayResponseAction;
import com.aurxsiu.util.netty.handler.action.DelaySendAction;

/**
 * {@link ManagerInHandler}通过Code识别需要调用的{@link Action}
 * 为了方便修改拓展,可以通过{@link #setAction(Action)}来控制实际action.
 * */
public enum Code {
    /**
     * 识别该code后需要将数据转发回发送方 {@link #DELAY_SEND_LAYER},让发送方计算延迟
     * */
    DELAY_RESPONSE_LAYER(new DelayResponseAction()),

    /**
     * 识别该code后计算延迟
     * TODO:同时将延迟数据返回发送方
     * */
    DELAY_SEND_LAYER(new DelaySendAction()),

    COUNT_INDEX_LAYER(new CountIndexAction())
    ;
    private Action action;

    private Code(Action action) {
        this.action = action;
    }

    public Action getAction(){
        return this.action;
    }

    public void setAction(Action action){
        this.action = action;
    }
}
