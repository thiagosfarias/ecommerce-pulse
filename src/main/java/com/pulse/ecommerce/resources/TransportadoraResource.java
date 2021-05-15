package com.pulse.ecommerce.resources;

import com.pulse.ecommerce.entities.Transportadora;
import com.pulse.ecommerce.services.TransportadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transportadoras")
public class TransportadoraResource {

    @Autowired
    private TransportadoraService service;

    @GetMapping
    public ResponseEntity<List<Transportadora>> findAll(){
        List<Transportadora> list =service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Transportadora> findById(@PathVariable Long id){
        Transportadora obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/new")
    public ResponseEntity<Transportadora> insert(@RequestBody Transportadora obj){
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
