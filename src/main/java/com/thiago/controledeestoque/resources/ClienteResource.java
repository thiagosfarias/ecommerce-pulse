package com.thiago.controledeestoque.resources;

import com.thiago.controledeestoque.services.ClienteService;
import com.thiago.controledeestoque.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>>findAll(){
        List<Cliente> list =service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/new")
    public ResponseEntity<Cliente> insert(@RequestBody Cliente obj){
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
