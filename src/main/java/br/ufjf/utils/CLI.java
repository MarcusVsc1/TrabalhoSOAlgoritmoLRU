package br.ufjf.utils;

import java.io.PrintStream;
import java.util.Scanner;

public class CLI {

    private final static Scanner input = new Scanner(System.in);
    private final static PrintStream output = System.out;

    public static String read() {
        return input.nextLine();
    }

    public static void write(String content) {
        output.println(content);
    }
}
