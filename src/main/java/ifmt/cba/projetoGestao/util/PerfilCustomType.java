package ifmt.cba.projetoGestao.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ifmt.cba.projetoGestao.model.Usuario.Perfil;

import net.sf.hibernate.HibernateException;

public class PerfilCustomType implements net.sf.hibernate.UserType {

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		String value = rs.getString(names[0]);
		return value == null ? null : Perfil.valueOf(value);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, java.sql.Types.VARCHAR);
		} else {
			st.setString(index, ((Perfil) value).name());
		}
		
	}

	@Override
	public Class returnedClass() {
		// TODO Auto-generated method stub
		return Perfil.class;
	}

	@Override
	public int[] sqlTypes() {
		// TODO Auto-generated method stub
		return new int[] {java.sql.Types.VARCHAR};
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) return true;
        if (x == null || y == null) return false;
        return x.equals(y);
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	


	
}
