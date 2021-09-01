package br.com.proieto.springboot.repository;

import br.com.proieto.springboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select u from Usuario u where u.nome like %?1%")
    List<Usuario> buscarNome(String name);

}
