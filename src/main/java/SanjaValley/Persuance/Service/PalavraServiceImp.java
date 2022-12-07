package SanjaValley.Persuance.Service;

import java.util.ArrayList;
import java.util.List;

import SanjaValley.Persuance.Entity.TextoResultado;
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

    public int deletaPalavra(Long palavraId) {
        return palavraRepository.deleteByPalavra(palavraId);
    }

    @Override
    public Palavra novaPalavra(Palavra palavra) {

        if (!checkPreenchimentoPalavra(palavra.getPalavra())
                    || !checkPreenchimentoClasseGramatical(palavra.getClasseGramatical())) {
                throw new IllegalArgumentException(mensagem);
            }
            List<Palavra> palavraList = palavraRepository.findByPalavraAndClasseGramaticalOrderByRevisaoDesc(palavra.getPalavra()
                    , palavra.getClasseGramatical());
            if (!palavraList.isEmpty()) {
                Palavra palavraEncontrada = palavraList.get(0);
                palavra.setId(palavraEncontrada.getId());
                palavra.setRevisao(palavraList.get(0).getRevisao() + 1);
            } else {
                palavra.setRevisao(1);
                palavra.setId(palavraRepository.findLastPalavraId() + 1);
            }
            return palavraRepository.save(palavra);
        }


    @Override
    public List<Palavra> buscaPorPalavra(String palavra){
        //todo if usuario admin uma busca

        if(!checkPreenchimentoPalavra(palavra)){
            throw new IllegalArgumentException(mensagem);
        }
        List<Palavra> palavraList = palavraRepository.findByPalavra(palavra);
        if(palavraList.isEmpty()){
            throw new IllegalStateException("Nenhuma Palavra Encontrada");
        }
        return palavraList;
    }

    @Override
    public List<Palavra> todasAsPalavras(){
       List<Palavra> palavraList = palavraRepository.findAll();
       return palavraList;
    }





    @Override
    public List<Palavra> buscaPalavraEClasseGramatical(String palavra, String classeGramatical){

        if(!checkPreenchimentoPalavra(palavra) || !checkPreenchimentoClasseGramatical(classeGramatical)){
            throw new IllegalArgumentException(mensagem);
        }
        List<Palavra> palavraList = palavraRepository.findByPalavraAndClasseGramaticalOrderByRevisaoDesc(palavra
                ,null);
        if(palavraList.isEmpty()){
            throw new IllegalStateException("Nenhuma Palavra Encontrada");
        }
        return palavraList;
    }

    public List<TextoResultado> buscarPorPalavraNoTexto(String palavra){
        String[] arrOfStr = palavra.split(" ");
        List<TextoResultado> list = new ArrayList<TextoResultado>();

        for (String a : arrOfStr){
            TextoResultado textoResultado = new TextoResultado(a);
            if(list.contains(textoResultado)){
                continue;
            }
            List<Palavra> listaPalavra = palavraRepository.findByPalavra(a);
            if (listaPalavra.isEmpty()){
                textoResultado.setAprovada(false);
            }else{
                for (Palavra p : listaPalavra){
                   textoResultado.setAprovada(p.isAprovada());
                }
            }
            list.add(textoResultado);
        }
        return list;
    }


}
