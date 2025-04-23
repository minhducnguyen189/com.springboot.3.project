package com.springboot.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "websocket_login")
public class WebsocketLoginEntity extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "session_id")
    private String sessionId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "login_state")
    private LoginStateEnum loginState;
}
