package lv.webkursi.klucis;

/**
 * Runtime errors during KLUCIS image generation
 * @author kap
 */
@SuppressWarnings("serial")
public class KlucisException extends RuntimeException {
	public KlucisException(String message) {
		super(message);
	}

}
