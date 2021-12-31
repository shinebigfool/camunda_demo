package com.example.demo.controller;

import com.example.demo.service.CamundaRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: NXY
 * @date: 2021-12-31 13:46
 **/
@RestController
public class CamundaDemoController {
    @Autowired
    private CamundaRunService camundaStartService;
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
}
