package com.thiago.ecommerce.resources;

import com.thiago.ecommerce.entities.*;
import com.thiago.ecommerce.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/carrinhos")
public class CarrinhoResource {
    @Autowired
    private CarrinhoService service;

    @GetMapping
    public ResponseEntity<List<Carrinho>> findAll(){
        return Optional.ofNullable(service.findAll())
                .map(carrinhos -> ResponseEntity.ok().body(carrinhos))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/new/{id}")
    public ResponseEntity<Carrinho> newCarrinho(@PathVariable Long id){
        return Optional.ofNullable( service.novoCarrinho(id) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Carrinho> findById(@PathVariable Long id){
        return Optional
                .ofNullable( service.findById(id) )
                .map( carrinho -> ResponseEntity.ok().body(carrinho) )
                .orElseGet( () -> ResponseEntity.status(404).build() );
    }

    @DeleteMapping("/deleteItem/{id}/{index}")
    public ResponseEntity<Carrinho> deleteItem(@PathVariable Long id, @PathVariable int index){
        return Optional
                .ofNullable( service.deleteItem(id, index) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PatchMapping("/newItem/{id}/{idProduto}/{quantidade}")
    public ResponseEntity<Carrinho> novoItem(@PathVariable Long id, @PathVariable Long idProduto, @PathVariable int quantidade){
        return Optional.ofNullable( service.novoItem(id, idProduto, quantidade) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("checkout/{id}")
    public ResponseEntity<Carrinho> checkout(@PathVariable Long id){
        return Optional.ofNullable( service.checkout(id) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("entrega/{id}/{idEndereco}/{idTransportadora}")
    public ResponseEntity<Carrinho> defineShippment(@PathVariable Long id, @PathVariable Long idEndereco, @PathVariable Long idTransportadora){
        return Optional.ofNullable( service.defineShippment(id, idEndereco, idTransportadora) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("pagar/{id}/{tipoPagamento}/{parcelas}/{idCupom}/has")
    public ResponseEntity<Carrinho> pay(@PathVariable Long id,
                                        @PathVariable Integer tipoPagamento,
                                        @PathVariable Long idCupom,
                                        @RequestParam Boolean cupom,
                                        @PathVariable Integer parcelas,
                                        @RequestBody Cartao obj) throws IllegalAccessException {
        if(cupom)
            return Optional.ofNullable(service.payWithCupom(id, tipoPagamento, idCupom, parcelas, obj))
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());

        return Optional.ofNullable(service.pay(id, tipoPagamento, parcelas, obj))
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<Carrinho> cancel(@PathVariable Long id) throws IllegalAccessException {

        return Optional.ofNullable( service.cancel(id) )
                .map(carrinho -> ResponseEntity.ok().body(carrinho))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




}
