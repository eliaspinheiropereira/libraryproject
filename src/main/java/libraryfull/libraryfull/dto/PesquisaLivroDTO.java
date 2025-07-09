package libraryfull.libraryfull.dto;

import libraryfull.libraryfull.model.Autor;
import libraryfull.libraryfull.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PesquisaLivroDTO(
        Long id,
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        AutorDTO autorDTO
) {
}
