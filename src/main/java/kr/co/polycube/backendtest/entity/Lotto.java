package kr.co.polycube.backendtest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.retry.annotation.Backoff;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "lotto")
public class Lotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number_1")
    private int number1;
    @Column(name = "number_2")
    private int number2;
    @Column(name = "number_3")
    private int number3;
    @Column(name = "number_4")
    private int number4;
    @Column(name = "number_5")
    private int number5;
    @Column(name = "number_6")
    private int number6;

    public List<Integer> getNumbers() {
        return List.of(number1, number2, number3, number4, number5, number6);
    }
}
