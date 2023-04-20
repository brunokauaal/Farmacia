package com.generation.farmacia.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produtos {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@NotBlank(message =  "Nome Do Produto É Obrigatorio!")
	@Size(min = 5, max = 50, message = "O Nome Do Produto Deve Possuir No Minimo 5 e no maximo 50 caracteres!")
	private String nome;
	
	
	
	@NotBlank(message =  "A Descrição É Obrigatorio!")
	@Size(min = 5, max = 500, message = "A Descrição  Deve Possuir No Minimo 5 e no maximo 500 caracteres!")
	private String descricao;
	
	
	private int quantidade;
	
	
	@NotBlank(message =  "Nome Do Laboratório É Obrigatorio!")
	@Size(min = 5, max = 100, message = "O Nome Do Laboratório  Deve Possuir No Minimo 5 e no maximo 100 caracteres!")
	private String laboratorio;
	
	@NotNull(message ="O Preço é obrigatorio!")
	@Positive(message = "O Numero Tem Que Ser Maior Que Zero!")
	private BigDecimal preco;
	
	private String foto;

	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Categoria categoria;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
	
	
	
	
}
