package com.example.myapplication.treeutil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)//注解生命在什么地方
@Retention(RetentionPolicy.RUNTIME)//注解在什么地方可见
public @interface TreeNodeLabel {

}
