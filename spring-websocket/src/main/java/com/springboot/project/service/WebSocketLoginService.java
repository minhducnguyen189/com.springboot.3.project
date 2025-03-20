package com.springboot.project.service;

import com.springboot.project.entity.LoginStateEnum;
import com.springboot.project.entity.WebsocketLoginEntity;
import com.springboot.project.repository.WebsocketLoginRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSocketLoginService {

  @Getter private WebsocketLoginEntity currentLogin;

  private final WebsocketLoginRepository websocketLoginRepository;

  @Autowired
  public WebSocketLoginService(WebsocketLoginRepository websocketLoginRepository) {
    this.websocketLoginRepository = websocketLoginRepository;
  }

  public boolean isLogin(long userId) {
    WebsocketLoginEntity websocketLoginEntity = this.getByUserId(userId);
    if (websocketLoginEntity == null) {
      return false;
    }
    return websocketLoginEntity.getLoginState().equals(LoginStateEnum.ACTIVE);
  }

  public WebsocketLoginEntity getLogin(long userId) {
    return this.getByUserId(userId);
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
    this.save(websocketLoginEntity);
  }

  public void logout(String sessionId) {
    WebsocketLoginEntity websocketLoginEntity = this.getBySessionId(sessionId);
    if (websocketLoginEntity == null) {
      return;
    }
    websocketLoginEntity.setLoginState(LoginStateEnum.INACTIVE);
    this.save(websocketLoginEntity);
  }

  public void setCurrentLogin(WebsocketLoginEntity websocketLoginEntity) {
    this.currentLogin = websocketLoginEntity;
  }

  public WebsocketLoginEntity getWebsocketLogin(String id) {
    return this.getBySessionId(id);
  }

  private WebsocketLoginEntity getBySessionId(String sessionId) {
    return this.websocketLoginRepository.findBySessionId(sessionId);
  }

  private WebsocketLoginEntity getByUserId(long userId) {
    return this.websocketLoginRepository.findByUserId(userId);
  }

  public WebsocketLoginEntity save(WebsocketLoginEntity websocketLoginEntity) {
    return this.websocketLoginRepository.save(websocketLoginEntity);
  }
}
