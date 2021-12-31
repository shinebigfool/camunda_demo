package com.example.demo.service.impl;

import com.example.demo.mapper.CamundaDemoMapper;
import com.example.demo.service.CamundaDemoService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: NXY
 * @date: 2021-12-31 09:08
 **/
//@Service
public class CamundaDemoServiceImpl implements CamundaDemoService {
//    @Resource
//    private CamundaDemoMapper camundaDemoMapper;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("审核流程--------回调");
//        System.out.println(camundaDemoMapper.testSelect());
    }
}
