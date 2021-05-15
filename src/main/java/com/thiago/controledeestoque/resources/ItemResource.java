package com.thiago.controledeestoque.resources;

import com.thiago.controledeestoque.services.ItemService;
import com.thiago.controledeestoque.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/items")
public class ItemResource {
    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity<List<Item>> findAll(){
        List<Item> list =service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> findById(@PathVariable Long id){
        Item obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/new")
    public ResponseEntity<Item> insert(@RequestBody Item obj){
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
}
