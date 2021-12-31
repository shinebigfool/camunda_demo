package com.example.demo.execute;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * @author: NXY
 * @date: 2021-12-31 10:52
 **/
public class BusinessRefuseExecution implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("拒绝处理----------");
    }
}
