package libraryfull.libraryfull;

import libraryfull.libraryfull.model.Autor;
import libraryfull.libraryfull.repositories.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LibraryfullApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryfullApplication.class, args);
//		AutorRepository autorEntity = contexxt.getBean(AutorRepository.class);
//		registrarAutorTeste(autorEntity);
	}


//	public static void registrarAutorTeste(AutorRepository autorEntity){
//		Autor autor = new Autor();
//		autor.setNome("jorge elias pinheiro pereira");
//		autor.setNacionalidade("brasileiro");
//		autor.setDataNascimento(LocalDate.of(1989, 2, 8));
//
//		var autorRegistrado = autorEntity.save(autor);
//		System.out.println("Autor regiistrado: "+autorRegistrado);
//	}
}
