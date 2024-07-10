package br.com.alura.literalura.model;

import br.com.alura.literalura.service.ConverteDados;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    private Integer id;
    private String title;
    @Column(insertable=false, updatable=false)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Autor> authors;
    private List<String> languages;
    private Integer download_count;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.id = dadosLivro.id();
        this.title = dadosLivro.title();
        this.authors = dadosLivro.authors().stream().map(dadosAutor -> new Autor(dadosAutor)).toList();
        this.languages = dadosLivro.languages();
        this.download_count = dadosLivro.download_count();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<Autor> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Autor> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Integer getDownload_count() {
        return download_count;
    }
    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    @Override
    public String toString() {
        return String.format("""
                --> Livro: "%s"
                - Identificador: "%s"
                - Autor(es): "%s"
                - Linguagens: "%s"
                - Número de downloads: "%s"
                """
        ,title
        ,id.toString()
        ,authors.stream()
                .map(author -> author.getName().replace(",", ""))
                .collect(Collectors.joining(", "))
        , String.join(", ", languages)
        ,download_count.toString()
        );
    }
}
