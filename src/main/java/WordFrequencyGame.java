import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {
    final String SPACE_REGEX = "\\s+";
    final String ERROR_MESSAGE = "Calculate Error";

    public String getWordFrequency(String inputStr) {
        try {
            List<WordInfo> wordCount = calWordCount(inputStr);
            return formatOutputString(wordCount);
        } catch (Exception e) {
            return ERROR_MESSAGE;
        }
    }

    private String formatOutputString(List<WordInfo> wordCount) {
        wordCount.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

        StringJoiner joiner = new StringJoiner("\n");
        wordCount.stream()
                .forEach(word -> joiner.add(String.format("%s %d", word.getValue(), word.getWordCount())));

        return joiner.toString();
    }

    private List<WordInfo> calWordCount(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_REGEX));
        List<String> distinctWord = words.stream()
                .distinct()
                .collect(Collectors.toList());

        List<WordInfo> wordCount = new ArrayList<>();

        distinctWord.stream()
                .forEach(targetWord ->
                {
                    int count = (int) words.stream().filter(word -> word.equals(targetWord)).count();
                    wordCount.add(new WordInfo(targetWord, count));
                });

        return wordCount;
    }

}
