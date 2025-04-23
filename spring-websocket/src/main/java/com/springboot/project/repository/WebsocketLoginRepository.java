package com.springboot.project.repository;

import com.springboot.project.entity.WebsocketLoginEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsocketLoginRepository extends JpaRepository<WebsocketLoginEntity, UUID> {

    WebsocketLoginEntity findByUserId(long userId);

    WebsocketLoginEntity findBySessionId(String sessionId);
}
