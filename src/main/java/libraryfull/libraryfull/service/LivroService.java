package libraryfull.libraryfull.service;

import libraryfull.libraryfull.dto.AtualizaLivroDTO;
import libraryfull.libraryfull.dto.AutorDTO;
import libraryfull.libraryfull.dto.CadastroLivroDTO;
import libraryfull.libraryfull.dto.PesquisaLivroDTO;
import libraryfull.libraryfull.exceptions.OperacaoNaoPermitidaException;
import libraryfull.libraryfull.model.Autor;
import libraryfull.libraryfull.model.Livro;
import libraryfull.libraryfull.repositories.AutorRepository;
import libraryfull.libraryfull.repositories.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;


    public void salvarLivro(CadastroLivroDTO dto){
        Livro livro = dto.mapaearParaLivro();
        Optional<Autor> autorExiste = this.autorRepository.findById(dto.idAutor());

        if(!autorExiste.isPresent()){
            throw new OperacaoNaoPermitidaException("autor não encontrado, por isso não podemos cadastrar o livro");
        }else {
            Autor autor = autorExiste.get();
            livro.setAutor(autor);
            this.livroRepository.save(livro);
        }
    }

    public PesquisaLivroDTO buscarLivroPorId(Long id) {
        Livro livroOptional = this.livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(livroOptional != null){
            Livro livro = livroOptional;
            // para nivel de conhecimento.
            // pois podemos deixar no dto de pesquisalivrodto apenas Autor autor que ja faz essa chamada de autordto abaixo.
            AutorDTO autorDTO = new AutorDTO(
                    livro.getAutor().getId(),
                    livro.getAutor().getNome(),
                    livro.getAutor().getNacionalidade(),
                    livro.getAutor().getDataNascimento()
            );

            PesquisaLivroDTO livroDTO = new PesquisaLivroDTO(
                livro.getId(),
                    livro.getIsbn(),
                    livro.getTitulo(),
                    livro.getDataPublicacao(),
                    livro.getGenero(),
                    livro.getPreco(),
                    autorDTO
            );
            return livroDTO;
        }
        return null;
    }

    public void excluirLivro(Long id) {
        Livro livro = this.livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if(livro.getId() != null){
            this.livroRepository.deleteById(livro.getId());
        }
    }

    public void atualizarLivro(AtualizaLivroDTO dto, Long id){
        Livro existeLivro = this.livroRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(existeLivro.getId() != null){
            existeLivro.setIsbn(dto.isbn());
            existeLivro.setTitulo(dto.titulo());
            existeLivro.setDataPublicacao(dto.dataPublicacao());
            existeLivro.setGenero(dto.genero());
            existeLivro.setPreco(dto.preco());
            this.livroRepository.save(existeLivro);
        }
    }
}
