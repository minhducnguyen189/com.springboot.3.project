package com.springboot.project.service;

import com.springboot.project.entity.WebsocketLoginEntity;
import com.springboot.project.repository.WebsocketLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WebSocketLoginService {

    private final WebsocketLoginRepository websocketLoginRepository;


    @Autowired
    public WebSocketLoginService(WebsocketLoginRepository websocketLoginRepository) {
        this.websocketLoginRepository = websocketLoginRepository;
    }

    public WebsocketLoginEntity getBySessionId(String sessionId) {
        return this.websocketLoginRepository.findBySessionId(sessionId);
    }

    public WebsocketLoginEntity getByUserId(long userId) {
        return this.websocketLoginRepository.findByUserId(userId);
    }

    public WebsocketLoginEntity save(WebsocketLoginEntity websocketLoginEntity) {
        return this.websocketLoginRepository.save(websocketLoginEntity);
    }

}
