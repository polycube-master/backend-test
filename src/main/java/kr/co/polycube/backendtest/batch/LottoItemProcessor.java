package kr.co.polycube.backendtest.batch;

import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.entity.Winner;
import kr.co.polycube.backendtest.service.LottoService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LottoItemProcessor implements ItemProcessor<Lotto, Winner> {

    private final LottoService lottoService;

    public LottoItemProcessor(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @Override
    public Winner process(Lotto lotto) {
        Lotto winningNumbers = lottoService.generateRandomLotto();
        int matchedNumbers = countMatchedNumbers(lotto, winningNumbers);

        if (matchedNumbers == 6) {
            return new Winner(lotto, 1);
        } else if (matchedNumbers == 5) {
            return new Winner(lotto, 2);
        } else if (matchedNumbers == 4) {
            return new Winner(lotto, 3);
        } else if (matchedNumbers == 3) {
            return new Winner(lotto, 4);
        } else if (matchedNumbers == 2) {
            return new Winner(lotto, 5);
        } else {
            return null;
        }
    }

    private int countMatchedNumbers(Lotto lotto, Lotto winningNumbers) {
        Set<Integer> lottoNumbers = Set.of(
                lotto.getNumber1(), lotto.getNumber2(), lotto.getNumber3(),
                lotto.getNumber4(), lotto.getNumber5(), lotto.getNumber6()
        );

        Set<Integer> winningNumbersSet = Set.of(
                winningNumbers.getNumber1(), winningNumbers.getNumber2(),
                winningNumbers.getNumber3(), winningNumbers.getNumber4(),
                winningNumbers.getNumber5(), winningNumbers.getNumber6()
        );

        lottoNumbers.retainAll(winningNumbersSet); // 교집합 계산
        return lottoNumbers.size();
    }
}
