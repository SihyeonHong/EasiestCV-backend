package EasiestCV.easiestCV.controller;

import EasiestCV.easiestCV.model.User;
import EasiestCV.easiestCV.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


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

    // 프론트엔드에서도 잘 되는데 왜 얘만 403
    @Test
    @WithMockUser
    public void testSignup() throws Exception {
        User user = new User();
        user.setUserid("testcode");
        user.setUsername("testcode");
        user.setEmail("testcode");
        user.setPassword("testcode");
        user.setImg(null);
        user.setPdf(null);

        when(userService.signup(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userid", is(user.getUserid())))
                .andExpect(jsonPath("$.username", is(user.getUsername())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.img", is(user.getImg())))
                .andExpect(jsonPath("$.pdf", is(user.getPdf())));
    }
}
