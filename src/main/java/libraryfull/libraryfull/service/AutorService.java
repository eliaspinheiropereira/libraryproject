package libraryfull.libraryfull.service;

import libraryfull.libraryfull.dto.AutorDTO;
import libraryfull.libraryfull.exceptions.OperacaoNaoPermitidaException;
import libraryfull.libraryfull.model.Autor;
import libraryfull.libraryfull.repositories.AutorRepository;
import libraryfull.libraryfull.repositories.LivroRepository;
import libraryfull.libraryfull.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;
    private final AutorValidator autorValidator;

    public Page<Autor> buscarAutores(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.autorRepository.findAll(pageable);
    }

    public AutorDTO buscarAutoresPorId(Long id){
        Optional<Autor> autorOptional = this.autorRepository.findById(id);
        if(autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDTO autorDTO = new AutorDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getNacionalidade(),
                    autor.getDataNascimento()
            );
            return autorDTO;
        }
        return null;
    }

    public void salvarAutor(AutorDTO autorDTO) {
        Autor autor = autorDTO.mapearParaAutor();
        this.autorValidator.validar(autor);
        this.autorRepository.save(autor);
    }

    public void atualizarAutor(Long id, AutorDTO autorDTO) {
        Optional<Autor> autorOptional = this.autorRepository.findById(id);
        Autor autor = autorOptional.get();

        if (autor.getId() == null){
            throw new IllegalArgumentException("para atualizar é necessario que o autor esteja salvo na base.");
        }else {
            autor.setNome(autorDTO.nome());
            autor.setNacionalidade(autorDTO.nacionalidade());
            autor.setDataNascimento(autorDTO.dataNascimento());
            this.autorValidator.validar(autor);
            this.autorRepository.save(autor);
        }
    }

    public void excluirAutor(Long id) {
        Optional<Autor> autorOptional = this.autorRepository.findById(id);
        Autor autor = autorOptional.get();

        if(possuiLivros(autor)){
            throw new OperacaoNaoPermitidaException("não é permitido excluir autor que possui livros cadastrados.");
        }

        this.autorRepository.deleteById(id);
    }

    private boolean possuiLivros(Autor autor){
        return this.livroRepository.existsByAutor(autor);
    }
}
