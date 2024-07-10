package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;
    @Id
    private String name;
    private Integer birth_year;
    private Integer death_year;

    public Autor() {}

    public Autor(DadosAutor dadosAutor) {
        // this.id = dadosAutor.id();
        this.name = dadosAutor.name();
        this.birth_year = dadosAutor.birth_year();
        this.death_year = dadosAutor.death_year();
    }

    // public Long getId() {
    //     return id;
    // }
    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }
    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }
    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    @Override
    public String toString() {
        String anoNascimento;
        String anoFalecimento;
        if(birth_year == null) { anoNascimento = "(Sem informação)"; }
        else { anoNascimento = birth_year.toString(); }

        if(death_year == null) { anoFalecimento = "(Sem informação)"; }
        else { anoFalecimento = death_year.toString(); }

        return String.format("""
                --> Autor: "%s"
                - Ano de nascimento: "%s"
                - Ano de falecimento: "%s"
                """
                ,name
                ,anoNascimento
                ,anoFalecimento
        );
    }
}
