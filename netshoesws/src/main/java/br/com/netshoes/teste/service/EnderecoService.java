package br.com.netshoes.teste.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.netshoes.teste.data.Endereco;
import br.com.netshoes.teste.data.impl.EnderecoImpl;

/**
 * EnderecoService serviços com modelo de negócio
 * @author Marcelo Heidi Yokoyama
 */
@Service
public class EnderecoService implements Serializable {

	private static final long serialVersionUID = 8747685854734158308L;

	private EnderecoImpl dao;
	
	@Autowired(required=true)
	private EnderecoService(EnderecoImpl dao) {
		this.dao = dao;
	}
	
	private static final String sucesso = "Comando executado com sucesso.";
	private static final String semSucesso = "Falha na execução do comando: ";
	
	/**
	 * Método incluir
	 * Verifica se todos os campos do endereco estao preenchidos e persiste retornando mensagem.
	 */
	public String incluir(Endereco endereco) {
		
		try {
			if (isNotNull(endereco.getRua()) && (endereco.getNumero() > 0) && isNotNull(endereco.getCidade())
					&& isNotNull(endereco.getEstado()) && isNotNull(endereco.getCep())) {
				dao.salvar(endereco);
			} else {
				return semSucesso + "Os campos rua, número, cep, cidade e estado são obrigatórios.";
			}
		} catch (Exception e) {
			return semSucesso + e.getMessage();
		}
		
		return sucesso;
	}
	
	/**
	 * Método deletar
	 * Deleta o registro e retorna mensagem.
	 */
	public String deletar(Long id) {
		
		try {
			dao.deletar(id);
		} catch (Exception e) {
			return semSucesso + e.getMessage();
		}
		
		return sucesso;
	}
	
	/**
	 * Método atualizar
	 * Atualiza os campos informados apenas se o ID também for informado retornando mensagem.
	 */
	public String atualizar(Endereco endereco) {
		
		try {
			if (isNotNull(endereco.getId())) {
				dao.atualizar(endereco);
			}
		} catch (Exception e) {
			return semSucesso + e.getMessage();
		}
		
		return sucesso;
	}
	
	/**
	 * Método isNotNull
	 * Verifica se o objeto não é nulo.
	 */
	private boolean isNotNull(Object valor) {
		return (valor != null && !valor.equals("") ? true : false);
	}

	/**
	 * Método consultar (recursivo)
	 * Caso o CEP seja válido, realiza a consulta no banco de dados até encontrar o endereco, inserindo 0 à direita e parando ao preencher todo o CEP com zeros.
	 */
	public Endereco consultar(String cep, int zeros) {

		Endereco endereco = null;

		if (validarCep(cep) && !cep.equals("00000000")) {

			Endereco ender = dao.consultarPorCep(cep);
			
			if (ender != null && ender.getCep().equals(cep)) {
				return ender;
			}

			if (endereco == null && zeros > 0) {
				endereco = consultar(
						(cep.substring(0, cep.length() - zeros) + preencherZeros(zeros)),
						zeros + 1);
			}
		}

		if (endereco == null) {
			endereco = new Endereco();
			endereco.setMessage("CEP inválido.");
		}
		
		return endereco;
	}

	/**
	 * Método preencherZeros
	 * Auxilia no preenchimento de zeros à direita
	 */
	private String preencherZeros(int z) {

		String zeros = "";
		for (int x = 1; x <= z; x++) {
			zeros = zeros + "0";
		}
		return zeros;
	}

	/**
	 * Método validarCep
	 * Valida o CEP caso seja não nulo e tamanho = 8
	 */
	public boolean validarCep(String cep) {
		return (isNotNull(cep) && cep.length() == 8) ? true : false;
	}
}
