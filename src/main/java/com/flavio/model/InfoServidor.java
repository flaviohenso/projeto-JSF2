package com.flavio.model;

import java.util.Date;


public class InfoServidor {

	 private Integer id;
	 private String nome;
	 private String ip;
	 private String tempoLigado;
	 private Boolean threadLigada;
	 private String versaoBanco;
	 private String versaoSINfield;
	 private String usuarioUltimoAcesso;
	 private Date horarioUltimoAcesso;
	 private String sistemaOperacional; 
	 private String espacoEmDisco;
	 
	 private String statusConexao;
	 private Date dataAtualizacao;
	 private String usuarioAtualizacao;
	 
	 private Integer qtdFornecedores;
	 private Integer qtdProdutos;
	 private Integer qtdFuncionarios;
	 private Integer qtdUsuarios;
	 
	 private Date ultimoRegistroOnline;
	 
	 public InfoServidor(){
		 
	 }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTempoLigadoString(){
		if (tempoLigado!=null) {
			return tempoLigado.substring(tempoLigado.indexOf("up ")+3, tempoLigado.indexOf(",")+7);
		}else{
			return "";
		}
	}
	
	public String getTempoLigado() {
		return tempoLigado;
	}

	public void setTempoLigado(String tempoLigado) {
		this.tempoLigado = tempoLigado;
	}

	public Boolean getThreadLigada() {
		return threadLigada;
	}

	public void setThreadLigada(Boolean threadLigada) {
		this.threadLigada = threadLigada;
	}

	public String getVersaoBanco() {
		return versaoBanco;
	}

	public void setVersaoBanco(String versaoBanco) {
		this.versaoBanco = versaoBanco;
	}

	public String getVersaoSINfield() {
		return versaoSINfield;
	}

	public void setVersaoSINfield(String versaoSINfield) {
		this.versaoSINfield = versaoSINfield;
	}

	public String getUsuarioUltimoAcesso() {
		return usuarioUltimoAcesso;
	}

	public void setUsuarioUltimoAcesso(String usuarioUltimoAcesso) {
		this.usuarioUltimoAcesso = usuarioUltimoAcesso;
	}

	public Date getHorarioUltimoAcesso() {
		return horarioUltimoAcesso;
	}

	public void setHorarioUltimoAcesso(Date horarioUltimoAcesso) {
		this.horarioUltimoAcesso = horarioUltimoAcesso;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getEspacoEmDisco() {
		return espacoEmDisco;
	}

	public void setEspacoEmDisco(String espacoEmDisco) {
		this.espacoEmDisco = espacoEmDisco;
	}

	public String getStatusConexao() {
		return statusConexao;
	}

	public void setStatusConexao(String statusConexao) {
		this.statusConexao = statusConexao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public Date getUltimoRegistroOnline() {
		return ultimoRegistroOnline;
	}

	public void setUltimoRegistroOnline(Date ultimoRegistroOnline) {
		this.ultimoRegistroOnline = ultimoRegistroOnline;
	}

	public Integer getQtdFornecedores() {
		return qtdFornecedores;
	}

	public void setQtdFornecedores(Integer qtdFornecedores) {
		this.qtdFornecedores = qtdFornecedores;
	}

	public Integer getQtdProdutos() {
		return qtdProdutos;
	}

	public void setQtdProdutos(Integer qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

	public Integer getQtdFuncionarios() {
		return qtdFuncionarios;
	}

	public void setQtdFuncionarios(Integer qtdFuncionarios) {
		this.qtdFuncionarios = qtdFuncionarios;
	}

	public Integer getQtdUsuarios() {
		return qtdUsuarios;
	}

	public void setQtdUsuarios(Integer qtdUsuarios) {
		this.qtdUsuarios = qtdUsuarios;
	}
	 
	
	
	
	 
}
