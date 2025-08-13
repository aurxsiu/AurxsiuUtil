package com.aurxsiu.network.netty.handler.action;

import com.aurxsiu.network.netty.handler.ManagerInHandler;
import io.netty.buffer.ByteBuf;

import java.sql.SQLOutput;
import java.util.HashMap;

/**
 * 默认sout接收到的顺序,否则通过{@link #setResult_act(ActionWithCount)} )}来修改
 * 通过long计数,默认不会用光,所以不考虑超过最大值的问题
 * */

public class CountIndexAction extends Action {
    private final HashMap<ManagerInHandler,Long> before = new HashMap<>();
    public interface ActionWithCount{
        /**
         * input: 接收的序号
         * compareResult: 第一个序号和比上次大的序号为true*/
        public void act(long input,boolean compareResult);
    }
    private ActionWithCount result_act = (input, compareResult) -> System.out.println("message index: "+input+" |比上一次接收序号: "+(compareResult?"大":"小"));
    public CountIndexAction(){}

    public void setResult_act(ActionWithCount result_act) {
        this.result_act = result_act;
    }

    @Override
    public void readAction(ManagerInHandler input) {


        ByteBuf buf = input.getBuf();

        long l = buf.readLong();

        //netty应该在这里是单线程的,所以不担心线程问题

        before.putIfAbsent(input, l);

        result_act.act(l,l>=before.get(input));

        before.put(input,l);
    }

    @Override
    public void destroy(ManagerInHandler input) {
        before.remove(input);
    }
}
