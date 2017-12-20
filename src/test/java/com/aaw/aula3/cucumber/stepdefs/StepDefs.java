package com.aaw.aula3.cucumber.stepdefs;

import com.aaw.aula3.AawAula3App;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = AawAula3App.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
