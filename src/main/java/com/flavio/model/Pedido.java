package com.flavio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity(name = "pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigo;
	private Date dataCriacao;
	private Date dataEntrega;
	private String observacao;
	private StatusPedido statusPedido;
	private FormaPagamento formaPagamento;
	private BigDecimal valorFrete;
	private BigDecimal valorDesconto;
	private BigDecimal valorTotal;
	private Cliente cliente;
	private Fornecedor fornecedor;
	private EnderecoEntrega enderecoEntrega;
	private List<ItemPedido> itemPedidos = new ArrayList<ItemPedido>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@NotBlank @NotNull
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @Temporal(TemporalType.TIMESTAMP):defini que o campo data_criacao deve 
	 * salvar a hora como minutos e segundos
	 */
	@Temporal(TemporalType.TIMESTAMP) 
	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega", nullable = false)
	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	/**
	 * @Column(columnDefinition = "text"):  Definição especial para a coluna ("text", "blob",...)
	 * A definição do colummDefinition pode ser um problema caso haja a necessidade de migra para outro banco
	 * O ideal é deixar a JPA inferir o tipo da coluna de acordo com o tipo Java do atributo.
	 */
	@Column(columnDefinition = "text")
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	/**
	 *@Enumerated(EnumType.STRING): defini que vai ser atribuido para o campo a string do Enum
	 *@Enumerated(EnumType.ORDINAL): defini que vai ser atribuido para o campo um numeral referente a posição no Enum 
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "status_pedido", nullable = false, length = 30)
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento", nullable = false, length = 30)
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Column(name = "valor_frete", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	@Column(name = "valor_desconto", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	@Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @ManyToOne: Muitos pedidos podem ser para um cliente
	 */
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * @Embedded: Imbute os campos da classe EnderecoEntrega na entidade Pedido
	 */
	@Embedded
	public EnderecoEntrega getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	/**
	 * @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true): Um pedido pode ter 
	 * varios itens, onde esta mapeado de forma contrário ao "pedido" na classe ItemPedido, esta definido para
	 * salvar e remover em cascata os itens e não pode haver ItemPedido orfã, ou seja, sem um Pai.
	 */
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
