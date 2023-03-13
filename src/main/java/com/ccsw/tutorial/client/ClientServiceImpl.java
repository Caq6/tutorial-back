package com.ccsw.tutorial.client;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.exception.ClientNameAlreadyExistsException;
/**
 * 
 * @author caliagaq
 *
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {
        return (List<Client>) this.clientRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * @throws ClientNameAlreadyExistsException 
     */
    @Override
    public void save(Long id, ClientDto clientToInsert) throws ClientNameAlreadyExistsException {
        Client client = null;
        String newName = clientToInsert.getName();
        
        List<Client> clientWithNewName = this.clientRepository.findByName(newName);
        
        if(!clientWithNewName.isEmpty()) {
            throw new ClientNameAlreadyExistsException("Customer name already exists");
        }
        
        if(id != null) {
            client = this.clientRepository.findById(id).orElse(null);
        }
        else {
            client = new Client();
        }
        client.setName(newName);
        this.clientRepository.save(client);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.clientRepository.deleteById(id);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client get(Long id) {
        return this.clientRepository.findById(id).orElse(null);
    }


}
