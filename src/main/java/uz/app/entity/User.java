package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.app.enums.Role;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String surname;
    private String username;
    private String password;
    private Double balance = 0D;
    private Role role;
}
