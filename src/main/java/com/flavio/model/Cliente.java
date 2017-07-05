package com.flavio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
/*
 * @DiscriminatorValue contem o valor que irá identificar cada classe na tabela do
 * banco de dados
 */
@DiscriminatorValue("cliente")
public class Cliente extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoPessoa tipo;
	private List<Endereco> enderecos = new ArrayList<Endereco>();

	/*
	 * mappedBy = "cliente" : defini que o lado dominante do relacionamento é Endereço
	 * através do atributo cliente dessa classe.
	 */
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

}
