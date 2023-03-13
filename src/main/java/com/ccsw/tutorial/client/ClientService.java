package com.ccsw.tutorial.client;

import java.util.List;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.exception.ClientNameAlreadyExistsException;


/**
 * 
 * @author caliagaq
 *
 */
public interface ClientService {
    
    /**
     * Método para recuperar todos los {@link com.ccsw.tutorial.client.model.Client}
     * @return
     */
    List<Client> findAll();

    /**
     * Método para crear o actualizar un {@link com.ccsw.tutorial.client.model.Client}
     * @param id
     * @param clientToInsert
     */
    void save(Long id, ClientDto clientToInsert) throws ClientNameAlreadyExistsException;

    /**
     * Método para borrar un {@link com.ccsw.tutorial.client.model.Client}
     * @param id
     */
    void delete(Long id);

    /**
     * Método para encontrar a un cliente por su id
     * @param id
     * @return
     */
    Client get(Long id);
    

}
