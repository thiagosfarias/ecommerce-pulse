package com.thiago.ecommerce.resources;

import com.thiago.ecommerce.entities.Cupom;
import com.thiago.ecommerce.services.CupomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cupoms")
public class CupomResource {
    @Autowired
    private CupomService service;

    @GetMapping
    public ResponseEntity<List<Cupom>> findAll(){
        return Optional.ofNullable(service.findAll())
                .map(cupoms -> ResponseEntity.ok().body(cupoms))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cupom> findById(@PathVariable Long id){
        return Optional.ofNullable(service.findById(id))
                .map(cupom -> ResponseEntity.ok().body(cupom))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<Cupom> novoCupom(@RequestBody Cupom obj){
        return Optional.ofNullable(service.novoCupom(obj))
                .map(cupom -> ResponseEntity.ok().body(cupom))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
