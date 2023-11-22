package com.example.back.repository;

import com.example.back.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoria(String categoria);

    List<Produto> findByCaloriasLessThanEqual(double calorias);

    @Query(value = "SELECT categoria, COUNT(*) FROM Produto GROUP BY categoria", nativeQuery = true)
    List<Object[]> contarProdutosPorCategoria();

    @Query(value = "SELECT AVG(calorias) FROM Produto", nativeQuery = true)
    Double calcularMediaCalorias();
}