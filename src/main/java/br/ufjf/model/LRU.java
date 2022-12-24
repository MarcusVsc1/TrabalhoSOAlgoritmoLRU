package br.ufjf.model;

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
        System.out.println("====================================================");
        System.out.println("Iniciando algoritmo LRU com Aging (envelhecimento)");
        insercoes.forEach(this::inserirPagina);
        System.out.println("====================================================");
        System.out.println("Total de faltas: "+ faltas);
        System.out.println("Buffer final: "+ this.frames);
        System.out.println("====================================================");
        this.faltas = 0;
        this.frames = new LinkedList<>();
    }

    private void inserirPagina(Pagina pagina) {
        System.out.println("====================================================");
        if(this.frames.contains(pagina)){
            System.out.println("Pagina presente: ["+pagina.getConteudo()+"], atualizando a referência. ");
            zerarContadores();
        }
        else {
            System.out.println("Página não presente: ["+pagina.getConteudo()+"]");
            if(this.frames.size() == maxFrames) {
                substituirPagina(pagina);
                zerarContadores();
            } else {
                zerarContadores();
                this.frames.add(pagina);
            }
            faltas++;
            System.out.println("Página ["+pagina.getConteudo()+"] foi adicionada ao buffer");
        }
        atualizarReferencia(pagina);
        System.out.println("Buffer atual: "+this.frames.toString());
        System.out.println("Realizando interrupção de relógio...");

    }

    private void substituirPagina(Pagina pagina) {
        Pagina candidata = frames.stream().min(Comparator.comparing(Pagina::converterContadorEmDecimal)).orElse(null);
        System.out.println("Página substituída: ["+candidata.getConteudo()+"]");
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
