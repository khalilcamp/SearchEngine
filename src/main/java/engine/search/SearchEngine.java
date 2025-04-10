package main.java.engine.search;

import main.java.engine.index.InvertedIndex;

import java.util.*;

public class SearchEngine {
    private final InvertedIndex index;
    public SearchEngine(InvertedIndex index) {
        this.index = index;
    }

    public Set<String> search(String query) {
        String[] words = preprocess(query);
        Set<String> result = new HashSet<>();
        boolean first = true;
        for (String word : words) {
            Set<String> docs = index.search(word);
            if (first) {
                result.addAll(docs);
                first = false;
            } else {
                result.retainAll(docs); // AND
            }
        }

        return result;
    }

    private String[] preprocess(String query) {
        return query.toLowerCase()
                .replaceAll("[^a-z0-9 ]", "")
                .split("\\s+");
    }
}