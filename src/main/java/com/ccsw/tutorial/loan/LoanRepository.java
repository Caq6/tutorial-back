package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorial.author.model.Author;
import com.ccsw.tutorial.loan.model.Loan;

/**
 * 
 * @author caliagaq
 *
 */
public interface LoanRepository extends CrudRepository<Loan, Long> {

    /**
     * Método para recuperar un listado paginado de {@link com.ccsw.tutorial.loan.model.Loan}
     * @param page
     * @return
     */
    Page<Loan> findAll(Pageable pageable);
    
    /**
     * Método para recuperar un listado páginado de {@link com.ccsw.tutorial.loan.model.Loan} 
     * con filtros aplicados
     * @param pageable
     * @param gameId
     * @param clientId
     * @param date
     * @return
     */
    @Query("select l from Loan l where (:idGame is null or l.game.id = :idGame) and (:idClient is null or l.client.id = :idClient) and (:date is null or (l.begin <= :date and l.end >= :date))")
    Page<Loan> find(Pageable pageable, @Param("idGame") Long gameId, @Param("idClient") Long clientId,
            @Param("date") Date date);
    
    
    /**
     * Método para recuperar listado de {@link com.ccsw.tutorial.loan.model.Loan}
     * de un cliente entre unas determinadas fechas
     * @param client
     * @param begin
     * @param end
     * @return
     */
    @Query("select l from Loan l where l.client.id = :client and (l.begin <= :end and l.end >= :begin)")
    public List<Loan> findByClientAndActiveLoans(@Param("client") Long client, @Param("begin") Date begin, @Param("end") Date end);

    /**
     * Método para recuperar listado de {@link com.ccsw.tutorial.loan.model.Loan}
     * de un juego entre unas determinadas fechas
     * @param game
     * @param begin
     * @param end
     * @return
     */
    @Query("select l from Loan l where l.game.id = :game and (l.begin <= :end and l.end >= :begin)")
    public List<Loan> findByGameAndActiveLoans(@Param("game") Long game, @Param("begin") Date begin, @Param("end") Date end);

    
    
}
