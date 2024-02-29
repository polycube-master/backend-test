package kr.co.polycube.backendtest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* 효율성을 위해서 로또번호를 생성하는 메소드 모듈화 */
public class NumberGenerator {
    public List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(45) + 1; // 1부터 45 사이의 난수 생성
            numbers.add(randomNumber);
        }
        return numbers;
    }
}