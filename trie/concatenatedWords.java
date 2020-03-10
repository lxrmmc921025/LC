package trie;

import basicClass.TrNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 3/9/2020.
 */
public class concatenatedWords {
    //LC 472 hard

    //M1 : Trie + DFS
    //https://leetcode.com/problems/concatenated-words/discuss/95644/102ms-java-Trie-%2B-DFS-solution.-With-explanation-easy-to-understand.
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;
        TrieNode root = buildTrie(words);
        for (String word : words) {
            if (isConcatenated(word.toCharArray(), 0, root, 0)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean isConcatenated(char[] ch, int idx, TrieNode root, int count) {
        TrieNode cur = root;
        int n = ch.length;
        for (int i = idx; i < n; i++) {
            if (cur.next[ch[i] - 'a'] == null) {
                return false;
            }
            if (cur.next[ch[i] - 'a'].isWord) {
                if (i == n - 1) {
                    return count >= 1;
                }
                if (isConcatenated(ch, i + 1, root, count + 1)) {
                    return true;
                }
            }
            cur = cur.next[ch[i] - 'a'];
        }
        return false;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (cur.next[idx] == null) {
                    cur.next[idx] = new TrieNode();
                }
                cur = cur.next[idx];
            }
            cur.isWord = true;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isWord;
    }


    //M2 : DP
    //https://leetcode.com/problems/concatenated-words/discuss/95652/Java-DP-Solution
}
