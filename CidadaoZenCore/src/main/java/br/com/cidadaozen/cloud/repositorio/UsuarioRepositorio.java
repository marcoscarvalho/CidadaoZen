package br.com.cidadaozen.cloud.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

	public Usuario findByEmail(String email);

	public Usuario findByTelefone(String telefone);

}
