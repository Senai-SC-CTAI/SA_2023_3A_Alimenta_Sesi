package com.example.back.controller;

import com.example.back.model.Produto;
import com.example.back.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        // Adiciona uma verificação se o ID do produto já existe
        if (produto.getId() != null && produtoRepository.existsById(produto.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Produto novoProduto = produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping("/categorias/{categoria}")
    public List<Produto> listarProdutosPorCategoria(@PathVariable String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }

    @GetMapping("/calorias/{calorias}")
    public List<Produto> listarProdutosPorCalorias(@PathVariable double calorias) {
        return produtoRepository.findByCaloriasLessThanEqual(calorias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        // Verifica se o produto a ser atualizado existe
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Produto produtoExistente = produtoRepository.findById(id).orElse(null);

        if (produtoExistente != null) {
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setDescricao(produto.getDescricao());
            produtoExistente.setCategoria(produto.getCategoria());
            produtoExistente.setCalorias(produto.getCalorias());
            produtoExistente.setPreco(produto.getPreco());
            produtoExistente.setImagem(produto.getImagem());
            produtoExistente.setQuantidadeDisponivel(produto.getQuantidadeDisponivel());

            produtoRepository.save(produtoExistente);
            return ResponseEntity.ok(produtoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adiciona uma verificação antes de deletar o produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estatisticas/categorias")
    public ResponseEntity<List<Object[]>> contarProdutosPorCategoria() {
        List<Object[]> estatisticas = produtoRepository.contarProdutosPorCategoria();
        return ResponseEntity.ok(estatisticas);
    }

    @GetMapping("/estatisticas/media-calorias")
    public ResponseEntity<Double> calcularMediaCalorias() {
        Double mediaCalorias = produtoRepository.calcularMediaCalorias();
        return ResponseEntity.ok(mediaCalorias);
    }
}
