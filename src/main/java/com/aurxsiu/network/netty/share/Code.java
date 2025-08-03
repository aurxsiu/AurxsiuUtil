package com.aurxsiu.network.netty.share;

import com.aurxsiu.network.netty.handler.ManagerInHandler;
import com.aurxsiu.network.netty.handler.action.DelayResponseAction;
import com.aurxsiu.network.netty.handler.action.DelaySendAction;
import com.aurxsiu.share.action.ActionWithInput;

/**
 * 将消息分层,首位为int,用于判定类型,让handler判断是否可以处理,如果不能处理,传递给下一个handler
 * 所以,handler的顺序很重要,server和client的顺序必须一致
 * TODO:一层一层剥离.让一个handler来做管理员,识别类型然后分配给处理器
 * */
public enum Code {
    /**
     * 识别该code后需要将数据转发回发送方 {@link DELAY_SEND_LAYER},让发送方计算延迟
     * */
    DELAY_RESPONSE_LAYER(new DelayResponseAction()),

    /**
     * 识别该code后计算延迟
     * TODO:同时将延迟数据返回发送方
     * */
    DELAY_SEND_LAYER(new DelaySendAction())
    ;
    private final ActionWithInput<ManagerInHandler> action;

    private Code(ActionWithInput<ManagerInHandler> action) {
        this.action = action;
    }

    public ActionWithInput<ManagerInHandler> getAction(){
        return this.action;
    }
}
