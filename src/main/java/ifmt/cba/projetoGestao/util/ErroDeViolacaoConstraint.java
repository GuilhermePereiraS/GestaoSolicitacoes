package ifmt.cba.projetoGestao.util;

public class ErroDeViolacaoConstraint extends RuntimeException {
	
	public 	ErroDeViolacaoConstraint(String mensagem, Throwable e) {
		super(mensagem, e);
	}

}
