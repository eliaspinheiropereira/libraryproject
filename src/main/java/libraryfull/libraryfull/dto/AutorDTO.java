package libraryfull.libraryfull.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import libraryfull.libraryfull.model.Autor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record AutorDTO(Long id, String nome, String nacionalidade, @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataNascimento) {


    public Autor mapearParaAutor(){
        Autor autor = new Autor();

        autor.setNome(this.nome);
        autor.setNacionalidade(this.nacionalidade);
        autor.setDataNascimento(this.dataNascimento);

        return autor;
    }
}
