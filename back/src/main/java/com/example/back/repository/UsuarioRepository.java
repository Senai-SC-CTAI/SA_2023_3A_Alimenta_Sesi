package com.example.back.repository;

import com.example.back.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCpfAndSenha(String cpf, String senha);
}
