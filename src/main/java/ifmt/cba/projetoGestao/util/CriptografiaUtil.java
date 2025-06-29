package ifmt.cba.projetoGestao.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;



public class CriptografiaUtil {
	
	public static String criptografa(String texto) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(texto.getBytes());
			return Base64.getEncoder().encodeToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Erro ao criptografar: " + e.getMessage());
		}
	}
}
