package br.com.netshoes.teste.data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.netshoes.teste.data.Endereco;

/**
 * EnderecoImpl serviços relacionados ao banco de dados
 * @author Marcelo Heidi Yokoyama
 */
@Repository
public class EnderecoImpl {

	@PersistenceContext
	EntityManager entityManager;
	
	/**
	 * Método consultarPorCep
	 * Realiza a consulta no banco de dados através da variável cep
	 */
	@SuppressWarnings("unchecked")
	public Endereco consultarPorCep(String cep) {

		Endereco ender = null;

		Query query = entityManager.createNamedQuery("endereco.buscarPorCep");
		query.setParameter("cep", cep);
		List<Endereco> enders = query.getResultList();

		if (enders != null && enders.size() > 0) ender = enders.get(0);

		return ender;
	}

	/**
	 * Método consultarPorId
	 * Realiza a consulta no banco de dados através da variável id
	 */
	public Endereco consultarPorId(Long id) {
		Endereco ender = entityManager.find(Endereco.class, id);
		return ender;
	}
	
	/**
	 * Método deletar
	 * Realiza a pesquisa do endereco por id e caso encontre faz a remoção
	 */
	@Transactional
	public void deletar(Long id) throws Exception {
		Endereco endereco = consultarPorId(id);
		if (endereco != null) {
			entityManager.remove(endereco);
		} else {
			throw new Exception("Endereco nao encontrado.");
		}
	}

	/**
	 * Método salvar
	 * Salva o registro
	 */
	@Transactional
	public void salvar(Endereco endereco) {
		entityManager.persist(endereco);
	}
	
	/**
	 * Método atualizar
	 * Realiza a pesquisa do endereco por id e caso encontre atualiza apenas os campos informados
	 */
	@Transactional
	public void atualizar(Endereco endereco) {
		Endereco newEnder = consultarPorId(endereco.getId());
		if (newEnder != null) {
			if (endereco.getRua() != null) newEnder.setRua(endereco.getRua());
			if (endereco.getBairro() != null) newEnder.setBairro(endereco.getBairro());
			if (endereco.getCidade() != null) newEnder.setCidade(endereco.getCidade());
			if (endereco.getEstado() != null) newEnder.setEstado(endereco.getEstado());
			if (endereco.getCep() != null) newEnder.setCep(endereco.getCep());
			entityManager.merge(newEnder);
		}
	}
}
