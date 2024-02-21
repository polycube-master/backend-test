package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.Repository.LottoRepository;
import kr.co.polycube.backendtest.Repository.WinnerRepository;
import kr.co.polycube.backendtest.domain.Lotto;
import kr.co.polycube.backendtest.domain.Rank;
import kr.co.polycube.backendtest.domain.Winner;
import kr.co.polycube.backendtest.dto.response.LottoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LottoService {

    private final LottoRepository lottoRepository;
    private final WinnerRepository winnerRepository;

    public LottoResponse drawLotto() {

        Lotto lotto = new Lotto(getLotto());
        lottoRepository.save(lotto);

        return LottoResponse.from(lotto);
    }

    public void winLotto() {

        List<Lotto> lottos = lottoRepository.findAll();
        List<Integer> winNumbers = getLotto();
        Winner winner = new Winner();

        for (Lotto lotto : lottos) {
            lotto.getNumbers().retainAll(winNumbers);

            switch (lotto.getNumbers().size()) {
                case 6:
                    winner.saveWinner(lotto.getId(), Rank.FIRST);
                    winnerRepository.save(winner);
                    break;
                case 5:
                    winner.saveWinner(lotto.getId(), Rank.SECOND);
                    winnerRepository.save(winner);
                    break;
                case 4:
                    winner.saveWinner(lotto.getId(), Rank.THIRD);
                    winnerRepository.save(winner);
                    break;
                case 3:
                    winner.saveWinner(lotto.getId(), Rank.FOURTH);
                    winnerRepository.save(winner);
                    break;
                case 2:
                    winner.saveWinner(lotto.getId(), Rank.FIFTH);
                    winnerRepository.save(winner);
                    break;
                default:
                    break;
            }
        }

        lottoRepository.deleteAllInBatch();
    }

    private static List<Integer> getLotto() {

        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        List<Integer> winNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            winNumbers.add(numbers.get(i));
        }

        return winNumbers;
    }
}
