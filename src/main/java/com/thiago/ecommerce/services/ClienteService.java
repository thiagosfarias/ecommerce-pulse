package com.thiago.ecommerce.services;

import com.thiago.ecommerce.entities.Cliente;
import com.thiago.ecommerce.entities.Endereco;
import com.thiago.ecommerce.repositories.ClienteRepository;
import com.thiago.ecommerce.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente findById(Long id){
        return Optional.ofNullable( repository.findById(id) ).get().orElseThrow();
    }

    public Cliente insert(Cliente obj){
        Optional<Cliente> cliente = Optional.ofNullable( findAll() )
                                            .get()
                                            .stream()
                                            .filter(client -> client.getnDocumento().equals(obj.getnDocumento()))
                                            .findFirst();

        if(!cliente.isPresent() && obj.getnDocumento().length() == 11)
            return repository.save(obj);

        return null;
    }

    public Cliente update(Cliente obj, Long id) {
        Optional<Cliente> oldCliente = Optional.ofNullable(findById(id));

        if(oldCliente.isPresent()){
            oldCliente.get().setNome(obj.getNome());
            oldCliente.get().setnDocumento(obj.getnDocumento());
            oldCliente.get().setDataNascimento(obj.getDataNascimento());
            oldCliente.get().setTelefone(obj.getTelefone());
            oldCliente.get().setHistoricos(obj.getHistoricos());
            oldCliente.get().setCartoes(obj.getCartoes());

            return repository.save(oldCliente.get());
        }
        return null;
    }

    public Cliente newEndereco(Long id, Endereco endereco){
        Optional<Cliente> obj = Optional.ofNullable( repository.findById(id) ).get();

        if(obj.isPresent()){
            obj.get().setEnderecos(endereco);
            endereco.setTitular(obj.get());
            repository.save(obj.get());
            enderecoRepository.save(endereco);
            return obj.get();
        }

        return null;
    }


}
