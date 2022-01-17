package br.com.estoque.model;

import java.util.Date;

public class Estoque
{
	private int id_estoque;
	private int quant_atual;
	private int quant_min;
	
	private Date date_register;
	
	private String nome;
	private String modelo;
	private String marca;
	private String descricao;
	private String cod_fabricante;
	private String foto;
	private String posicao_prateleira;
	private String setor;
	
	
	public int getQuant_atual() {
		return quant_atual;
	}
	public void setQuant_atual(int quant_atual) {
		this.quant_atual = quant_atual;
	}
	public int getQuant_min() {
		return quant_min;
	}
	public void setQuant_min(int quant_min) {
		this.quant_min = quant_min;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCod_fabricante() {
		return cod_fabricante;
	}
	public void setCod_fabricante(String cod_fabricante) {
		this.cod_fabricante = cod_fabricante;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Date getDate_register() {
		return date_register;
	}
	public void setDate_register(Date date_register) {
		this.date_register = date_register;
	}
	public String getPosicao_prateleira() {
		return posicao_prateleira;
	}
	public void setPosicao_prateleira(String posicao_prateleira) {
		this.posicao_prateleira = posicao_prateleira;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public int getId_estoque() {
		return id_estoque;
	}
	public void setId_estoque(int id_estoque) {
		this.id_estoque = id_estoque;
	}
	
	
}