package br.com.alura.literalura;

import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.FuncoesDB;
import br.com.alura.literalura.service.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LiteraluraApplication implements CommandLineRunner {

//	private Scanner leitura = new Scanner(System.in);
//	private ConsumoApi consumo = new ConsumoApi();
//	private ConverteDados conversor = new ConverteDados();
//	private final String ENDERECO = "https://gutendex.com/";
//	private List<DadosSerie> dadosSeries = new ArrayList<>();
//
//	private SerieRepository repositorio;
//	private List<Serie> series = new ArrayList<>();
//	private Optional<Serie> serieBusca;

	@Autowired
	private LivroRepository repositorioLivro;
	@Autowired
	private AutorRepository repositorioAutor;

	public static void main(String[] args) { SpringApplication.run(LiteraluraApplication.class, args); }

	@Override
	public void run(String... args) throws Exception {
		FuncoesDB funcoesDB = new FuncoesDB(repositorioLivro, repositorioAutor);
		Menu menu = new Menu(funcoesDB);

		// System.out.println("repositorioLivro" + repositorioLivro);
		// System.out.println("repositorioAutor" + repositorioAutor);
		menu.exibeMenu();
	}
}
