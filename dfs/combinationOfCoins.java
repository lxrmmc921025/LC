package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 10/22/2019.
 */
public class combinationOfCoins {
    public static List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(target, coins,0, new ArrayList<>(), res);
        return res;
    }

    public static void dfs(int target, int[] coins, int index, List<Integer> tmp, List<List<Integer>> res) {
        /*
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        //[!!] index represent the layer in coins, cannot go out of bound
        if (target < 0 || index >= coins.length) {
            return;
        }*/
        //[!!]improvement : last layer, don't need to go over
        if (index == coins.length - 1) {
            if (target % coins[coins.length - 1] == 0) {
                tmp.add(target / coins[coins.length - 1]);
                res.add(new ArrayList<>(tmp));
                tmp.remove(tmp.size() - 1);
            }
            return;
        }
        for (int i = 0; i <= target / coins[index]; i++) {
            tmp.add(i);
            dfs(target - i * coins[index], coins, index + 1, tmp, res);
            tmp.remove(tmp.size() - 1);
        }
    }
}
