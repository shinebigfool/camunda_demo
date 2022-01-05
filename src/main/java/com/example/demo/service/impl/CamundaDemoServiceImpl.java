package com.example.demo.service.impl;

import com.example.demo.mapper.CamundaDemoMapper;
import com.example.demo.service.CamundaDemoService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * expression+springbean-----audit.bpmn
 * todo
 * @author: NXY
 * @date: 2021-12-31 09:08
 **/
@Service("camundaDemoServiceImpl")
public class CamundaDemoServiceImpl implements CamundaDemoService {
    @Resource
    private CamundaDemoMapper camundaDemoMapper;
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("bean--------");
        System.out.println(camundaDemoMapper.testSelect());
    }

    @Override
    public void execute() {
        System.out.println("bean--------");
        List<Map<String, Object>> maps = camundaDemoMapper.testSelect();
        System.out.println(maps);
    }
}
