package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String> {
    @Query("SELECT a FROM Autor a WHERE a.birth_year <= :ano AND a.death_year > :ano")
    List<Autor> autoresVivosEmAno(int ano);

    List<Autor> findByNameContainingIgnoreCase(String name);

    @Query("SELECT a FROM Autor a WHERE a.birth_year = :birth_year")
    List<Autor> findByBirthYear(int birth_year);

    @Query("SELECT a FROM Autor a WHERE a.death_year = :death_year")
    List<Autor> findByDeathYear(int death_year);
}
