package ifmt.cba.projetoGestao.util;

import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.out.println("Erro na criação da sessionFactory: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
