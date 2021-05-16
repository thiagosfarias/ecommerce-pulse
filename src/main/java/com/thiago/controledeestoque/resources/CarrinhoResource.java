package com.thiago.controledeestoque.resources;

import com.thiago.controledeestoque.services.CarrinhoService;
import com.thiago.controledeestoque.entities.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/carrinhos")
public class CarrinhoResource {
    @Autowired
    private CarrinhoService service;

    @GetMapping
    public ResponseEntity<List<Carrinho>> findAll(){
        List<Carrinho> list =service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Carrinho> findById(@PathVariable Long id){
        return Optional
                .ofNullable( service.findById(id) )
                .map( carrinho -> ResponseEntity.ok().body(carrinho) )
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/new")
    public ResponseEntity<Carrinho> insert(@RequestBody Carrinho obj){
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
