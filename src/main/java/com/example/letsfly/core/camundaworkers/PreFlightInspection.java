package com.example.letsfly.core.camundaworkers;

import com.example.letsfly.core.services.WeatherService;
import com.example.letsfly.core.services.dto.Weather;
import com.google.gson.Gson;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@ExternalTaskSubscription("preFlightInspection") // create a subscription for this topic name
public class PreFlightInspection implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        boolean isPassedPreFlightInspection;
        Random randomNum = new Random();
        int randomNumber = randomNum.nextInt(20);

        isPassedPreFlightInspection = ((randomNumber > 10) ? false : true);

        VariableMap variables = Variables.createVariables();

        variables.put("isPassedPFI", isPassedPreFlightInspection);

        externalTaskService.complete(externalTask,variables);

    }

}
