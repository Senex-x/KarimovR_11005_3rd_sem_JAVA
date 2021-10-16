package com.itis.servletsapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class User {
    private Long id;
    private String username;
    private String password;
    private Long avatarId;
}