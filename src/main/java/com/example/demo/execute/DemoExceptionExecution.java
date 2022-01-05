package com.example.demo.execute;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * @author: NXY
 * @date: 2022-01-05 16:09
 **/
public class DemoExceptionExecution implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("异常处理-----------");
    }
}
