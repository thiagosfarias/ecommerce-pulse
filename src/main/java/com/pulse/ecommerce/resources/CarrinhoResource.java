package com.pulse.ecommerce.resources;

import com.pulse.ecommerce.services.CarrinhoService;
import com.pulse.ecommerce.entities.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Carrinho obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/new")
    public ResponseEntity<Carrinho> insert(@RequestBody Carrinho obj){
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
