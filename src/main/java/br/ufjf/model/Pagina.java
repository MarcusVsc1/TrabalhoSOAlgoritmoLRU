package br.ufjf.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Pagina {

    private String conteudo;
    private List<Integer> contador;

    public Pagina(String conteudo) {
        this.conteudo = conteudo;
        this.contador = Arrays.asList(0,0,0,0,0,0);
    }

    public void atualizarContador(){
        for(int i = contador.size() - 1; i > 0; i--) {
            contador.set(i, contador.get(i-1));
        }
        contador.set(0,0);
    }

    public void referenciar(){
        this.getContador().set(0,1);
    }

    public Integer converterContadorEmDecimal(){
        int decimal = 0;
        for(int i = this.contador.size() - 1; i >= 0 ; i--){
            decimal += this.contador.get(i) == 0 ? 0 : (int) Math.pow(2, contador.size() -1 - i);
        }

        return decimal;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pagina){
            return ((Pagina) obj).getConteudo().equals(this.conteudo);
        }
        return  false;
    }

    @Override
    public int hashCode() {
        return this.conteudo.hashCode();
    }

    @Override
    public String toString(){
        return this.conteudo;
    }

}
