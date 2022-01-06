package com.example.demo.service.impl;

import com.example.demo.service.CamundaRunService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricCaseInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstanceReport;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

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
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
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

    @Override
    public String testQueryHistory(Map<String, Object> params) {
        // bpmn整个图的id=》processDefinitionKey
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery()
                .finished()
                .processDefinitionKey("approve_process")
                .variableValueEquals("role","NXY")
                .orderByProcessInstanceDuration().desc()
                .listPage(0, 10);
        // 启动任务的实例id
        List<HistoricProcessInstance> historicProcessInstances1 = historyService.createHistoricProcessInstanceQuery()
                .finished()
                .processInstanceId("e4793682-6dc0-11ec-bf9e-00ff0d0979a2")
                .orderByProcessInstanceDuration().desc()
                .listPage(0, 10);
        // ?
        List<HistoricCaseInstance> task_1afpv9z = historyService.createHistoricCaseInstanceQuery()
                .closed()
                .caseDefinitionKey("Task_1afpv9z")
                .orderByCaseInstanceDuration().desc()
                .listPage(0, 10);
        //
        List<HistoricCaseInstance> task_1afpv9z1 = historyService.createHistoricCaseInstanceQuery()
                .closed()
                .caseDefinitionId("Task_1afpv9z")
                .orderByCaseInstanceDuration().desc()
                .listPage(0, 10);
        // 参数列表
        List<HistoricVariableInstance> approve_process = historyService.createHistoricVariableInstanceQuery()
                .processDefinitionKey("approve_process")
                .processInstanceId("e4793682-6dc0-11ec-bf9e-00ff0d0979a2")
                .orderByVariableName().desc()
                .list();
        // ?
        HistoricProcessInstanceReport historicProcessInstanceReport = historyService
                .createHistoricProcessInstanceReport();
        // http://camunda-cn.shaochenfeng.com/user-guide/process-engine/incidents/
        List<Incident> list = runtimeService.createIncidentQuery().list();

        return "success";
    }

    @Override
    public String startLinstenerBPMNDemo() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("myVar", " listening!");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("listener_demo", variables);
        Object varSetByListener = runtimeService.getVariable(processInstance.getId(), "var");
        System.out.println(varSetByListener);
        return "success";
    }
}
