import java.util.HashMap;
import java.util.Map;

public class FrequencyAnalysis {

    //    We just count occurrences of the given characters one by one.
    public Map<Character, Integer> run(String text) {
        text = text.toUpperCase();
        Map<Character, Integer> frequencies = new HashMap<>();

        // initialize frequencies HashMap
        for (int i = 0; i < Constants.ALPHABET.length(); i++) {
            frequencies.put(Constants.ALPHABET.charAt(i), 0);
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if(Constants.ALPHABET.indexOf(c) != -1) {
                frequencies.put(c, frequencies.get(c) + 1);
            }
        }

        return  frequencies;
    }


}
