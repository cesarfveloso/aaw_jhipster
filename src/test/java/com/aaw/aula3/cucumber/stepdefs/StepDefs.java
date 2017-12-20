package com.aaw.aula3.cucumber.stepdefs;

import com.aaw.aula3.Aula3App;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = Aula3App.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
