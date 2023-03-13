package com.ccsw.tutorial.loan.model;

import org.springframework.data.domain.Pageable;

/**
 * 
 * @author caliagaq
 *
 */
public class LoanSearchDto {

    Pageable pageable;

    /**
     * @return the pageable
     */
    public Pageable getPageable() {
        return pageable;
    }

    /**
     * @param pageable new value of {@link #getPageable}.
     */
    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
