package com.itis.servletsapp.database;
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
    private String email;
}
