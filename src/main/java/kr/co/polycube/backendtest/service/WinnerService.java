package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.entity.Winner;
import kr.co.polycube.backendtest.repository.LottoRepository;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WinnerService {

    private final WinnerRepository winnerRepository;
    private final LottoRepository lottoRepository;

    @Autowired
    public WinnerService(WinnerRepository winnerRepository, LottoRepository lottoRepository) {
        this.winnerRepository = winnerRepository;
        this.lottoRepository = lottoRepository;
    }

    // 당첨 번호 임의로 설정
    private final List<Integer> winningNumbers = List.of(1,7,28,33,2,45,19);

    public Winner processLotto(Lotto lotto) {
        int matchCount = 0;

        // 로또 번호와 당첨 번호를 비교하여 일치하는 번호의 개수를 계산
        for (int number : lotto.getNumbers()) {
            if (winningNumbers.contains(number)) {
                matchCount++;
            }
        }

        // 일치하는 번호의 개수를 기반으로 당첨 등수를 결정
        int rank = determineRank(matchCount);

        // 당첨된 경우 Winner 객체를 생성하고 반환 (등수별로 나뉨)
        if (rank > 0) {
            return Winner.builder()
                    .lottoId(lotto.getId())
                    .rank(rank)
                    .build();
        } else {
            // 당첨 X
            return null;
        }
    }

    private int determineRank(int matchCount) {
        return switch (matchCount) {
            case 6 -> 1;
            case 5 -> 2;
            case 4 -> 3;
            case 3 -> 4;
            case 2 -> 5;
            default -> 0;
        };
    }
}
