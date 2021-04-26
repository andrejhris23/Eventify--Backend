package app.eventify.model.exceptions;

public class InvalidLikedPostException extends RuntimeException{

    public InvalidLikedPostException(Long id){
        super("Invalid post liked  - " + id);
    }
}


