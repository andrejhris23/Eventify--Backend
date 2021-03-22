package app.eventify.model.exceptions;

public class InvalidPostIdException extends RuntimeException{

    public InvalidPostIdException(Long id){
        super("Invalid post id - " + id);
    }
}
