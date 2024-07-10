package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

     @Query(value = "SELECT * FROM livros l WHERE :idioma = ANY(l.languages)", nativeQuery = true)
     List<Livro> livrosPorIdioma(String idioma);
     // @Query(value = "SELECT l FROM Livro l where l.languages IN :idiomas")
     // List<Livro> livrosPorIdioma(@Param("idiomas") List<String> idiomas);
}
