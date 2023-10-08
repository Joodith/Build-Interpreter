package com.craftinginterpreter.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
//import java.util.Scanner;
import com.craftinginterpreter.lox.Scanner;


public class Lox {
    static boolean hadError=false;
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Command is : jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runScript(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runScript(String path) throws IOException {
        byte[] scriptBytes = Files.readAllBytes(Paths.get(path));
        run(new String(scriptBytes, Charset.defaultCharset()));
        if(hadError){
            System.exit(65);
        }
    }

    private static void runPrompt() throws IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(in);
        for (; ; ) {
            System.out.println(">");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError=false;
        }

    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.out.println("[ line " + line + " ] " + where + " : " + message);
        hadError = true;
    }
}
