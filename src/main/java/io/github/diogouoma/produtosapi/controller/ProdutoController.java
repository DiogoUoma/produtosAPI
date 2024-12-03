package io.github.diogouoma.produtosapi.controller;

import io.github.diogouoma.produtosapi.model.Produto;
import io.github.diogouoma.produtosapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        System.out.println("Produto recebido: " + produto);

        var id = UUID.randomUUID().toString();
        produto.setId(id);

        produtoRepository.save(produto);
        System.out.println("Produto salvo com sucesso!");
        return produto;
    }

    @GetMapping("/buscar/{id}")
    public Produto obterPorId(@PathVariable("id") String id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarProduto(@PathVariable String id) {
        produtoRepository.deleteById(id);
        System.out.println("Produto deletado com sucesso!");
    }

    @PutMapping("/atualizar/{id}")
    public void atualizar(@PathVariable String id, @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
        System.out.println("Produto atualizado com sucesso!");
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome) {
        return produtoRepository.findByNome(nome);
    }
}
