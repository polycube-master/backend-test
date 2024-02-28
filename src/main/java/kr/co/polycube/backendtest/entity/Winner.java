package kr.co.polycube.backendtest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "winner")
@Getter
@Setter
@NoArgsConstructor
public class Winner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lotto_id")
    private Long lottoId; // 당첨 번호의 id

    private String rank;

    public Winner(Long id, Long lottoId, String rank){
        this.id=id;
        this.lottoId=lottoId;
        this.rank=rank;
    }
}
