import java.io.*;
import java.util.*;

public class BookIndexer {
    private Map<String, Set<Integer>> wordIndex;
    private Set<String> excludeWords;

    public BookIndexer() {
        wordIndex = new TreeMap<>();
        excludeWords = new HashSet<>();
    }

    public void loadExcludeWords(String excludeWordsFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(excludeWordsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                excludeWords.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void indexBookPages(String... pageFiles) {
        for (int i = 0; i < pageFiles.length; i++) {
            String pageFile = pageFiles[i];
            try (BufferedReader reader = new BufferedReader(new FileReader(pageFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        word = word.toLowerCase();
                        word = word.replaceAll("[^a-zA-Z-``]", "");
                        if (!excludeWords.contains(word)) {
                            Set<Integer> pages = wordIndex.getOrDefault(word, new HashSet<>());
                            pages.add(i + 1); // Page numbers start from 1
                            wordIndex.put(word, pages);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveIndex(String indexFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(indexFile))) {
            for (Map.Entry<String, Set<Integer>> entry : wordIndex.entrySet()) {
                String word = entry.getKey();
                Set<Integer> pages = entry.getValue();
                writer.write(word + " : ");
                boolean isFirstPage = true;
                for (int page : pages) {
                    if (!isFirstPage) {
                        writer.write(", ");
                    }
                    writer.write(Integer.toString(page));
                    isFirstPage = false;
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BookIndexer indexer = new BookIndexer();
        indexer.loadExcludeWords("exclude-words.txt");
        indexer.indexBookPages("Page1.txt", "Page2.txt", "Page3.txt");
        indexer.saveIndex("index.txt");
        System.out.println("Index created successfully.");
    }
}
