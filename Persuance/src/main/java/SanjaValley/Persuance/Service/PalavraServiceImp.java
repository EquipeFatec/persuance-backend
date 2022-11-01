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

    String mensagem;

    private boolean checkPreenchimentoPalavra(String palavra){
        if(palavra.isEmpty() || palavra == null){
            mensagem = "Palavra não preenchida";
            return false;
        }
        return true;
    }

    private boolean checkPreenchimentoClasseGramatical(String classe){
        if(classe.isEmpty() || classe == null){
            mensagem = "Classe Gramatical não preenchida";
            return false;
        }
        return true;
    }

    @Override
    public int deletaPalavra(String palavra) {


        return palavraRepository.deleteByPalavra(palavra);
    }

    @Override
    public Palavra novaPalavra(Palavra palavra) {
        if (!checkPreenchimentoPalavra(palavra.getPalavra())
                || !checkPreenchimentoClasseGramatical(palavra.getClasseGramatical())){
            throw new IllegalArgumentException(mensagem);
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

        if(!checkPreenchimentoPalavra(palavra)){
            throw new IllegalArgumentException(mensagem);
        }
        List<Palavra> palavraList = palavraRepository.findUltimaRevisaoPalavra(palavra);
        if(palavraList.isEmpty()){
            throw new IllegalStateException("Nenhuma Palavra Encontrada");
        }
        return palavraList;
    }

    @Override
    public List<Palavra> buscaPalavraEClasseGramatical(String palavra, String classeGramatical){

        if(!checkPreenchimentoPalavra(palavra) || !checkPreenchimentoClasseGramatical(classeGramatical)){
            throw new IllegalArgumentException(mensagem);
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
