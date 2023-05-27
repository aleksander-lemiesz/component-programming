package pl.exceptions;

public class DaoFileNotFoundException extends FileDaoReadException {
    public DaoFileNotFoundException(String s) {
        super(s);
    }
}
