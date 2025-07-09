package libraryfull.libraryfull.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import libraryfull.libraryfull.model.GeneroLivro;
import libraryfull.libraryfull.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AtualizaLivroDTO(
        @NotBlank(message = "campo obrigatório")
        String isbn,
        @NotBlank(message = "campo obrigatório")
        String titulo,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @NotNull(message = "campo obrigatório")
        @Past(message = "não pode ser uma data futura")
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco
) {
}
