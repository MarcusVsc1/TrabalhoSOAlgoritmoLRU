package br.ufjf.resources;

import br.ufjf.model.Pagina;
import br.ufjf.utils.CLI;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LRU {

    private List<Pagina> frames = new LinkedList<>();
    private int faltas;
    private int maxFrames;

    public LRU(int maxFrames) {
        this.maxFrames = maxFrames;
    }

    public void rodarLRUAging(List<Pagina> insercoes){
        CLI.write("====================================================");
        CLI.write("Iniciando algoritmo LRU com Aging (envelhecimento)");
        CLI.write("Lista de páginas a inserir: " + insercoes);
        insercoes.forEach(this::inserirPagina);
        CLI.write("====================================================");
        CLI.write("Total de faltas: "+ faltas);
        CLI.write("Buffer final: "+ this.frames);
        CLI.write("====================================================");
        this.faltas = 0;
        this.frames = new LinkedList<>();
    }

    private void inserirPagina(Pagina pagina) {
        CLI.write("====================================================");
        if(this.frames.contains(pagina)){
            CLI.write("Pagina presente: ["+pagina.getConteudo()+"], atualizando a referência. ");
            zerarContadores();
        }
        else {
            CLI.write("Página não presente: ["+pagina.getConteudo()+"]");
            if(this.frames.size() == maxFrames) {
                substituirPagina(pagina);
                zerarContadores();
            } else {
                zerarContadores();
                this.frames.add(pagina);
            }
            faltas++;
            CLI.write("Página ["+pagina.getConteudo()+"] foi adicionada ao buffer");
        }
        atualizarReferencia(pagina);
        CLI.write("Buffer atual: "+this.frames.toString());
        CLI.write("Realizando interrupção de relógio...");

    }

    private void substituirPagina(Pagina pagina) {
        Pagina candidata = frames.stream().min(Comparator.comparing(Pagina::converterContadorEmDecimal)).orElse(null);
        CLI.write("Página substituída: ["+candidata.getConteudo()+"]");
        int idx = this.frames.indexOf(candidata);
        zerarContadores();
        this.frames.set(idx, pagina);
    }

    private void atualizarReferencia(Pagina pagina) {
        int idx = this.frames.indexOf(pagina);
        this.frames.get(idx).referenciar();
    }

    private void zerarContadores(){
        this.frames.forEach(Pagina::atualizarContador);
    }

}
