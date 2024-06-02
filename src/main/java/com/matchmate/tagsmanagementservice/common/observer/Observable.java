package com.matchmate.tagsmanagementservice.common.observer;

import com.matchmate.tagsmanagementservice.common.event.DomainEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter
public class Observable {

    private static class Observer {
        String eventName;
        Consumer<DomainEvent> callback;

        Observer(String eventName, Consumer<DomainEvent> callback) {
            this.eventName = eventName;
            this.callback = callback;
        }
    }
    
    private final List<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    public void register(String eventName, Consumer<DomainEvent> callback) {
        this.observers.add(new Observer(eventName, callback));
    }

    public void notify(DomainEvent event) {
        for (Observer observer : observers) {
            if (observer.eventName.equals(event.getName())) {
                observer.callback.accept(event);
            }
        }
    }
}