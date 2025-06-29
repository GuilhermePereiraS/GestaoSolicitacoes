package ifmt.cba.projetoGestao.util;

import java.util.List;

import ifmt.cba.projetoGestao.DAO.Dao;
import ifmt.cba.projetoGestao.model.Usuario;

public class Testa {

	public static void main(String[] args) {
		Dao dao = new Dao();
		List<Usuario> lista = dao.lista("Usuario");
		
		for  (Usuario u : lista) {
			System.out.println(u.getLogin());
		}
	}

}
