package main.java.engine;

import main.java.engine.index.DocumentLoader;
import main.java.engine.index.InvertedIndex;
import main.java.engine.search.SearchEngine;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        String docsPath = "src/main/resources/docs";

        DocumentLoader loader = new DocumentLoader(docsPath);
        Map<String, String> documents = loader.loadDocuments();

        InvertedIndex index = new InvertedIndex();
        index.build(documents);

        SearchEngine engine = new SearchEngine(index);

        Scanner scanner = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println("Search Engine Iniciada(Digita end se quiser sair)");
        System.out.println("======================================");

        while (true) {
            System.out.print("Procure por: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) break;

            Set<String> result = engine.search(input);

            if (result.isEmpty()) {
                System.out.println("===================");
                System.out.println("Nenhum documento encontrado :[");
                System.out.println("===================");
            } else {
                System.out.println("Òtima escolha!");
                System.out.println("===================");
                System.out.println("Encontrado em: " + result);
                System.out.println("===================");
            }
        }

        scanner.close();
    }
}