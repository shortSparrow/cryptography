import java.util.List;

public class LanguageDetector {
    private FileProcessor fileProcessor;
    private List<String> englishWords;

    public LanguageDetector() {
        fileProcessor = new FileProcessor();
        englishWords = fileProcessor.getWords();
    }

    private int countEnglishWordsInText(String text) {
        text = text.toUpperCase();
        String[] words = text.split(" ");
        int matches = 0;

        for (String str : words) {
            if (englishWords.contains(str)) {
                matches++;
            }
        }

        return matches;
    }

    public boolean isEnglish(String text) {
        int matches = countEnglishWordsInText(text);
        /**
         * You can define your own classification algorithm.
         * in this case the assumption: if 60 percent of the words in the text are English word
         * that it is an English text
         */
        return ((float) matches / text.split(" ").length) * 100 >= 60;
    }
}
