package com.example.demo.execute;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * @author: NXY
 * @date: 2021-12-31 10:07
 **/
public class SendMessageExecution implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.getProcessEngineServices()
                .getRuntimeService()
                .createMessageCorrelation("message")
                .processInstanceBusinessKey("messageBusinessKey")
                .correlate();
    }
}
