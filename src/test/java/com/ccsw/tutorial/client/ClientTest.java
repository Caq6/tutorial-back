package com.ccsw.tutorial.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.exception.ClientNameAlreadyExistsException;

/**
 * 
 * @author caliagaq
 *
 */
@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @InjectMocks
    ClientServiceImpl clientService;
    
    @Mock
    ClientRepository clientRepository;
    
    public static final Long EXISTING_ID = 1L; 
    public static final String EXISTING_NAME = "Blanca Paloma Ramos";
    public static final String NEW_NAME = "Rosa Linn";
    public static final Long NOT_EXISTING_CLIENT_ID = 0L;

    
    @Test
    public void findAllShouldReturnAllClients() {
        List<Client> list = new ArrayList<>();
        list.add(mock(Client.class));

        when(clientRepository.findAll()).thenReturn(list);

        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(1, clients.size());
    }
    
    @Test
    public void saveExistingIdAndNoExistingNameShouldUpdate() throws ClientNameAlreadyExistsException {
        ClientDto clientToUpdate = new ClientDto();
        clientToUpdate.setName(NEW_NAME);
        
        Client client = mock(Client.class);
        when(clientRepository.findById(EXISTING_ID)).thenReturn(Optional.of(client));
        
        clientService.save(EXISTING_ID, clientToUpdate);
        verify(clientRepository).save(client);   
    }
    
    @Test
    public void saveClientWithExistingNameShouldError() {
        ClientDto clientToUpdate = new ClientDto();
        clientToUpdate.setName(EXISTING_NAME);
        
        List<Client> clientsWithThatName = new ArrayList<>();
        clientsWithThatName.add(mock(Client.class));
        when(clientRepository.findByName(EXISTING_NAME)).thenReturn(clientsWithThatName);
        
        assertThrows(ClientNameAlreadyExistsException.class,
            ()->{
                clientService.save(EXISTING_ID, clientToUpdate);
                });
    }
    
    @Test
    public void saveWithoutIdAndNoExistingNameShouldInsert() throws ClientNameAlreadyExistsException {
        ClientDto clientToInsert = new ClientDto();
        clientToInsert.setName(NEW_NAME);
        
        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);
        clientService.save(null, clientToInsert);
        
        verify(clientRepository).save(client.capture());
        assertEquals(NEW_NAME, client.getValue().getName());
    }
    
    @Test
    public void saveNotExistingIdShouldThrowNullPointerException() {
        ClientDto clientToInsert = new ClientDto();
        clientToInsert.setName(NEW_NAME);
        
        when(clientRepository.findById(NOT_EXISTING_CLIENT_ID)).thenReturn(null);

        assertThrows(NullPointerException.class,
            ()->{
                clientService.save(NOT_EXISTING_CLIENT_ID, clientToInsert);
                });
    }
    
    @Test
    public void deleteExistingIdShouldDelete() {
        clientService.delete(EXISTING_ID);
        
        verify(clientRepository).deleteById(EXISTING_ID);
    }
    
}
