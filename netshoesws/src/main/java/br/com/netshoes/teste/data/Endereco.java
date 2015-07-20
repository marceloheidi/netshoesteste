package br.com.netshoes.teste.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Entidade Endereco
 * @author Marcelo
 *
 */
@Entity
@NamedQueries ({
    @NamedQuery(name="endereco.buscarPorCep", query="from Endereco where cep = :cep")
})
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Endereco")
    @SequenceGenerator(name = "Endereco", sequenceName = "endereco_seq", allocationSize = 1)
	private Long id;
	
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	
	@Transient
	private String message;
	
	public Endereco() {}
	
	public Endereco(Long id, String rua, String bairro, String cidade, String estado, String cep) {
		if (id != null) this.id = id;
		if (rua != null && !rua.equals("")) this.rua = rua;
		if (bairro != null && !bairro.equals("")) this.bairro = bairro;
		if (cidade != null && !cidade.equals("")) this.cidade = cidade;
		if (estado != null && !estado.equals("")) this.estado = estado;
		if (cep != null && !cep.equals("")) this.cep = cep;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
}
