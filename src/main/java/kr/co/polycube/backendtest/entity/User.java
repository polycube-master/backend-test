package kr.co.polycube.backendtest.entity;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
