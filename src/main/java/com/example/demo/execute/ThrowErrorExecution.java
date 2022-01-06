package com.example.demo.execute;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * @author: NXY
 * @date: 2022-01-06 13:38
 **/
public class ThrowErrorExecution implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("抛出异常");
        throw new Exception("111111");
//        throw new BpmnError("111111"); 不会触发事务
    }
}
