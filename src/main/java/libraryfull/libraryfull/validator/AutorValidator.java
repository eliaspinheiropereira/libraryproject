package libraryfull.libraryfull.validator;

import libraryfull.libraryfull.exceptions.RegistroDuplicadoException;
import libraryfull.libraryfull.model.Autor;
import libraryfull.libraryfull.repositories.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AutorValidator {

    private final AutorRepository autorRepository;

    public void validar(Autor autor) {
        if(existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoException("Autor já cadastrado");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        Optional<Autor> autorEncontrado = this.autorRepository.findByNomeAndNacionalidadeAndDataNascimento(
                autor.getNome(), autor.getNacionalidade(), autor.getDataNascimento());

        // logica para verificar se existe autor na hora de criar.
        if (autor.getId() == null){
            return autorEncontrado.isPresent();
        }

        // não posso atualizar o usuario existente com o um usuario que ja existe no banco
        if(!autorEncontrado.isPresent()){
            return false;
        }

        return !autor.getId().equals(autorEncontrado.get().getId());
    }
}
