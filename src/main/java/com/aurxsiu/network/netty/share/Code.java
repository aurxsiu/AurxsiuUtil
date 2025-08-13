package com.aurxsiu.network.netty.share;

import com.aurxsiu.network.netty.handler.ManagerInHandler;
import com.aurxsiu.network.netty.handler.action.Action;
import com.aurxsiu.network.netty.handler.action.CountIndexAction;
import com.aurxsiu.network.netty.handler.action.DelayResponseAction;
import com.aurxsiu.network.netty.handler.action.DelaySendAction;

/**
 * {@link ManagerInHandler}通过Code识别需要调用的{@link Action}
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
    private final Action action;

    private Code(Action action) {
        this.action = action;
    }

    public Action getAction(){
        return this.action;
    }
}
