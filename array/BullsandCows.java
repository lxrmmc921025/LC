package array;
/**
 * Created by az on 9/23/2019.
 */
public class BullsandCows {
    public static String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        //use array "+&-" to count change of two string at one time
        //array, suitable for small and fix range
        int[] arr = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) bulls++;
            else {
                if (arr[s] < 0) cows++;
                if (arr[g] > 0) cows++;
                arr[s]++;
                arr[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
