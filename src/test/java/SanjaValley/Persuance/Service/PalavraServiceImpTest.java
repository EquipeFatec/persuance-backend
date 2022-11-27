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
        palavra.setId(1);
        palavra.setPalavra("FREE");
        palavra.setTraducao("livre");
        palavra.setAprovada(true);
        palavra.setSignificado("That can move easily [Do not use compund adjectives with free, for example, lintfree, dustfree, unless they are technical names]");
        palavra.setConjucacao("");
        palavra.setExemploAprovado("MAKE SURE THAT THE NUTS ARE FREE TO TURNN ON THE SLEEVES or MAKE SURE THAT THERE IS NO DUST ON THE SURFACE");
        palavra.setClasseGramatical("adjetivo");
        palavra.setCategoria("");
        palavra.setRevisao(0);
        palavras.add(palavra);

        Mockito.when(repo.findByPalavra(any())).thenReturn(palavras);

        List<Palavra> palavrasBuscadas = service.buscaPorPalavra(palavra.getPalavra());
        Palavra primeiraPalavraDaBusca = palavrasBuscadas.get(0);
        assertNotNull(palavrasBuscadas);
        assert(palavra.equals(primeiraPalavraDaBusca));
        
        assertEquals(palavra.getId(), 1);
        assertEquals(palavra.getPalavra(), "ALEXIA");
        assertEquals(palavra.getTraducao(), "livre");
        assertEquals(palavra.isAprovada(), true);
        assertEquals(palavra.getSignificado(), "That can move easily [Do not use compund adjectives with free, for example, lintfree, dustfree, unless they are technical names]");
        assertEquals(palavra.getConjucacao(), "");
        assertEquals(palavra.getExemploAprovado(), "MAKE SURE THAT THE NUTS ARE FREE TO TURNN ON THE SLEEVES or MAKE SURE THAT THERE IS NO DUST ON THE SURFACE");
        assertEquals(palavra.getClasseGramatical(), "adjetivo");
        assertEquals(palavra.getCategoria(), "");
        assertEquals(palavra.getRevisao(), 0);
    }

    @Test
    public void verificandoBuscarPalavraInvalida(){
        List<Palavra> palavras = new ArrayList<Palavra>();
        Palavra palavra = new Palavra(
            1, 
            "FREE", 
            "livre", 
            true, 
            "That can move easily [Do not use compund adjectives with free, for example, lintfree, dustfree, unless they are technical names]", 
            "", 
            "MAKE SURE THAT THE NUTS ARE FREE TO TURNN ON THE SLEEVES or MAKE SURE THAT THERE IS NO DUST ON THE SURFACE", 
            "adjetivo", 
            "", 
            0
        );
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
