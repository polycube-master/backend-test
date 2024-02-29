package kr.co.polycube.backendtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WinnerDTO {
    private Long id;
    private Long lottoId;
    private String rank;
}
