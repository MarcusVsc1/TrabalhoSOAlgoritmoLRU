package br.ufjf.resources;

import br.ufjf.model.Pagina;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static br.ufjf.utils.IOUtils.write;

public class LRU {

    private List<Pagina> frames = new LinkedList<>();
    private int faltas;
    private int maxFrames;

    public LRU(int maxFrames) {
        this.maxFrames = maxFrames;
    }

    public void rodarLRUAging(List<Pagina> insercoes){
        write("====================================================");
        write("Iniciando algoritmo LRU com Aging (envelhecimento)");
        write("Lista de páginas a inserir: " + insercoes);
        insercoes.forEach(this::inserirPagina);
        write("====================================================");
        write("Total de faltas: "+ faltas);
        write("Buffer final: "+ this.frames);
        write("====================================================");
        this.faltas = 0;
        this.frames = new LinkedList<>();
    }

    private void inserirPagina(Pagina pagina) {
        write("====================================================");
        if(this.frames.contains(pagina)){
            write("Pagina presente: ["+pagina.getConteudo()+"], atualizando a referência. ");
            zerarContadores();
        }
        else {
            write("Página não presente: ["+pagina.getConteudo()+"]");
            if(this.frames.size() >= maxFrames) {
                substituirPagina(pagina);
                zerarContadores();
            } else {
                zerarContadores();
                this.frames.add(pagina);
            }
            faltas++;
            write("Página ["+pagina.getConteudo()+"] foi adicionada ao buffer");
        }
        atualizarReferencia(pagina);
        write("Buffer atual: "+this.frames.toString());
        write("Contador de cada página:");
        frames.forEach(page-> write(page.toString()+": "+page.getContador()));
        write("Realizando interrupção de relógio...");

    }

    private void substituirPagina(Pagina pagina) {
        Pagina candidata = frames.stream().min(Comparator.comparing(Pagina::converterContadorEmDecimal)).orElse(null);
        write("Página substituída: ["+candidata.getConteudo()+"]");
        int idx = this.frames.indexOf(candidata);
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
