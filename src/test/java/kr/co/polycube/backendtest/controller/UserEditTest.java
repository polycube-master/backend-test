package kr.co.polycube.backendtest.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserEditTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEditUser() throws Exception {
        MvcResult registerResult = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"tachung\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = registerResult.getResponse().getContentAsString();
        JSONObject jsonResponse = new JSONObject(response);
        long userId = jsonResponse.getLong("id");

        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Wick\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(userId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Wick"));
    }
}
