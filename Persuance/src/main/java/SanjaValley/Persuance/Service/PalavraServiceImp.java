package SanjaValley.Persuance.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SanjaValley.Persuance.Entity.Palavra;
import SanjaValley.Persuance.Repository.PalavraRepository;

@Service
public class PalavraServiceImp implements PalavraService{

    @Autowired
    private PalavraRepository palavraRepository;


    @Override
    public Palavra novaPalavra(Palavra palavra) {

        if(palavra.getPalavra().isEmpty() || palavra.getPalavra() == null
        || palavra.getClasseGramatical().isEmpty() || palavra.getClasseGramatical() == null){
            throw new IllegalArgumentException("Palavra ou Classe Gramatical não foi preenchida");
        }
      List<Palavra> palavraList = palavraRepository.findByPalavraAndClasseGramaticalOrderByRevisaoDesc(palavra.getPalavra()
              ,palavra.getClasseGramatical());
      if(!palavraList.isEmpty()){
          palavra.setRevisao(palavraList.get(0).getRevisao() +1);
      }else{
          palavra.setRevisao(1);
        }
       return palavraRepository.save(palavra);
    }

    @Override
    public List<Palavra> buscaPorPalavra(String palavra){
        //todo if usuario admin uma busca

        if(palavra.isEmpty() || palavra == null){
            throw new IllegalArgumentException("Não foi inserida nenhuma palavra");
        }
        List<Palavra> teste = palavraRepository.findUltimaRevisaoPalavra(palavra);
        if(teste.isEmpty()){
            throw new IllegalStateException("Nenhuma Palavra Encontrada");
        }
        return teste;
    }

    @Override
    public List<Palavra> buscaPalavraEClasseGramatical(String palavra, String classeGramatical){

        if(palavra.isEmpty() || palavra == null
                || classeGramatical.isEmpty() || classeGramatical == null){
            throw new IllegalArgumentException("Palavra ou Classe Gramatical não foi preenchida");
        }
        List<Palavra> palavraList = palavraRepository.findByPalavraAndClasseGramaticalOrderByRevisaoDesc(palavra
                ,classeGramatical);
        if(palavraList.isEmpty()){
            throw new IllegalStateException("Nenhuma Palavra Encontrada");
        }
        return palavraList;
    }


/*
    public List<Palavra> buscarPorPalavraNoTexto(String palavra){
        String[] arrOfStr = palavra.split(" ");

        for (String a : arrOfStr)
            palavraRepository.findByPalavra(a);

        return  null;
    }*/


}
