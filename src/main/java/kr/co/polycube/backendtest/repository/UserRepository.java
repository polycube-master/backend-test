package kr.co.polycube.backendtest.repository;

import kr.co.polycube.backendtest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long Id);
}
