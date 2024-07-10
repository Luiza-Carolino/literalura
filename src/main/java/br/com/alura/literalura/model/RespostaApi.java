package br.com.alura.literalura.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Optional;

@JsonSerialize
public record RespostaApi(
        Integer count,
        Optional<String> next,
        Optional<String> previous,
        List<DadosLivro> results
) {
}
