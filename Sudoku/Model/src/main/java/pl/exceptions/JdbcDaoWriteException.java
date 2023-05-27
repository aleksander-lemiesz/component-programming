package pl.exceptions;

public class JdbcDaoWriteException extends DaoException {
    public JdbcDaoWriteException(String message) {
        super(message);
    }
}
