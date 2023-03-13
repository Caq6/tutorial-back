package com.ccsw.tutorial.loan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.author.model.Author;
import com.ccsw.tutorial.author.model.AuthorSearchDto;
import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.exception.GameNotAvailableException;
import com.ccsw.tutorial.exception.LoanPeriodTooLongException;
import com.ccsw.tutorial.exception.TooManyActiveLoansException;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

/**
 * 
 * @author caliagaq
 *
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private GameService gameService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> findAll() {
        return (List<Loan>) loanRepository.findAll();
    }
    
    /**
     * {@inheritDoc}
     */
     @Override
     public Page<Loan> findPage(LoanSearchDto loanSearchDto, Long idGame, Long idClient, Date date) {
         if(idGame == null && idClient == null && date == null) {
             return this.loanRepository.findAll(loanSearchDto.getPageable());
         }
         
         return this.loanRepository.find(loanSearchDto.getPageable(), idGame, idClient, date);
     }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(LoanDto loanDto) {
       
        Long clientId = loanDto.getClient().getId();
        Long gameId = loanDto.getGame().getId();

        //Client can't have more than 2 active loans
        if(this.loanRepository.findByClientAndActiveLoans(clientId, loanDto.getBegin(), loanDto.getEnd()).size() >= 2) {
            throw new TooManyActiveLoansException("Client already has 2 loans at the time");
        }
        
        //Game needs to be available
        if(! this.loanRepository.findByGameAndActiveLoans(gameId, loanDto.getBegin(), loanDto.getEnd()).isEmpty()) {
            throw new GameNotAvailableException("Game not available on those dates");
        }
        
        //If everything's okay, then we save it
        Loan loan = new Loan();

        BeanUtils.copyProperties(loanDto, loan, "id", "game", "client");    //Se ignoran porque solo contienen los ids y queremos las entidades
        
        loan.setGame(gameService.get(gameId));
        loan.setClient(clientService.get(clientId));
        
        System.out.print(loan.toString());
        
        loanRepository.save(loan);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        loanRepository.deleteById(id);
    }
    
    

}
