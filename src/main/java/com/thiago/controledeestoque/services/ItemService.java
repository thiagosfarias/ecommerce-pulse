package com.thiago.controledeestoque.services;

import com.thiago.controledeestoque.entities.Item;
import com.thiago.controledeestoque.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public List<Item> findAll(){
        return repository.findAll();
    }

    public Item findById(Long id){
        Optional<Item> obj = repository.findById(id);
        return obj.get();
    }

    public Item insert(Item obj){
        return repository.save(obj);
    }
}
