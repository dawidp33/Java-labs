package t9;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

class WordTrie {

    public Node root = new Node(false);

    public static class Node extends HashMap<Character, Node> {
        //czy jest wpisane słowo
        public boolean isWord;
        
        public Node(boolean isWord) {
            this.isWord = isWord;
        }
    }

    public WordTrie(Collection<String> words) {
        for (String word : words) {
            add(word);
        }
    }

    public void add(String word) {
        buildTree(root, word.toLowerCase());
    }

    private void buildTree(Node node, String word) {
        if (word.length() == 0) {
            //jezeli przeszlismy cale slowo
            node.isWord = true;
            return;
        } else {
            //dodawanie kolejnego węzła
            //pierwsza litera
            char first = word.charAt(0);
            //reszta słowa
            String rest = word.substring(1);
            if (!node.containsKey(first)) {
                //dodanie wezla
                node.put(first, new Node(false));
            }

            buildTree(node.get(first), rest);
        }
    }

    public static WordTrie fromSystemWords() {
        List<String> words = FileUtils.readFileInList("common_words.txt");
        return new WordTrie(words);
    }
}
