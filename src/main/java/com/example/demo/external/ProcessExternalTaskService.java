package com.example.demo.external;

import org.camunda.bpm.engine.ExternalTaskService;
import org.camunda.bpm.engine.externaltask.LockedExternalTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author: NXY
 * @date: 2022-01-06 09:26
 **/
@Service
public class ProcessExternalTaskService {
    @Autowired
    private ExternalTaskService externalTaskService;
    /**
     * @description: 此处应该由其他系统调用rest api
     * 先查找指定主题的指定任务，然后完成任务
     * @param:
     * @return:
     */
    public void process(boolean approve){
        List<LockedExternalTask> taskList = externalTaskService.fetchAndLock(1, "externalWorkerId")
                .topic("AddressValidation", 60L * 1000L)
                .execute();
        if(taskList==null||taskList.size()==0){
            System.out.println("无外部任务");
            return;
        }
        if(approve){
            HashMap<String, Object> var = new HashMap<>();
            var.put("approve",true);
            externalTaskService.complete(taskList.get(0).getId(),"externalWorkerId",var);
        }else {
//            todo 任务失败如何捕获？
//            externalTaskService.handleFailure();
//            http://camunda-cn.shaochenfeng.com/user-guide/process-engine/expression-language/#%E5%A4%96%E9%83%A8%E4%BB%BB%E5%8A%A1%E9%94%99%E8%AF%AF%E5%A4%84%E7%90%86
            externalTaskService.handleBpmnError(taskList.get(0).getId(),
                    "externalWorkerId",
                    "0001",
                    "Address could not be validated: Address database not reachable");
        }
    }
}
