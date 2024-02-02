package com.sp.app.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.app.domain.Lotto;
import com.sp.app.domain.User;
import com.sp.app.domain.mylotto;
import com.sp.app.service.LottoService;
import com.sp.app.service.UserService;

@RestController
public class UserController {
	// 이름 허용 가능 글자
	private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789?&=://";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LottoService lottoService;
	
	@GetMapping("/users")
	public ResponseEntity<Map<String, Long>> register() {
	    User user = new User();
	    // 랜덤 id generate
	    Random random = new Random();
	    long randomId = Math.abs(random.nextLong() % 100000);
	    
	    // 랜덤 name generate
	    int randomlen = 5;
	    StringBuilder randomname = new StringBuilder(randomlen);
	    
	    for (int i=0; i < randomlen; i++) {
	    	 int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
	         char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
	         randomname.append(randomChar);
	    }
	    
	    
	    user.setId(randomId);
	    user.setName(randomname.toString());
	   	
	    userService.saveUser(user);
	    Map<String, Long> response = new HashMap<>();
	    response.put("id", user.getId());

	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") long id) {
        User user = userService.getUserById(id);


        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());

        return ResponseEntity.ok(response);
    }
	
	@PostMapping("/users/{id}")
	public ResponseEntity<Map<String, Object>> update(@PathVariable("id") long id, User dto){
		
		userService.update(dto);
		User user = userService.getUserById(id);
		
		Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
		
		
        return ResponseEntity.ok(response);
	}
	
	@GetMapping("/lotto")
    public ResponseEntity<Map<String, Long[]>> lotto() {
		// 기존 로또 
		List<Lotto> savedLottoList = lottoService.saveLottoNumbersInOrder();
		
		
		
		// 당첨 로또 
		mylotto mlotto = new mylotto();
        long[] lottoNumbers = generateSortedLottoNumbers();
        Random random = new Random();
	    long randomId = Math.abs(random.nextLong() % 100000);
        mlotto.setId(randomId);
        
        mlotto.setNumber_1(lottoNumbers[0]);
        mlotto.setNumber_2(lottoNumbers[1]);
        mlotto.setNumber_3(lottoNumbers[2]);
        mlotto.setNumber_4(lottoNumbers[3]);
        mlotto.setNumber_5(lottoNumbers[4]);
        mlotto.setNumber_6(lottoNumbers[5]);

        lottoService.saveLottoNumbers(mlotto);
        
        Map<String, Long[]> response = new HashMap<>();
        Long[] numbersArray = Arrays.stream(lottoNumbers).boxed().toArray(Long[]::new);
        response.put("numbers", numbersArray);
        
        System.out.println(savedLottoList.toArray(new Lotto[0]));
        
        return ResponseEntity.ok(response);
    }

    private long[] generateSortedLottoNumbers() {
        Random random = new Random();
        long[] lottoNumbers = new long[6];


        for (int i = 0; i < 6; i++) {
            lottoNumbers[i] = random.nextInt(45) + 1;
        }
        Arrays.sort(lottoNumbers);

        return lottoNumbers;
    }
    
    

	

}
