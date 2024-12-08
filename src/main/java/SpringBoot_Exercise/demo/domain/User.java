package SpringBoot_Exercise.demo.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String password;

    @Column(unique = true)
    private String email;

    protected User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
