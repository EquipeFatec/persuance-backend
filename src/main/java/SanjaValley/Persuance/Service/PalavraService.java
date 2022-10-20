package SanjaValley.Persuance.Service;

import SanjaValley.Persuance.Entity.Palavra;

import java.util.List;

public interface PalavraService {

    public Palavra novaPalavra(Palavra palavra);

    public List<Palavra> buscaPorPalavra(String palavra);

    public List<Palavra> buscaPalavraEClasseGramatical(String palavra, String classeGramatical);
}
