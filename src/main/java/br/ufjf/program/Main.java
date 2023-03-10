package br.ufjf.program;

import br.ufjf.model.Pagina;
import br.ufjf.resources.LRU;
import br.ufjf.utils.FileUtils;

import java.util.List;

import static br.ufjf.utils.IOUtils.read;
import static br.ufjf.utils.IOUtils.write;



public class Main {
    public static void main(String[] args) {
        try {
            write("====================================================");
            write("Por favor, informe o arquivo contendo as páginas que serão inseridas.");
            String nomeArquivo = read();
            List<Pagina> insercoes = FileUtils.lerInsercoesDoArquivo(nomeArquivo);
            write("====================================================");
            write("Por favor, informe o número máximo de páginas no buffer de memória.");
            int maxFrames = Integer.parseInt(read());
            LRU lru = new LRU(maxFrames);
            lru.rodarLRUAging(insercoes);
        } catch (Exception e) {
            write("====================================================");
            write(e.getMessage());
            write("====================================================");
        }
    }
}