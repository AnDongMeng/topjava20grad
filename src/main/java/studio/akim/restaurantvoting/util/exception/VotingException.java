package studio.akim.restaurantvoting.util.exception;

public class VotingException extends RuntimeException {
    public VotingException(String msgCode) {
        super(msgCode);
    }
}
