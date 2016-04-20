package com.trifork.axon.quickstart;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

public class ToDoItem extends AbstractAnnotatedAggregateRoot {

    // The aggregate class [ToDoItem] does not specify an Identifier, identified with @AggregateIdentifier
    @AggregateIdentifier
    private String id;

    // No-arg constructor, required by Axon
    public ToDoItem() {
    }

    // The apply() method tells Axon that we want to apply this Event to the aggregate, have it stored
    // and published to other components
    @CommandHandler
    public ToDoItem(CreateToDoItemCommand command) {
        apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()));
    }

    @CommandHandler
    public void markCompleted(MarkCompletedCommand command) {
        apply(new ToDoItemCompletedEvent(id));
    }

    // error: Make sure the Aggregate Identifier is initialized before registering events.
    // EventSourcingHandler vs EventHandler
    @EventHandler
    public void on(ToDoItemCreatedEvent event) {
        this.id = event.getTodoId();
    }

}
