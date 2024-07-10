package br.com.alura.literalura.service;

import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Scanner;

public class Menu {

    private final FuncoesDB funcoesDB;

    public Menu(FuncoesDB funcoesDB) {
        this.funcoesDB = funcoesDB;
    }

    public void exibeMenu(){
        var menu = """
                Digite a opção desejada:
                1  - Buscar livros pelo título
                2  - Listar livros registrados
                3  - Listar autores registrados
                4  - Listar por autores que estavam vivos em um determinado ano
                5  - Listar livros em um determinado idioma
                6  - Buscar autor por nome
                7  - Buscar autor por ano de nascimento
                8  - Buscar autor por ano de falecimento
                
                0 - Sair
                """;

        System.out.println(menu);

        Scanner leitura = new Scanner(System.in);
        var opcao = leitura.nextInt();
        System.out.println(opcao);

        switch (opcao){
            case 1 :
                funcoesDB.buscarLivroPorTitulo();
                break;
            case 2 :
                funcoesDB.listarLivrosRegistrados();
                break;
            case 3 :
                funcoesDB.listarAutoresRegistrados();
                break;
            case 4 :
                funcoesDB.listarAutoresVivosEmAno();
                break;
            case 5 :
                funcoesDB.listarLivrosPorIdioma();
                break;
            case 6 :
                funcoesDB.buscarAutorPorCampo("name");
                break;
            case 7 :
                funcoesDB.buscarAutorPorCampo("birth_year");
                break;
            case 8 :
                funcoesDB.buscarAutorPorCampo("death_year");
                break;
        }
    }
}
