package com.example.demo.execute;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * @author: NXY
 * @date: 2022-01-05 15:40
 **/
public class DemoListenerExecution implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("do java task~~~~");
        // todo 测试异常路径用
        throw new BpmnError("111111");
    }
}
