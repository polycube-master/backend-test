package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.repository.LottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LottoService {

    private final LottoRepository lottoRepository;

    @Autowired
    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public List<Integer> generateLottoNumbers() {
        final int LOTTO_NUMBERS_SIZE = 6;
        final int MAX_LOTTO_NUMBER = 45;

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, LOTTO_NUMBERS_SIZE);
    }

//    // 데이터베이스에 로또 번호 저장
//    public Lotto saveLottoNumbers(List<Integer> numbers) {
//        Lotto lotto = Lotto.builder()
//                .number1(numbers.get(0))
//                .number2(numbers.get(1))
//                .number3(numbers.get(2))
//                .number4(numbers.get(3))
//                .number5(numbers.get(4))
//                .number6(numbers.get(5))
//                .build();
//
//        return lottoRepository.save(lotto);
//    }

}
