package ifmt.cba.projetoGestao.DAO;

import java.util.List;

import ifmt.cba.projetoGestao.model.Departamento;
import ifmt.cba.projetoGestao.model.Usuario;

public class TestaDao {

	public static void main(String[] args) {
		Dao dao = new Dao();
		List<Usuario> listaU = dao.lista("Usuario");
		
		for (Usuario u : listaU) {
			Usuario usuarioNovo = u;
			u.setNome("guilherme");
			dao.edita(u);
		}
	}

}
