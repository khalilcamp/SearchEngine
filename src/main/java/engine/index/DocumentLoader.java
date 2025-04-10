package main.java.engine.index;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DocumentLoader {
    private final Path docsDirectory;

    public DocumentLoader(String folderpath) {
        this.docsDirectory = Paths.get(folderpath);
    }

    public Map<String, String> loadDocuments(){
        Map<String, String> documents = new HashMap<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(docsDirectory, "*.txt")){
            for (Path entry: stream){
                String content = Files.readString(entry);
                documents.put(entry.getFileName().toString(),content);
            }
        } catch (IOException e){
            System.err.println("Erro ao carregar: " + e.getMessage());
        }

        return documents;
    }
}
