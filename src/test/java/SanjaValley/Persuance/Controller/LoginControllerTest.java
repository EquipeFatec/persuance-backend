package SanjaValley.Persuance.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import SanjaValley.Persuance.Entity.UserModel;
import SanjaValley.Persuance.Repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class LoginControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserRepository userRepo;

    @BeforeEach
    public void setup() {
        UserModel user = new UserModel();
        user.setUsername("userTeste");
        
        String password = "teste123";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String encodedPassword = encoder.encode(password).toString();
        user.setPassword(encodedPassword);

        user.setId(1L);

        Optional<UserModel> userOp = Optional.of(user);
        Mockito.when(userRepo.findByUsername(any())).thenReturn(userOp);        
    }

    @Test
    public void loginTest() throws Exception {
        mvc.perform(post("/login")
            .content("{\"username\":\"userTeste\", \"password\":\"teste123\"}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").exists());
    }
}
