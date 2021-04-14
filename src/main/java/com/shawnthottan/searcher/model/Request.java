package com.shawnthottan.searcher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Request {
    String text;
    String word;
    Integer maxLevenshteinDistance;
}
