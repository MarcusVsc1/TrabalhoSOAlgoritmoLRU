package br.ufjf.program;

import br.ufjf.model.LRU;
import br.ufjf.model.Pagina;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Pagina> insercoes = Stream.of(0,1,2,3,4,3,2,5,4,8)
                .map(conteudo -> {
                    String valor = String.valueOf(conteudo);
                    return new Pagina(valor);
                })
                .collect(Collectors.toList());
        LRU lru = new LRU(5);
        lru.rodarLRUAging(insercoes);
    }
}