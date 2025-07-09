package libraryfull.libraryfull.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import libraryfull.libraryfull.model.Autor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record AutorDTO(
        Long id,
        @NotBlank(message = "não pode estar em branco") String nome,
        @NotBlank(message = "não pode estar em branco") String nacionalidade,
        @JsonFormat(pattern = "dd/MM/yyyy") @NotNull(message = "não pode ser null") LocalDate dataNascimento
) {


    public Autor mapearParaAutor(){
        Autor autor = new Autor();

        autor.setNome(this.nome);
        autor.setNacionalidade(this.nacionalidade);
        autor.setDataNascimento(this.dataNascimento);

        return autor;
    }
}
