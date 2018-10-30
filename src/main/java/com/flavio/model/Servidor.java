package com.flavio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity(name = "SERVIDOR")
public class Servidor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String ip;
	private String nome;
	private String descricao;
	private String status;
	@Column(name="ip_redelocal")
	private String ipLocal;
	@Column(name="diretorio_download_documentos")
	private String diretorioDownloadDocumentos;
	
	//Propriedades para controle de sincronismo
	@Column(name="ult_fornecedor")
	private Date dtUltimoFornecedor;
	@Column(name="ult_produto")
	private Date dtUltimoProduto;
	@Column(name="ult_produtocompl")
	private Date dtUltimoProdutoCompl;
	@Column(name="ult_modulo")
	private Date dtUltimoModulo;
	@Column(name="ult_bem")
	private Date dtUltimoBem;
	@Column(name="data_sincronismo_eventos")
	private Date dtUltimoSincronismoEventosFolha;
	@Column(name="usuario_sincronismo_eventos")
	private String usuarioUltimoSincronismoEventosFolha;
	@Column(name="ult_chapa", columnDefinition="SMALLINT")
	private Integer codUltimaChapa;
	@Column(name="q_forn", columnDefinition="SMALLINT")
	private Integer codUltimaForn;
	@Column(name="ult_formapagamento")
	private Date dtUltimoFormaPagamento;
	
	@Transient
	private InfoServidor infoServidor;
	@Transient
	private String ipWS;
	@Column(name="obs")
	private String obs;
	@Column(name="patrimonio")
	private String patrimonio;
	
	public Servidor(){
		
	}

	public Servidor(int id){
		this.id = id;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIpLocal() {
		return ipLocal;
	}

	public void setIpLocal(String ipLocal) {
		this.ipLocal = ipLocal;
	}



	public Date getDtUltimoFornecedor() {
		return dtUltimoFornecedor;
	}



	public void setDtUltimoFornecedor(Date dtUltimoFornecedor) {
		this.dtUltimoFornecedor = dtUltimoFornecedor;
	}



	public Date getDtUltimoProduto() {
		return dtUltimoProduto;
	}



	public void setDtUltimoProduto(Date dtUltimoProduto) {
		this.dtUltimoProduto = dtUltimoProduto;
	}



	public Date getDtUltimoProdutoCompl() {
		return dtUltimoProdutoCompl;
	}



	public void setDtUltimoProdutoCompl(Date dtUltimoProdutoCompl) {
		this.dtUltimoProdutoCompl = dtUltimoProdutoCompl;
	}



	public Date getDtUltimoModulo() {
		return dtUltimoModulo;
	}



	public void setDtUltimoModulo(Date dtUltimoModulo) {
		this.dtUltimoModulo = dtUltimoModulo;
	}



	public Date getDtUltimoBem() {
		return dtUltimoBem;
	}



	public void setDtUltimoBem(Date dtUltimoBem) {
		this.dtUltimoBem = dtUltimoBem;
	}



	public Integer getCodUltimaChapa() {
		return codUltimaChapa;
	}



	public void setCodUltimaChapa(Integer codUltimaChapa) {
		this.codUltimaChapa = codUltimaChapa;
	}



	public Integer getCodUltimaForn() {
		return codUltimaForn;
	}



	public void setCodUltimaForn(Integer codUltimaForn) {
		this.codUltimaForn = codUltimaForn;
	}



	public String getDiretorioDownloadDocumentos() {
		return diretorioDownloadDocumentos;
	}



	public void setDiretorioDownloadDocumentos(String diretorioDownloadDocumentos) {
		this.diretorioDownloadDocumentos = diretorioDownloadDocumentos;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public InfoServidor getInfoServidor() {
		return infoServidor;
	}



	public void setInfoServidor(InfoServidor infoServidor) {
		this.infoServidor = infoServidor;
	}



	public String getObs() {
		return obs;
	}



	public void setObs(String obs) {
		this.obs = obs;
	}


	public String getPatrimonio() {
		return patrimonio;
	}


	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}


	public Date getDtUltimoSincronismoEventosFolha() {
		return dtUltimoSincronismoEventosFolha;
	}


	public void setDtUltimoSincronismoEventosFolha(
			Date dtUltimoSincronismoEventosFolha) {
		this.dtUltimoSincronismoEventosFolha = dtUltimoSincronismoEventosFolha;
	}


	public String getUsuarioUltimoSincronismoEventosFolha() {
		return usuarioUltimoSincronismoEventosFolha;
	}


	public void setUsuarioUltimoSincronismoEventosFolha(
			String usuarioUltimoSincronismoEventosFolha) {
		this.usuarioUltimoSincronismoEventosFolha = usuarioUltimoSincronismoEventosFolha;
	}

	
	
	/**
	 * @return the dtUltimoFormaPagamento
	 */
	public Date getDtUltimoFormaPagamento() {
		return dtUltimoFormaPagamento;
	}

	/**
	 * @param dtUltimoFormaPagamento the dtUltimoFormaPagamento to set
	 */
	public void setDtUltimoFormaPagamento(Date dtUltimoFormaPagamento) {
		this.dtUltimoFormaPagamento = dtUltimoFormaPagamento;
	}

	public String getIpWS() {
		if (getStatus().compareTo("1")==0) {
			ipWS = descricao;//ip;
		} else {
			ipWS = "192.168.0.251";
		}		
		return ipWS;
	}

}
