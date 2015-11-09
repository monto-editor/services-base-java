package monto.service.message;

public class ParseException extends Exception {

    public ParseException(Exception e) {
        super(e);
    }

    public ParseException(String reason) {
        super(reason);
    }

    public ParseException(String reason, Exception e) {
		super(reason,e);
	}

	private static final long serialVersionUID = -8652632901411933961L;

}
