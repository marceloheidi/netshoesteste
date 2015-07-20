package br.com.netshoes.teste.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.netshoes.teste.data.Endereco;
import br.com.netshoes.teste.service.EnderecoService;

/**
 * EnderecoController com os requests para CRUD do endereco
 * @author Marcelo Heidi Yokoyama
 */
@Controller()
public class EnderecoController implements Serializable {

	private static final long serialVersionUID = -5408136305699674463L;

	@Autowired
	private EnderecoService svc;
	
	/**
	 * consultar
	 * @param cep (String)
	 * @return Endereco
	 */
	@RequestMapping(value="/consultar", method={RequestMethod.POST,RequestMethod.GET}, consumes={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Endereco consultar(@RequestBody Endereco endereco) {
		
		return svc.consultar(endereco.getCep(), 1);
	}
	
	/**
	 * incluir
	 * @param endereco (Endereco)
	 * @return String
	 */
	@RequestMapping(value="/incluir", method={RequestMethod.POST,RequestMethod.GET}, consumes={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public String incluir(@RequestBody Endereco endereco) {
		
		return svc.incluir(endereco);
	}
	
	/**
	 * deletar
	 * @param id (Long)
	 * @return String
	 */
	@RequestMapping(value="/deletar", method={RequestMethod.POST,RequestMethod.GET}, consumes={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public String deletar(@RequestBody Endereco endereco) {
		
		return svc.deletar(endereco.getId());
	}
	
	/**
	 * atualizar
	 * @param endereco (Endereco com campos opcionais)
	 * @return String
	 */
	@RequestMapping(value="/atualizar", method={RequestMethod.POST,RequestMethod.GET}, consumes={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public String atualizar(@RequestBody Endereco endereco) {
		
		return svc.atualizar(endereco);
	}
}
