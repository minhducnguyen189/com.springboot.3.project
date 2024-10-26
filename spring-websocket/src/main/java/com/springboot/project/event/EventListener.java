package com.springboot.project.event;

public interface EventListener {

    void on(BaseEvent event, Object data);

}
