package com.shawnthottan.searcher.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Response {
    @NonNull Long frequency;
    String[] similarWords;
}
