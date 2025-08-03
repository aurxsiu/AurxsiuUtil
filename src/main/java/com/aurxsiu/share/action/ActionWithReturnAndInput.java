package com.aurxsiu.share.action;

public interface ActionWithReturnAndInput<R,I> {
    public R act(I input);
}
