package studio.akim.restaurantvoting.util.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msgCode) {
        super("Not found " + msgCode);
    }

}
