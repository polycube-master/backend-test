package kr.co.polycube.backendtest.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "'user'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String userName;

    private User(String userName) {
        this.userName = userName;
    }

    public static User of(String userName) {
        return new User(userName);
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }
}
