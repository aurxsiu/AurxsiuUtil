package com.aurxsiu.util.logAno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface Rate {
    /**
     * 做题的历程
     * */
    String experience();
    /**
     * 对下次刷题的建议,怎么刷,是否需要刷
     * */
    String advice();
    /**
     * 注意事项
     * */
    String attention();

    String questionDetail() default "";

}