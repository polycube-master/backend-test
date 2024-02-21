package kr.co.polycube.backendtest.dto.response;

import kr.co.polycube.backendtest.domain.Lotto;

import java.util.List;

public record LottoResponse(
        List<Integer> numbers) {

    public static LottoResponse of(
            List<Integer> numbers
    ) {
        return new LottoResponse(numbers);
    }

    public static LottoResponse from(Lotto lotto) {
        return LottoResponse.of(
                lotto.getNumbers());
    }
}
