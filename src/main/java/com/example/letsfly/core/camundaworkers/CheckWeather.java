package com.example.letsfly.core.camundaworkers;

import com.example.letsfly.core.services.WeatherService;
import com.example.letsfly.core.services.dto.Weather;
import com.google.gson.Gson;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.camunda.bpm.client.task.impl.dto.BpmnErrorRequestDto;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ExternalTaskSubscription("checkWeather") // create a subscription for this topic name
public class CheckWeather implements ExternalTaskHandler {

    private final WeatherService weatherService;

    @Autowired
    public CheckWeather( WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {

        Weather weather = weatherService.getWeather();

        boolean isGoodWeather;

        isGoodWeather = ((weather.getWindSpeed() > 10) ? false : true);

        VariableMap variables = Variables.createVariables();

        variables.put("isGoodWeather", isGoodWeather);

        if(weather.getWindSpeed() > 14){
            externalTaskService.handleBpmnError(externalTask , "Simulated_Error");
        }

        externalTaskService.complete(externalTask,variables);

    }

}
