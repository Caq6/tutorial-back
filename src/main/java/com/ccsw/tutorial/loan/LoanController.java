package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.config.mapper.BeanMapper;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

/**
 * 
 * @author caliagaq
 *
 */
@CrossOrigin("*")
@RequestMapping(value = "/loan")
@RestController
public class LoanController {

    @Autowired
    LoanService loanService;
    
    @Autowired
    BeanMapper beanMapper;
    
    /**
     * Método para recuperar todos los préstamos
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<LoanDto> findAll(){
        return this.beanMapper.mapList(this.loanService.findAll(), LoanDto.class);
    }
    
    /**
     * Método para recuperar listado paginado
     * @param loanSearchDto
     * @return
     */
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<LoanDto> findPage(@RequestParam(value="idGame", required=false) Long idGame, 
            @RequestParam(value="idClient", required=false) Long idClient, 
            @RequestParam(value="date", required=false) Date date, 
            @RequestBody LoanSearchDto loanSearchDto){
        
        Page<Loan> loans = this.loanService.findPage(loanSearchDto, idGame, idClient, date);
        return this.beanMapper.mapPage(loans, LoanDto.class);
    }
    
    /**
     * Método para guardar un préstamo
     * @param loanDto
     */
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public void save(@RequestBody LoanDto loanDto){
        this.loanService.save(loanDto);
    }
    
    /**
     * Método para borrar un préstamo
     * @param id
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        this.loanService.delete(id);
    }
    
}
