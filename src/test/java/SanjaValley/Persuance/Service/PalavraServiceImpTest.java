package SanjaValley.Persuance.Service;

import SanjaValley.Persuance.Entity.Palavra;
import SanjaValley.Persuance.Repository.PalavraRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Transactional
@Rollback
public class PalavraServiceImpTest {

    @Autowired
    private PalavraService service;
    
    @MockBean
    private PalavraRepository repo;



    @Test
    public void verificandoBuscaValida(){
        List<Palavra> palavras = new ArrayList<Palavra>();
        Palavra palavra = new Palavra();
        palavra.setPalavra("ALEXIA");
        palavras.add(palavra);

        Mockito.when(repo.findByPalavra(any())).thenReturn(palavras);


        assertNotNull(service.buscaPorPalavra(palavra.getPalavra()));
    }

    @Test
    public void verificandoBuscarPalavraInvalida(){
        List<Palavra> palavras = new ArrayList<Palavra>();
        Palavra palavra = new Palavra();
        palavra.setPalavra("ALEXIA");
        palavras.add(palavra);

        Mockito.when(repo.findByPalavra(any())).thenReturn(palavras);


        assertThrows(IllegalArgumentException.class, () -> service.buscaPorPalavra(""));
    }

    @Test
    public void verificandoSeBuscaRetornaVazio(){
        Mockito.when(repo.findByPalavra(any())).thenReturn(new ArrayList<Palavra>());

        assertThrows(IllegalStateException.class, () -> service.buscaPorPalavra("helen"));
    }

}
