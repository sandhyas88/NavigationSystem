package com.microsoft.navigation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MapNotFoundException extends NavigationSystemException {
	
	private static final long serialVersionUID = 1946645867486798852L;

	public MapNotFoundException(String error){
        super(error);
    }

    public MapNotFoundException(String error, Throwable t){
        super(error,t);
    }

}
