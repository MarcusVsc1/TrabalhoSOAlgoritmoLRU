package br.ufjf.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static List<String> lerInsercoesDoArquivo(String input) throws Exception {
        try {
            Path path = Paths.get("files/" + input);
            return Arrays.asList(Files.readString(path).split(","));
        } catch (Exception e) {
            throw new IOException("Não foi possível a leitura do arquivo. Erro: " + e.getMessage());
        }
    }

}
