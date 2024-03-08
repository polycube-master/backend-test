package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.dto.LottoResponse;
import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lottos")
public class LottosController {

    @Autowired
    private LottoService lottoService;

    @PostMapping
    public ResponseEntity<LottoResponse> generateLotto() {
        Lotto lotto = lottoService.generateRandomLotto();
        Lotto savedLotto = lottoService.saveLotto(lotto);
        List<Integer> numbers = Arrays.asList(savedLotto.getNumber1(), savedLotto.getNumber2(),
                savedLotto.getNumber3(), savedLotto.getNumber4(), savedLotto.getNumber5(),
                savedLotto.getNumber6());
        return ResponseEntity.ok(new LottoResponse(numbers));
    }
}
