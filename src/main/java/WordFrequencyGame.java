import java.util.*;
import java.util.stream.Collectors;


public class WordFrequencyGame {
    public String getResult(String inputStr){

        String spaceRegex = "\\s+";

            try {

                //split the input string with 1 to n pieces of spaces
                List<String> words = Arrays.asList(inputStr.split(spaceRegex));
                List<WordInfo> inputList = calWordCount(words);

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo w : inputList) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
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
