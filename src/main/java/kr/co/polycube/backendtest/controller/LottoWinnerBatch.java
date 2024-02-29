package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.dto.LottoDTO;
import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.service.LottoService;
import kr.co.polycube.backendtest.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/*로또 번호 당첨자 검수 Batch*/
@Component
public class LottoWinnerBatch {

    private final LottoService lottoService;
    private final WinnerService winnerService;
    private final NumberGenerator numberGenerator = new NumberGenerator();

    // 필드 주입이 아닌 생성자 주입을 사용하면 의존성 주입의 명시적 처리 및 테스트 용이성 향상
    public LottoWinnerBatch(LottoService lottoService, WinnerService winnerService) {
        this.lottoService = lottoService;
        this.winnerService = winnerService;
    }

    @Scheduled(cron = "0 0 0 * * SUN")  // 매주 일요일 0시에 실행
    public void checkWinners(){
        List<LottoDTO> lottos=lottoService.getAllLottos();
        List<Integer> winningNumbers=numberGenerator.generateRandomNumbers(); //당첨번호생성
        for(LottoDTO lottoDTO: lottos){
            int matchingNumbers=countMatchingNumbers(lottoDTO,winningNumbers);
            // 3개 이상 일차하면 당첨
            if(matchingNumbers>=3){
                int rank=determineRank(matchingNumbers);
                winnerService.saveWinner(lottoDTO.getId(),rank+"등");
            }
        }
    }

    // 등수 정하는 메소드
    private int determineRank(int matchingNumbers) {
        return switch (matchingNumbers) {
            case 6 -> 1;
            case 5 -> 2;
            case 4 -> 3;
            case 3 -> 4;
            default -> 5;
        };
    }

    // 일치하는 숫자 세는 메소드
    private int countMatchingNumbers(LottoDTO lottoDTO, List<Integer> winningNumbers) {
        int count=0;
        List<Integer> numbers= Arrays.asList(lottoDTO.getNumber1(), lottoDTO.getNumber2(), lottoDTO.getNumber3(),
                lottoDTO.getNumber4(), lottoDTO.getNumber5(), lottoDTO.getNumber6());
        for (int number: numbers){
            if(winningNumbers.contains(number)){
                count++;
            }
        }
        return count;
    }

}
