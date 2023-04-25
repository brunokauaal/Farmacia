package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.farmacia.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

	List<Produtos> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

	List<Produtos> findAllByPrecoBefore(@Param("preco") BigDecimal preco);

	public List<Produtos> findAllByNomeAndLaboratorio(String nome, String laboratorio);

	public List<Produtos> findAllByNomeOrLaboratorio(String nome, String laboratorio);

	List<Produtos> findAllByPrecoGreaterThanOrderByPreco(@Param("preco") BigDecimal preco);

	List<Produtos> findAllByPrecoBetween(BigDecimal inicio, BigDecimal fim);

	List<Produtos> findAllByPrecoLessThanOrderByPrecoDesc(@Param("preco") BigDecimal preco);
}
