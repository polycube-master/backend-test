package kr.co.polycube.backendtest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "`winner`")
@NoArgsConstructor
public class Winner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lotto_id")
    private Lotto lotto;

    private Integer rank;

    public Winner(Lotto lotto, Integer rank) {
        this.lotto = lotto;
        this.rank = rank;
    }
}
