package kr.co.polycube.backendtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LottoController {
    private final NumberGenerator numberGenerator = new NumberGenerator();
    @PostMapping("/lottos")
    public ResponseEntity<Map<String, List<Integer>>> generateLottoNumbers() {
        List<Integer> numbers = numberGenerator.generateRandomNumbers();
        Map<String, List<Integer>> response = new HashMap<>();
        response.put("numbers", numbers);
        return ResponseEntity.ok(response);
    }

}
