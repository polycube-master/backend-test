package kr.co.polycube.backendtest.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("해당 id의 유저가 없습니다.");
    }
}
