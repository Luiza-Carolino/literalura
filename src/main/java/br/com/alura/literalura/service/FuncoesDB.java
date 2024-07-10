package br.com.alura.literalura.service;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class FuncoesDB {

    private ConverteDados conversor = new ConverteDados();
    private final String enderecoApi = "https://gutendex.com";
    private ConsumoApi api = new ConsumoApi(enderecoApi);
    private Scanner leitura = new Scanner(System.in);

    @Autowired
    private LivroRepository repositorioLivro;
    @Autowired
    private AutorRepository repositorioAutor;

     public FuncoesDB(LivroRepository repositorioLivro, AutorRepository repositorioAutor) {
         this.repositorioLivro = repositorioLivro;
         this.repositorioAutor = repositorioAutor;
     }

    public void buscarLivroPorTitulo() {

        // System.out.println("repositorioLivro" + repositorioLivro);
        // System.out.println("repositorioAutor" + repositorioAutor);

        System.out.println("Digite o título do livro que deseja procurar:");
        String titulo = leitura.nextLine();
        String parametro = "/books/?search="+titulo;

        String json = api.buscarApi(parametro);

        // System.out.println("==========");
        // System.out.println("json: " + json);
        // System.out.println("==========");

        RespostaApi dadosRespostaApi = conversor.obterDados(json, RespostaApi.class);

        // System.out.println("==========");
        // System.out.println("dadosRespostaApi: " + dadosRespostaApi);
        // System.out.println("==========");

        List<DadosLivro> dadosLivros = dadosRespostaApi.results();
        List<Livro> livros = new ArrayList<Livro>();
        List<Autor> autores = new ArrayList<Autor>();

        // System.out.println("==========");
        // System.out.println("dadosLivros: " + dadosLivros);
        // System.out.println("==========");

        if(dadosLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        }
        else {
            System.out.println("===== Livro(s) encontrado(s): =====");
            dadosLivros.stream().map(dadosLivro -> new Livro(dadosLivro)).forEach(livro -> {
                System.out.println(livro);
                livros.add(livro);
                autores.addAll(livro.getAuthors());
            });
        }

        if(!livros.isEmpty()) {
            repositorioLivro.saveAll(livros);
        }

    }

    public void listarLivrosRegistrados() {
         List<Livro> livros = repositorioLivro.findAll();

         if(livros.isEmpty()) {
             System.out.println("Não há livros registrados na base de dados.");
         }
         else {
             System.out.println("===== Livro(s) encontrado(s): =====");
             livros.forEach(livro -> System.out.println(livro));
         }
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = repositorioAutor.findAll();

        if(autores.isEmpty()) {
            System.out.println("Não há autores registrados na base de dados.");
        }
        else {
            System.out.println("===== Autor(es) encontrado(s): =====");
            autores.forEach(autor -> System.out.println(autor));
        }
    }

    public void listarAutoresVivosEmAno() {
         System.out.println("Digite o ano para a consulta:");
         Integer ano = leitura.nextInt();

         List<Autor> autores = repositorioAutor.autoresVivosEmAno(ano);

        if(autores.isEmpty()) {
            System.out.println("Não há autores vivos no ano de " + ano + " registrados na base de dados.");
        }
        else {
            System.out.println("===== Autor(es) vivo(s) no ano de " + ano + " encontrado(s): =====");
            autores.forEach(autor -> System.out.println(autor));
        }
    }

    public void listarLivrosPorIdioma() {
        // TODO: Listar opções de idioma
        System.out.println("Digite o idioma para a consulta:");
        String idioma = leitura.next().trim();

        List<Livro> livros = repositorioLivro.livrosPorIdioma(idioma);

        if(livros.isEmpty()) {
            System.out.println("Não há livros para o(s) idioma(s) " + idioma + " registrados na base de dados.");
        }
        else {
            System.out.println("===== Livros disponíveis para o(s) idioma(s) " + idioma + " encontrado(s): =====");
            livros.forEach(livro -> System.out.println(livro));
        }
    }

    public void buscarAutorPorCampo(String campo) {
         List<Autor> autores = new ArrayList<Autor>();

         switch (campo) {
             case "name":
                 System.out.println("Digite o nome do autor que deseja procurar:");
                 String nome = leitura.next().trim();
                 autores = repositorioAutor.findByNameContainingIgnoreCase(nome);
                 break;
             case "birth_year":
                 System.out.println("Digite o ano de nascimento do autor que deseja procurar:");
                 int anoNascimento = leitura.nextInt();
                 autores = repositorioAutor.findByBirthYear(anoNascimento);
                 break;
             case "death_year":
                 System.out.println("Digite o ano de falecimento do autor que deseja procurar:");
                 int anoFalecimento = leitura.nextInt();
                 autores = repositorioAutor.findByDeathYear(anoFalecimento);
                 break;
         }

        if(autores.isEmpty()) {
            System.out.println("Não há autores com os dados informados registrados na base de dados.");
        }
        else {
            System.out.println("===== Autor(es) com dados correspondentes encontrado(s): =====");
            autores.forEach(autor -> System.out.println(autor));
        }
    }
}
