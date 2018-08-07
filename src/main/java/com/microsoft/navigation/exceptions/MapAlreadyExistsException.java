package com.microsoft.navigation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MapAlreadyExistsException extends NavigationSystemException {
	
	private static final long serialVersionUID = 8414332370805619069L;

	public MapAlreadyExistsException(String error){
        super(error);
    }

    public MapAlreadyExistsException(String error, Throwable t){
        super(error,t);
    }

}
