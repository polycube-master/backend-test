package kr.co.polycube.backendtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LottoResponse {

    private List<Integer> numbers;
}
