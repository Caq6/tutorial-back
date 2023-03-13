package com.ccsw.tutorial.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.exception.ClientNameAlreadyExistsException;
import com.devonfw.module.beanmapping.common.api.BeanMapper;

/**
 * 
 * @author caliagaq
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    ClientService clientService;
    
    @Autowired
    BeanMapper beanMapper;
    
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ClientDto> findAll() {
        return this.beanMapper.mapList(this.clientService.findAll(), ClientDto.class);
    }
    
    @RequestMapping(path = {"", "/{id}"}, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClientDto client) throws ClientNameAlreadyExistsException {
        this.clientService.save(id, client);
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        this.clientService.delete(id);
    }
}
