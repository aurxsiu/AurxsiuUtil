package com.aurxsiu.network.netty.handler.action;

import com.aurxsiu.network.netty.handler.ManagerInHandler;

public class Action {

    /**
     * 当ManagerHandler读取数据识别Code后调用
     * 通过{@link ManagerInHandler#getBuf()}获取内容
     * 通过{@link ManagerInHandler#getToMsg()}去写入要响应回去的内容
     * */
    public void readAction(ManagerInHandler input){

    }
    /**
     * 当channel被销毁时调用
     * */
    public void destroy(ManagerInHandler input){

    }
}
