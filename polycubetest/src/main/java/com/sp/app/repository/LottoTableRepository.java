package com.sp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.app.domain.Lotto;

public interface LottoTableRepository extends JpaRepository<Lotto, Long> {
	
}
