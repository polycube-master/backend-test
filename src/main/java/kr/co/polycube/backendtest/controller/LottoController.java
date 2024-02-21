package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.dto.response.LottoResponse;
import kr.co.polycube.backendtest.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lottos")
public class LottoController {

    private final LottoService lottoService;

    @PostMapping
    public ResponseEntity<LottoResponse> generateLotto() {

        return ResponseEntity.ok().body(lottoService.drawLotto());
    }

    @Scheduled(cron = "0 0 * * 0")
    public void winLotto() {
        lottoService.winLotto();
    }
}