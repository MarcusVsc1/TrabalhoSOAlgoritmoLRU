package br.ufjf.utils;

import br.ufjf.model.Pagina;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    public static List<Pagina> lerInsercoesDoArquivo(String input) throws Exception {
        try {
            Path path = Paths.get("files/" + input);
            return Arrays.asList(Files.readString(path).split(","))
                    .stream()
                    .map(Pagina::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IOException("Não foi possível a leitura do arquivo. Erro: " + e.getMessage());
        }
    }

}
