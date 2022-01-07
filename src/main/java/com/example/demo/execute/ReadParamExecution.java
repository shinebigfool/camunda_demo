package com.example.demo.execute;

import com.example.demo.domain.TestParamDO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * @author: NXY
 * @date: 2022-01-07 17:04
 **/
public class ReadParamExecution implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("read params---------");
        TestParamDO param = (TestParamDO)execution.getVariable("param");
        System.out.println(param);
    }
}
