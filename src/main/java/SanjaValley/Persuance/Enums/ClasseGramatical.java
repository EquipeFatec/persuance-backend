package SanjaValley.Persuance.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ClasseGramatical {
    SUBSTANTIVO("Substantivo"),
    ARTIGO("Artigo"),
    ADJETIVO("Adjetivo"),
    NUMERAL("Numeral"),
    PRONOME("Pronome"),
    VERBO("Verbo"),
    ADVERBIO("Advérbio"),
    PREPOSICAO("Preposição"),
    CONJUNCAO("Conjunção"),
    INTERJEICAO("Interjeição");

    private String nome;
}
