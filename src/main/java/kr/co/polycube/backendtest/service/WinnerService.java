package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.entity.User;
import kr.co.polycube.backendtest.entity.Winner;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WinnerService {
    private final WinnerRepository winnerRepository;
    ModelMapper modelMapper=new ModelMapper();
    @Autowired
    public WinnerService(WinnerRepository winnerRepository) {
        this.winnerRepository = winnerRepository;
    }

    public void saveWinner(Long lottoId, String rank){
        Winner winner=new Winner();
        winner.setLottoId(lottoId);
        winner.setRank(rank);
        winnerRepository.save(winner);
    }
}
