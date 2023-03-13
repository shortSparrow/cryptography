import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private List<String> words;

    public FileProcessor() {
        words = new ArrayList<>();
        getData();
    }

    private void getData() {
        FileReader fileReader;
        BufferedReader bufferedReader;

        try {
            fileReader = new FileReader("src/english_words.txt");
            bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                words.add(line.toUpperCase());
            }

            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getWords() {
        return words;
    }

}
