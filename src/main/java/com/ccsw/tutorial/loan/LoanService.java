package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.author.model.Author;
import com.ccsw.tutorial.author.model.AuthorSearchDto;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

/**
 * 
 * @author caliagaq
 *
 */
public interface LoanService {

    /**
     * Método para recuperar todos los préstamos
     * @return
     */
    public List<Loan> findAll();
    
    /**
     * Método para recuperar un listado paginado de {@link com.ccsw.tutorial.loan.model.Loan}
     * @param date 
     * @param idClient 
     * @param idGame 
     * @param loanSearchDto
     * @return
     */
    public Page<Loan> findPage(LoanSearchDto loanSearchDto, Long idGame, Long idClient, Date date);

    /**
     * Método para guardar un nuevo préstamo
     * @param loanDto
     */
    public void save(LoanDto loanDto);
    
    /**
     * Método para borrar un préstamo con su id
     * @param id
     */
    public void delete(Long id);
    
    

}
