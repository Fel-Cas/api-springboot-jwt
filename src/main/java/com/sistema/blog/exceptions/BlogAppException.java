package com.sistema.blog.exceptions;

import org.springframework.http.HttpStatus;

public class BlogAppException extends  RuntimeException{
    private  static  final  long serailVersionUID=1L;

    private HttpStatus status;
    private String message;

    public  BlogAppException (HttpStatus status, String message){
        super();
        this.message=message;
        this.status=status;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public BlogAppException setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BlogAppException setMessage(String message) {
        this.message = message;
        return this;
    }
}
