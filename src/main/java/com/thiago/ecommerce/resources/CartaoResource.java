package com.thiago.ecommerce.resources;

import com.thiago.ecommerce.entities.Cartao;
import com.thiago.ecommerce.services.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/cartoes")
public class CartaoResource {
    @Autowired
    private CartaoService service;

    @GetMapping
    public ResponseEntity<List<Cartao>> findAll(){
        return Optional.ofNullable(service.findAll())
                .map(cartoes -> ResponseEntity.ok().body(cartoes))
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cartao> findById(@PathVariable Long id){
        return Optional.ofNullable(service.findById(id))
                .map(cartao -> ResponseEntity.ok().body(cartao))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<Cartao> novoCartao(@RequestBody Cartao obj){
        return Optional.ofNullable(service.novo(obj))
                .map(cartao -> ResponseEntity.ok().body(cartao))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCartao(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Removido com sucesso");
    }

}
