package com.sp.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.app.domain.Lotto;
import com.sp.app.domain.mylotto;
import com.sp.app.repository.LottoRepository;
import com.sp.app.repository.LottoTableRepository;

import jakarta.transaction.Transactional;

@Service
public class LottoService {
	
	@Autowired
	private LottoRepository lottoRepository;
	
	@Autowired
	private LottoTableRepository lottoTableRepository;
	
	
    public LottoService(LottoRepository lottoRepository, LottoTableRepository lottoTableRepository) {
        this.lottoRepository = lottoRepository;
        this.lottoTableRepository = lottoTableRepository;
    }
    
    

    @Transactional
    public List<Lotto> saveLottoNumbersInOrder() {
        List<List<Long>> lottoNumbersList = Arrays.asList(
                Arrays.asList(1L, 7L, 28L, 33L, 2L, 45L),
                Arrays.asList(2L, 26L, 14L, 41L, 3L, 22L),
                Arrays.asList(3L, 15L, 29L, 38L, 6L, 44L),
                Arrays.asList(4L, 31L, 16L, 42L, 9L, 23L),
                Arrays.asList(5L, 17L, 30L, 39L, 10L, 45L)
        );

        List<Lotto> savedLottoList = new ArrayList<>();

        for (List<Long> numbers : lottoNumbersList) {
            Lotto lotto = new Lotto();
            lotto.setNumber_1(numbers.get(0));
            lotto.setNumber_2(numbers.get(1));
            lotto.setNumber_3(numbers.get(2));
            lotto.setNumber_4(numbers.get(3));
            lotto.setNumber_5(numbers.get(4));
            lotto.setNumber_6(numbers.get(5));
            savedLottoList.add(lottoTableRepository.save(lotto));
        }

        return savedLottoList;
    }

    public void saveLottoNumbers(mylotto lotto) {
    	
    	try {
			
    		lottoRepository.save(lotto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }
    



	

}
