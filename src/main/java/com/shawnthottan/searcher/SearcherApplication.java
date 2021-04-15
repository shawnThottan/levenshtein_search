package com.shawnthottan.searcher;

import com.shawnthottan.searcher.model.Request;
import com.shawnthottan.searcher.model.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class SearcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearcherApplication.class, args);
    }

    @GetMapping("/")
    public ResponseEntity<Object> find(Request request) {
        String text = request.getText();
        // Checks if the text parameter is present
        if (text == null || text.length() == 0) {
            return new ResponseEntity<>("Error: Parameter 'text' is empty", HttpStatus.BAD_REQUEST);
        }

        String wordParam = request.getWord();
        // Checks if the word parameter is present
        if (wordParam == null || wordParam.length() == 0) {
            return new ResponseEntity<>("Error: Parameter 'word' is empty", HttpStatus.BAD_REQUEST);
        }

        // Removes the spaces from the word parameter before searching.
        String word = String.join("", wordParam.split(" "));

        // sets the default value for MaxLevenshteinDistance
        if (request.getMaxLevenshteinDistance() == null) {
            request.setMaxLevenshteinDistance(1);
        }

        // Creates an array of strings from the text
        // removes empty strings
        String[] words = Arrays.stream(text
                .split(" "))
                .filter((str) -> str.length() > 0)
                .toArray(String[]::new);

        // gets the number of repetitions of the word
        Long frequency = Arrays
                .stream(words)
                .filter(str -> str.equals(word))
                .count();

        // gets the words that are similar
        List<String> similarArr = Arrays
                .stream(words)
                .filter(str -> {
                    int levenshteinDistance = Util.getLevenshteinDistance(str, word);
                    return (levenshteinDistance > 0 && levenshteinDistance <= request.getMaxLevenshteinDistance());
                })
                .collect(Collectors.toList());

        // removes duplicates from the array
        String[] similar = new HashSet<>(similarArr).toArray(new String[0]);

        return new ResponseEntity<>(new Response(frequency, similar), HttpStatus.OK);
    }
}
