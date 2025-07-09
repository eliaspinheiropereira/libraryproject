package libraryfull.libraryfull.controller;

import jakarta.validation.Valid;
import libraryfull.libraryfull.dto.AtualizaLivroDTO;
import libraryfull.libraryfull.dto.CadastroLivroDTO;
import libraryfull.libraryfull.dto.ErroRespostaDTO;
import libraryfull.libraryfull.dto.PesquisaLivroDTO;
import libraryfull.libraryfull.exceptions.OperacaoNaoPermitidaException;
import libraryfull.libraryfull.exceptions.RegistroDuplicadoException;
import libraryfull.libraryfull.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
    private Logger logger = LoggerFactory.getLogger(LivroController.class);


    @PostMapping
    public ResponseEntity<?> salvarLivro(
            @RequestBody
            @Valid
            CadastroLivroDTO dto
    ) {
        logger.info("POST -> /livros");

        this.livroService.salvarLivro(dto);
        return new ResponseEntity(HttpStatus.CREATED);

    }


    @GetMapping("{id}")
    public ResponseEntity<PesquisaLivroDTO> buscarLivroPorId(
            @PathVariable("id") Long id
    ) {
        logger.info("GET -> /livros/" + id);
        var lista = this.livroService.buscarLivroPorId(id);
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirLivro(
            @PathVariable("id") Long id
    ){
        logger.info("DELETE -> /livros/" + id);
        this.livroService.excluirLivro(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizarLivro(
            @PathVariable("id") Long id,
            @RequestBody @Valid AtualizaLivroDTO dto
    ){
        logger.info("PUT -> /livros/" + id);
        this.livroService.atualizarLivro(dto, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
