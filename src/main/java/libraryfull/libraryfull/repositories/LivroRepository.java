package libraryfull.libraryfull.repositories;

import libraryfull.libraryfull.model.Autor;
import libraryfull.libraryfull.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    boolean existsByAutor(Autor autor);
}
