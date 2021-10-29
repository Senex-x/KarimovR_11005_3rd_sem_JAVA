package com.itis.servletsapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Long avatarId;
}