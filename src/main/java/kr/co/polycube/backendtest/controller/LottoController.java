package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lottos")
public class LottoController {

    private final LottoService lottoService;

    @Autowired
    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @PostMapping
    public Map<String, List<Integer>> generateLotto() {
        List<Integer> lottoNumbers = lottoService.generateLottoNumbers();
        return Collections.singletonMap("numbers", lottoNumbers);
    }
}
