package com.example.demo.controller;

import com.example.demo.domain.TestParamDO;
import com.example.demo.external.ProcessExternalTaskService;
import com.example.demo.service.CamundaRunService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: NXY
 * @date: 2021-12-31 13:46
 **/
@RestController
public class CamundaDemoController {
    @Autowired
    private CamundaRunService camundaStartService;
    @Autowired
    private ProcessExternalTaskService processExternalTaskService;
    @GetMapping("/start")
    public String startProcess(@RequestParam Map<String,Object> params){
        return camundaStartService.startProcess(params);
    }
    @GetMapping("/assign")
    public String assignTask(@RequestParam String processInstanceId) {
        return camundaStartService.assignTask(processInstanceId);
    }
    @GetMapping("/approve")
    public String approveTask(@RequestParam String taskId, @RequestParam boolean passed) {
        return camundaStartService.approveTask(taskId, passed);
    }
    @GetMapping("/testQueryHistory")
    public String testQueryHistory(Map<String,Object> params){
        return camundaStartService.testQueryHistory(params);
    }
    @GetMapping("/test")
    public String startLinstenerBPMNDemo(){
        return camundaStartService.startLinstenerBPMNDemo();
    }
    @GetMapping("/external")
    public String processExternalTask(@Param("approve") boolean approve){
        processExternalTaskService.process(approve);
        return "success";
    }
    @PostMapping("/startParamDemo")
    public String startParamDemo(@RequestBody TestParamDO testParamDO){

        return camundaStartService.startParamDemo(testParamDO);
    }
}
