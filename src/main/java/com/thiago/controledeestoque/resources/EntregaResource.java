package com.thiago.controledeestoque.resources;

import com.thiago.controledeestoque.entities.Entrega;
import com.thiago.controledeestoque.services.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/entregas")
public class EntregaResource {

    @Autowired
    private EntregaService service;

    @GetMapping
    public ResponseEntity<List<Entrega>> findAll(){
        List<Entrega> list =service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Entrega> findById(@PathVariable Long id){
        Entrega obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/new")
    public ResponseEntity<Entrega> insert(@RequestBody Entrega obj){
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
