package com.trifork.axon.quickstart;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

public class ToDoItemRunner {

    private CommandGateway commandGateway;

    public ToDoItemRunner(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        ToDoItemRunner runner = new ToDoItemRunner(applicationContext.getBean(CommandGateway.class));
        runner.run();
    }

    private void run() {
        final String itemId = UUID.randomUUID().toString();

        commandGateway.send(new CreateToDoItemCommand(itemId, "Need to do this"));
        commandGateway.send(new MarkCompletedCommand(itemId));
    }

}
