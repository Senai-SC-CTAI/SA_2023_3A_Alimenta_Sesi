package com.example.back.controller;

import com.example.back.model.Usuario;
import com.example.back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<String> fazerLogin(@RequestBody Usuario usuario) {
        System.out.println("CPF recebido: " + usuario.getCpf());
        System.out.println("Senha recebida: " + usuario.getSenha());
        Usuario usuarioExistente = usuarioRepository.findByCpfAndSenha(usuario.getCpf(), usuario.getSenha());

        if (usuarioExistente != null) {
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}


