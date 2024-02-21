package kr.co.polycube.backendtest.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "'winner'")
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long lotto_id;
    private Rank rank;

    public void saveWinner(Long lotto_id, Rank rank) {
        this.lotto_id = lotto_id;
        this.rank = rank;
    }
}
