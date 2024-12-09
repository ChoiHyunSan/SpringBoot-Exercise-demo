package SpringBoot_Exercise.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String password;

    @Column(unique = true)
    private String email;

    protected SiteUser() {
    }

    public SiteUser(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
