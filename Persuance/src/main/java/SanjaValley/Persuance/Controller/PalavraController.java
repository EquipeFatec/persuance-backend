package SanjaValley.Persuance.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import SanjaValley.Persuance.Entity.Palavra;
import SanjaValley.Persuance.Service.PalavraServiceImp;

@RestController
@CrossOrigin
@RequestMapping(value = "/search")
public class PalavraController {

    @Autowired
    private PalavraServiceImp palavraService;

    @DeleteMapping(value = "deleta")
    public ResponseEntity<String> deletaPalavra(@RequestParam(value = "palavra") String palavra){
        var isRemoved = palavraService.deletaPalavra(palavra);
        if (isRemoved > 0){
            return new ResponseEntity<>("Não foi possivel deletar a palavra: ",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Palavra deletada com Sucessso",HttpStatus.OK);
       // return new ResponseEntity<>("Delete called",HttpStatus.OK);
    }

    @GetMapping(value = "/{palavra}")
    public ResponseEntity<List<Palavra>> buscaPorPalavra(@PathVariable String palavra){
            List<Palavra> list = palavraService.buscaPorPalavra(palavra);
            return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/edita/{palavra}")
    public ResponseEntity<List<Palavra>> buscaPorPalavraEClasse(@PathVariable String palavra, String classe){
        List<Palavra> list = palavraService.buscaPalavraEClasseGramatical(palavra, classe);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Palavra> adicionaPalavra(@RequestBody Palavra palavra) {
            Palavra _palavra = palavraService.novaPalavra(palavra);
            return new ResponseEntity<>(_palavra, HttpStatus.CREATED);
    }
}