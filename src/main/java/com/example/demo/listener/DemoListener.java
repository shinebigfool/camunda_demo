package com.example.demo.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.Expression;

/**
 * @author: NXY
 * @date: 2022-01-05 15:34
 **/
public class DemoListener implements ExecutionListener {
    private Expression fixedValue;
    private Expression dynamicValue;
    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        System.out.println("listening-------");
        String value = fixedValue.getValue(delegateExecution).toString() +
                dynamicValue.getValue(delegateExecution).toString();
        delegateExecution.setVariable("var", value);
        System.out.println(value+"listening-------");
    }
}
