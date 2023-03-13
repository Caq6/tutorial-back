package com.ccsw.tutorial.author.model;

import org.springframework.data.domain.Pageable;

/**
 * 
 * @author caliagaq
 *
 */
public class AuthorSearchDto {

	Pageable pageable;

	/**
	 * @return the pageable
	 */
	public Pageable getPageable() {
		return pageable;
	}

	/**
	 * @param pageable new value of {@link #getpageable}.
	 */
	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}
	
	
}
