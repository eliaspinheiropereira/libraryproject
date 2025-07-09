package libraryfull.libraryfull.controller;

import jakarta.validation.Valid;
import libraryfull.libraryfull.dto.AutorDTO;
import libraryfull.libraryfull.dto.ErroRespostaDTO;
import libraryfull.libraryfull.exceptions.OperacaoNaoPermitidaException;
import libraryfull.libraryfull.exceptions.RegistroDuplicadoException;
import libraryfull.libraryfull.model.Autor;
import libraryfull.libraryfull.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;
    private Logger logger = LoggerFactory.getLogger(AutorController.class);


    @GetMapping
    public ResponseEntity<Page<Autor>> buscarAutores(
            @RequestParam(value = "page", required = true) int page,
            @RequestParam(value = "size", required = true) int size
    ) {
        logger.info("GET -> /autores");
        var lista = this.autorService.buscarAutores(page, size);
        return new ResponseEntity(lista.getContent(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<AutorDTO>> buscarAutoresPorId(
            @PathVariable Long id
    ) {
        logger.info("GET -> /autores/" + id);
        var lista = this.autorService.buscarAutoresPorId(id);
        return new ResponseEntity(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> salvarAutor(
            @RequestBody @Valid AutorDTO autorDTO
    ) {
        logger.info("POST -> /autores");
        this.autorService.salvarAutor(autorDTO);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizarAutor(
            @PathVariable(value = "id") Long id,
            @RequestBody AutorDTO autorDTO
    ) {
        logger.info("PUT -> /autores/" + id);
        this.autorService.atualizarAutor(id, autorDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);


    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluirAutor(@PathVariable("id") Long id) {
        logger.info("DELETE -> /autores/" + id);
        this.autorService.excluirAutor(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}
