package com.sp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sp.app.domain.mylotto;

public interface LottoRepository extends JpaRepository<mylotto, Long> {


}
