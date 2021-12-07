import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {
    public String getWordFrequency(String inputStr){

        String spaceRegex = "\\s+";

            try {
                List<String> words = Arrays.asList(inputStr.split(spaceRegex));
                List<WordInfo> wordCount = calWordCount(words);

                wordCount.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                wordCount.stream()
                        .forEach(word -> joiner.add(String.format("%s %d" , word.getValue() , word.getWordCount())));

                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
    }


    private List<WordInfo> calWordCount(List<String> sentence)
    {
        List<String> distinctWord = sentence.stream()
                .distinct()
                .collect(Collectors.toList());

        List<WordInfo> wordCount = new ArrayList<>();
        
        distinctWord.stream()
                .forEach( targetWord ->
                {
                    int count = (int) sentence.stream().filter(word -> word.equals(targetWord)).count();
                    wordCount.add(new WordInfo(targetWord , count));
                });

        return wordCount;
    }

}
