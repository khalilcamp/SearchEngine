package main.java.engine.index;

import java.util.*;

public class InvertedIndex {
    private final Map<String, Set<String>> index = new HashMap<>();

    public void build(Map<String, String> documents) {
        for (Map.Entry<String, String> entry : documents.entrySet()) {
            String docName = entry.getKey();
            String content = entry.getValue();

            String[] tokens = preprocess(content);

            for (String token : tokens) {
                if (!token.isEmpty()) {
                    index.computeIfAbsent(token, k -> new HashSet<>()).add(docName);
                }
            }
        }
    }

    public Set<String> search(String word) {
        return index.getOrDefault(word.toLowerCase(), Collections.emptySet());
    }

//    public Map<String, Set<String>> getIndex() {
//        return index;
//    }

    private String[] preprocess(String text) {
        return text.toLowerCase()
                .replaceAll("[^a-z0-9 ]", "")
                .split("\\s+");
    }
}
