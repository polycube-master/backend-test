package kr.co.polycube.backendtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class LottoController {
    @PostMapping("/lottos")
    public ResponseEntity<Map<String, List<Integer>>> generateLottoNumbers() {
        List<Integer> numbers = generateRandomNumbers();
        Map<String, List<Integer>> response = new HashMap<>();
        response.put("numbers", numbers);
        return ResponseEntity.ok(response);
    }

    private List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(45) + 1; // 1부터 45 사이의 난수 생성
            numbers.add(randomNumber);
        }
        return numbers;
    }
}
