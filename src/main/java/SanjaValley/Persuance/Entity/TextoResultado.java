package SanjaValley.Persuance.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextoResultado {



    private String palavra;
    private int aprovada;

    public void setAprovada(boolean aprovada) {
        if(this.aprovada == -1){
            if(aprovada){
                this.aprovada=1;
            }else {
                this.aprovada =0;
            }
        }else {
            if(this.aprovada == 1 && !aprovada || this.aprovada == 0 && aprovada){
                this.aprovada = 2;
            }
        }
    }

    @Override
    public boolean equals(Object teste){
        if(teste == this){
            return true;
        }
        if(teste instanceof TextoResultado && this.palavra.equals(((TextoResultado) teste).palavra)){
            return true;
        }
        return false;
    }

    public TextoResultado(String palavra){
        this.palavra = palavra;
        this.aprovada = -1;
    }


}
