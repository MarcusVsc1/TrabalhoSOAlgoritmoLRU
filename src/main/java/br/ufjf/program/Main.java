package br.ufjf.program;

import br.ufjf.model.Pagina;
import br.ufjf.resources.LRU;
import br.ufjf.utils.CLI;
import br.ufjf.utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            CLI.write("====================================================");
            CLI.write("Por favor, informe o arquivo contendo as páginas que serão inseridas.");
            String nomeArquivo = CLI.read();
            List<Pagina> insercoes = FileUtils.lerInsercoesDoArquivo(nomeArquivo)
                    .stream()
                    .map(Pagina::new)
                    .collect(Collectors.toList());
            CLI.write("====================================================");
            CLI.write("Por favor, informe o número máximo de páginas no buffer de memória.");
            int maxFrames = Integer.parseInt(CLI.read());
            LRU lru = new LRU(maxFrames);
            lru.rodarLRUAging(insercoes);
        } catch (Exception e) {
            CLI.write("====================================================");
            CLI.write(e.getMessage());
            CLI.write("====================================================");
        }
    }
}