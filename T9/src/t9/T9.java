package t9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class T9 {

    private static Map<Integer, ArrayList<Character>> getKeyMappings() {
        //kolekcja par klucz, wartosc
        Map<Integer, ArrayList<Character>> keyMappings = new HashMap<Integer, ArrayList<Character>>();
        keyMappings.put(2, new ArrayList<Character>(Arrays.asList('a', 'b', 'c')));
        keyMappings.put(3, new ArrayList<Character>(Arrays.asList('d', 'e', 'f')));
        keyMappings.put(4, new ArrayList<Character>(Arrays.asList('g', 'h', 'i')));
        keyMappings.put(5, new ArrayList<Character>(Arrays.asList('j', 'k', 'l')));
        keyMappings.put(6, new ArrayList<Character>(Arrays.asList('m', 'n', 'o')));
        keyMappings.put(7, new ArrayList<Character>(Arrays.asList('p', 'q', 'r', 's')));
        keyMappings.put(8, new ArrayList<Character>(Arrays.asList('t', 'u', 'v')));
        keyMappings.put(9, new ArrayList<Character>(Arrays.asList('w', 'x', 'y', 'z')));
        return keyMappings;
    }
    public static Map<Integer, ArrayList<Character>> keyMappings = getKeyMappings();

    //dopasowania słów
    public static Set<String> getMatches(WordTrie trie, List<Integer> keypresses) {
        return getMatches(trie.root, keypresses, 0);
    }

    private static Set<String> getMatches(WordTrie.Node node, List<Integer> keypresses, int fromIndex) {
        //kolekcja unikalnych elementów
        Set<String> matches = new HashSet<String>();
        if (fromIndex >= keypresses.size()) {
            if (node.isWord) {
                matches.add("");
            }
            return matches;
        }

        Integer keypress = keypresses.get(fromIndex);
        //wyszukanie w drzewie pasujacych znakow
        //dla wszystkich znakow ktore kliknelismy

        for (char firstChar : keyMappings.get(keypress)) {
            WordTrie.Node subnode = node.get(firstChar);
            if (subnode != null) {
                //wywolanie rekruancji, z pominieciem 1 znaku
                Set<String> suffixes = getMatches(subnode, keypresses, fromIndex + 1);
                for (String suffix : suffixes) {

                    matches.add( firstChar + suffix);
                }
            }
        }
        return matches;
    }
}
