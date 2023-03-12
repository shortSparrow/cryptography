import java.util.Map;

interface Crack {
    public void crack(String cipherText);
}

public class CrackCaesar implements Crack {
    FrequencyAnalysis frequencyAnalysis;

    CrackCaesar() {
        frequencyAnalysis = new FrequencyAnalysis();
    }

    @Override
    public void crack(String cipherText) {
        Map<Character, Integer> letterFrequencies = frequencyAnalysis.run(cipherText);
        // Simple O(n) linear search to find the character with the maximum frequency
        Map.Entry<Character, Integer> maxEntry = null;

        for (Map.Entry<Character, Integer> entry : letterFrequencies.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        char maxFrequentChar = maxEntry.getKey();

        // key (most frequent letter in the cipher tax, - the most frequent letter in the English alphabet)
        // the most common letter is a white space: that's why we do not care about ' ' in this case
        int approximatedKey = Constants.ALPHABET.indexOf(maxFrequentChar) - Constants.ALPHABET.indexOf(" "); // Empty space is the most character in English. If we talk about human characters is E

        System.out.println("approximatedKey is " + approximatedKey);
    }
}
