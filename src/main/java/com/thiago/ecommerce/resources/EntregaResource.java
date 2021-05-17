package com.thiago.ecommerce.resources;

import com.thiago.ecommerce.entities.Entrega;
import com.thiago.ecommerce.services.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/new/{idEndereco}/{idTransportadora}")
    public ResponseEntity<Entrega> insert(@PathVariable Long idEndereco, @PathVariable Long idTransportadora){
        return Optional.ofNullable( service.insert(idEndereco, idTransportadora) )
                .map(entrega -> ResponseEntity.ok().body(entrega))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
