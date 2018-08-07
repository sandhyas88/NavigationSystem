package com.microsoft.navigation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NavigationSystemException  extends RuntimeException {
	
	private static final long serialVersionUID = -4306495287040935805L;

    public NavigationSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public NavigationSystemException(String message) {
        super(message);
    }

    public NavigationSystemException(Throwable cause) {
        super(cause);
    }

    public NavigationSystemException() {
        super();
    }


}
