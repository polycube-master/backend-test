package kr.co.polycube.backendtest.service;

import jakarta.transaction.Transactional;
import kr.co.polycube.backendtest.dto.LottoDTO;
import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.repository.LottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LottoService {
    private final LottoRepository lottoRepository;

    @Autowired
    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }
    
    public List<LottoDTO> getAllLottos(){
        List<Lotto> lottos=lottoRepository.findAll();
        return lottos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LottoDTO convertToDTO(Lotto lotto) {
        LottoDTO dto = new LottoDTO();
        dto.setId(lotto.getId());
        dto.setNumber1(lotto.getNumber1());
        dto.setNumber2(lotto.getNumber2());
        dto.setNumber3(lotto.getNumber3());
        dto.setNumber4(lotto.getNumber4());
        dto.setNumber5(lotto.getNumber5());
        dto.setNumber6(lotto.getNumber6());
        return dto;
    }
}
