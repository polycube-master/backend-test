package kr.co.polycube.backendtest.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "'lotto'")
public class Lotto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ElementCollection
    @Column(name = "lotto_numbers")
    private List<Integer> numbers = new ArrayList<>();

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
