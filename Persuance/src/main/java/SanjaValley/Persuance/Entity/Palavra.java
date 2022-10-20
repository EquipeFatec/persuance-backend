package SanjaValley.Persuance.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="pa_palavra")
public class Palavra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pa_id")
    private int id;

    @Column(name = "pa_palavra")
    private String palavra;

    @Column(name = "pa_traducao")
    private String traducao;

    @Column(name = "pa_aprovado")
    private boolean aprovada;

    @Column(name = "pa_significado")
    private String significado;

    @Column(name = "pa_conjugacao")
    private String conjucacao;

    @Column(name = "pa_ex_aprovado", length=500)
    private String exemploAprovado;

    @Column(name = "pa_classe_gramatical")
    private String classeGramatical;

    @Column(name = "pa_categoria")
    private String categoria;

    @Column(name = "pa_revisao")
    private int revisao;

}
