package com.example.demo.service;

import com.example.demo.domain.TestParamDO;

import java.util.Map;

public interface CamundaRunService {
    String startProcess(Map<String,Object> params);
    String assignTask(String processInstanceId);
    String approveTask(String taskId, boolean passed);
    String testQueryHistory(Map<String,Object> params);
    String startLinstenerBPMNDemo();
    String startParamDemo(TestParamDO testParamDO);
}
