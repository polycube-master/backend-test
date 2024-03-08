package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.repository.LottoRepository;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LottoService {

    @Autowired
    private LottoRepository lottoRepository;

    @Autowired
    private WinnerRepository winnerRepository;

    public Lotto generateRandomLotto() {
        Lotto lotto = new Lotto();
        Random random = new Random();

        Set<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < 6) {
            uniqueNumbers.add(random.nextInt(45) + 1);
        }

        List<Integer> numberList = new ArrayList<>(uniqueNumbers);

        lotto.setNumber1(numberList.get(0));
        lotto.setNumber2(numberList.get(1));
        lotto.setNumber3(numberList.get(2));
        lotto.setNumber4(numberList.get(3));
        lotto.setNumber5(numberList.get(4));
        lotto.setNumber6(numberList.get(5));

        return lotto;
    }

    public Lotto saveLotto(Lotto lotto) {
        return lottoRepository.save(lotto);
    }
}
