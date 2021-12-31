package com.example.demo.service.impl;

import com.example.demo.service.CamundaRunService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: NXY
 * @date: 2021-12-31 13:51
 **/
@Service
public class CamundaRunServiceImpl implements CamundaRunService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Override
    public String startProcess(Map<String, Object> params) {
        Execution execution = runtimeService.startProcessInstanceByKey("approve_process", params);
        System.out.println("实例启动成功，实例ID：" + execution.getProcessInstanceId());
        return "实例启动成功，实例ID：" + execution.getProcessInstanceId();
    }

    @Override
    public String assignTask(String processInstanceId) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).active().list();
        if (!CollectionUtils.isEmpty(tasks)) {
            taskService.claim(tasks.get(0).getId(), "admin");
            System.out.println("任务认领成功，任务ID：" + tasks.get(0).getId() + "，认领人：admin");
            return "任务认领成功，任务ID：" + tasks.get(0).getId() + "，认领人：admin";
        }
        System.out.println("无任务可认领");
        return "无任务可认领";
    }

    @Override
    public String approveTask(String taskId, boolean passed) {
        List<Task> tasks = taskService.createTaskQuery().taskId(taskId).taskAssignee("admin").list();
        if (!CollectionUtils.isEmpty(tasks)) {
            Map<String, Object> approveVariables = new HashMap<>(1);
            approveVariables.put("passed", passed);
            taskService.complete(taskId, approveVariables);
            System.out.println("任务审核完成，审核"
                    + (passed ? "通过" : "拒绝"));
            return "任务审核完成，审核"
                    + (passed ? "通过" : "拒绝");
        }
        System.out.println("无任务可审核");
        return "无任务可审核";
    }
}
