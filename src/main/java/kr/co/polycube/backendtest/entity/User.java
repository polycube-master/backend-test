package kr.co.polycube.backendtest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="`User`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String name;
}
