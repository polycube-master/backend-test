package kr.co.polycube.backendtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.dto.UserDTO;
import kr.co.polycube.backendtest.entity.User;
import kr.co.polycube.backendtest.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterUser() throws Exception{
        // 요청받는 UserDTO 객체 임의 설정
        UserDTO userDTO=new UserDTO();
        userDTO.setName("Test User");

        // 생성된 User 객체 임의 생성
        User createUser=new User(1L,"Test User");

        // UserService 의 saveUser 가 호출될 때 Mockito 를 사용하여 createUser 객체가 반환됨
        Mockito.when(userService.saveUser(Mockito.any(UserDTO.class))).thenReturn(createUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/users") // "/users" 로의 POST 요청을 생성하고, 시뮬레이션 돌림
                .contentType(MediaType.APPLICATION_JSON) // 요청 본문의 컨텐츠 타입을 json 으로
                .content(objectMapper.writeValueAsString(userDTO))) // 요청 본문에는 userDTO 객체를 JSON 문자열로 변환하여 포함
                .andExpect(status().isCreated()) // 예상대로 응답 상태 코드가 201(Created)인지 확인
                .andExpect(jsonPath("$.id").value(1)); // 응답 본문에서 id 필드의 값이 1인지 확인
    }

    @Test
    public void testGetUser() throws Exception{
        // 존재하는 사용자 정보 설정
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Test User");

        // UserService 의 getUserById가 호출될 때 userDTO 객체가 반환됨
        Mockito.when(userService.getUserById(1L)).thenReturn(userDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1")) // "/users/1" 로의 GET 요청을 생성하고, 시뮬레이션 돌림
                .andExpect(status().isOk()) // 응답 상태 코드가 200(OK) 인지 확인
                .andExpect(jsonPath("$.id").value(1)) // 응답 본문의 id 필드 값이 1인지 확인
                .andExpect(jsonPath("$.name").value("Test User")); // 이름 필드 값이 "Test User"인지 확인
    }

    @Test
    public void testUpdateUser() throws Exception{
        // 업데이트할 사용자 정보를 설정
        UserDTO updatedUserDTO=new UserDTO();
        updatedUserDTO.setId(1L);
        updatedUserDTO.setName("Updated User");

        // 존재하는 사용자 정보 설정
        UserDTO existingUserDTO=new UserDTO();
        existingUserDTO.setId(1L);
        existingUserDTO.setName("Test User");

        // UserService 의 updateUser 메서드가 호출될 때 Mockito를 사용하여 updatedUserDTO 객체가 반환됨
        Mockito.when(userService.updateUser(Mockito.anyLong(), Mockito.any(UserDTO.class))).thenReturn(updatedUserDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/1") // "/users/1" 로의 PUT 요청을 생성하고, 시뮬레이션 돌림
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUserDTO))) // 요청 본문에는 JSON 형식의 updatedUserDTO 객체가 포함됨
                .andExpect(status().isOk()) // 응답 상태 코드가 200(OK) 인지 확인
                .andExpect(jsonPath("$.id").value(1)) // 응답 본문의 id 필드 값이 1인지 확인
                .andExpect(jsonPath("$.name").value("Updated User")); // 이름 필드 값이 "Updated User"인지 확인

    }

}
