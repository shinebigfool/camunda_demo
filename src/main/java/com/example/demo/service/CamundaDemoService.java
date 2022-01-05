package com.example.demo.service;

import org.camunda.bpm.engine.delegate.JavaDelegate;

public interface CamundaDemoService extends JavaDelegate {
    void execute();
}
