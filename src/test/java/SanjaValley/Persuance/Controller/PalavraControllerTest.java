package SanjaValley.Persuance.Controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import SanjaValley.Persuance.Entity.Palavra;
import SanjaValley.Persuance.Repository.PalavraRepository;
import SanjaValley.Persuance.Repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class PalavraControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PalavraRepository repo;

    @BeforeEach
    public void setup() {
        List<Palavra> palavras = new ArrayList<Palavra>();
        Palavra palavra = new Palavra();
        palavra.setId(1);
        palavra.setPalavra("FREE");
        palavras.add(palavra);

        Palavra palavra2 = new Palavra();
        palavra2.setId(2);
        palavra2.setPalavra("FRAGILE");
        palavras.add(palavra2);

        Mockito.when(repo.deleteByPalavra(any())).thenReturn(1);
        Mockito.when(repo.findAll()).thenReturn(palavras);
        Mockito.when(repo.findByPalavra("FRAGILE")).thenReturn(Arrays.asList(palavra2));
    }

    @Test
    public void deletaPalavraTest() throws Exception {
        String palavraADeletar = "FREE";

        mvc.perform(delete("/search/deleta/" + palavraADeletar))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Palavra deletada com Sucessso"));
    }

    @Test
    public void todasAsPalavrasTest() throws Exception {
        mvc.perform(get("/search/palavras"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void buscaPorPalavraTest() throws Exception {
        String palavraABuscar = "FRAGILE";

        mvc.perform(get("/search/" + palavraABuscar))
            .andDo(print())
            .andExpect(status().isOk());
    }

}
