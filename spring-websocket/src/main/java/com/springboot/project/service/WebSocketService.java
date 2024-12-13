package com.springboot.project.service;

import com.springboot.project.entity.LoginStateEnum;
import com.springboot.project.entity.WebsocketLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final WebSocketLoginService websocketLoginService;


    @Autowired
    public WebSocketService(WebSocketLoginService websocketLoginService) {
        this.websocketLoginService = websocketLoginService;
    }

    public boolean isLogin(long userId) {
        WebsocketLoginEntity websocketLoginEntity = websocketLoginService.getLogin(userId);
        if (websocketLoginEntity == null) {
            return false;
        }
        return websocketLoginEntity.getLoginState().equals(LoginStateEnum.ACTIVE);
    }

    public WebsocketLoginEntity getLogin(long userId) {
        return websocketLoginService.getLogin(userId);
    }

    public void login(String id, long userId) {
        WebsocketLoginEntity websocketLoginEntity = this.getLogin(userId);
        if (websocketLoginEntity == null) {
            websocketLoginEntity =
                    WebsocketLoginEntity.builder()
                            .userId(userId)
                            .sessionId(id)
                            .loginState(LoginStateEnum.ACTIVE)
                            .build();
        } else {
            websocketLoginEntity.setSessionId(id);
            websocketLoginEntity.setLoginState(LoginStateEnum.ACTIVE);
        }
        websocketLoginService.save(websocketLoginEntity);
    }

    public void logout(String sessionId) {
        WebsocketLoginEntity websocketLoginEntity = websocketLoginService.getWebsocketLogin(sessionId);
        if (websocketLoginEntity == null) {
            return;
        }
        websocketLoginEntity.setLoginState(LoginStateEnum.INACTIVE);
        websocketLoginService.save(websocketLoginEntity);
    }

    public WebsocketLoginEntity getWebsocketLogin(String id) {
        return websocketLoginService.getWebsocketLogin(id);
    }

}
