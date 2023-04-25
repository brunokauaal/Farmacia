package com.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Produtos;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.repository.ProdutosRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {

	@Autowired
	private ProdutosRepository produtosRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	/* METODO PARA LISTAR TODOS OS PRODUTOS DA FARMACIA */

	@GetMapping
	public ResponseEntity<List<Produtos>> getAll() {

		return ResponseEntity.ok(produtosRepository.findAll());
	}

	/* METODO BUSCAR POR ID */

	@GetMapping("/{id}")
	public ResponseEntity<Produtos> getById(@PathVariable Long id) {
		return produtosRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	/* METODO BUSCAR POR NOME E LABORATORIO */

	@GetMapping("/nome/{nome}/elaboratorio/{laboratorio}")
	public ResponseEntity<List<Produtos>> getByNomeELaboratorio(@PathVariable String nome,
			@PathVariable String laboratorio) {
		return ResponseEntity.ok(produtosRepository.findAllByNomeAndLaboratorio(nome, laboratorio));
	}

	/* CONSULTA POR NOME OU LABORATORIO */

	@GetMapping("/nome/{nome}/oulaboratorio/{laboratorio}")
	public ResponseEntity<List<Produtos>> getByNomeOuLaboratorio(@PathVariable String nome,
			@PathVariable String laboratorio) {
		return ResponseEntity.ok(produtosRepository.findAllByNomeOrLaboratorio(nome, laboratorio));
	}

	/* METODO CADASTRAR PRODUTOS */

	@PostMapping
	public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produtos) {
		if (categoriaRepository.existsById(produtos.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));

		return ResponseEntity.badRequest().build();
	}

	/* METODO BUSCAR MAIOR PREÇO */

	@GetMapping("/preco/{precomaior}")
	public ResponseEntity<List<Produtos>> getByPrecoMaior(@PathVariable("precomaior") BigDecimal precoMaior) {
		List<Produtos> produtos = produtosRepository.findAllByPrecoGreaterThanOrderByPreco(precoMaior);
		return ResponseEntity.ok(produtos);
	}

	/* METODO BUSCAR MENOR PREÇO */

	@GetMapping("/preco_menor/{precomenor}")
	public ResponseEntity<List<Produtos>> getByPrecoMenor(@PathVariable("precomenor") BigDecimal precomenor) {
		List<Produtos> produtos = produtosRepository.findAllByPrecoLessThanOrderByPrecoDesc(precomenor);
		return ResponseEntity.ok(produtos);
	}

	/* METODO ATUALIZAR PRODUTO */

	@PutMapping
	public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produtos) {
		return produtosRepository.findById(produtos.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produtos)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	/* METODO DELETAR PRODUTOS CADASTRADOS */

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {

		Optional<Produtos> produtos = produtosRepository.findById(id);
		if (produtos.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtosRepository.deleteById(id);

	}

	/* CONSULTA POR PREÇO ENTRE DOIS VALORES (Between) */

	@GetMapping("/preco_inicial/{inicio}/preco_final/{fim}")
	public ResponseEntity<List<Produtos>> getByPrecoEntreNatve(@PathVariable BigDecimal inicio,
			@PathVariable BigDecimal fim) {
		return ResponseEntity.ok(produtosRepository.findAllByPrecoBetween(inicio, fim));
	}

}
