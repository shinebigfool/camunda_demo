package com.example.demo.service;

import java.util.Map;

public interface CamundaRunService {
    String startProcess(Map<String,Object> params);
    String assignTask(String processInstanceId);
    String approveTask(String taskId, boolean passed);
}
