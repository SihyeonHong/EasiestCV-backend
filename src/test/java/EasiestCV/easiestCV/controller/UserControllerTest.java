package EasiestCV.easiestCV.controller;

import EasiestCV.easiestCV.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUsername() throws Exception{
        String expectedUsername = "testname";
        Mockito.when(userService.getUsernameById("testid")).thenReturn("testname");

        mockMvc.perform(get("/username")
                        .param("userid", "testid")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedUsername));
    }

    @Test
    public void getUsernameNotFound() throws Exception{
        when(userService.getUsernameById(anyString())).thenThrow(new NoSuchElementException("No user found with the provided ID."));

        mockMvc.perform(get("/username")
                        .param("userid", "unknownid")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
