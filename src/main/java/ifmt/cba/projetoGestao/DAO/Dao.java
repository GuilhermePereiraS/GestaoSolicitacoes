package ifmt.cba.projetoGestao.DAO;



import java.sql.SQLException;
import java.util.List;

import ifmt.cba.projetoGestao.model.Usuario;
import ifmt.cba.projetoGestao.util.HibernateUtil;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

public class Dao {
	
	public void persiste(Object objeto) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			session.save(objeto);
			
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Erro ao criar sessão: " + e.getMessage());
			try {
				transaction.rollback();
			} catch (HibernateException he) {
				System.out.println("rollback da transação deu errado: " + he.getMessage());
			}
		} finally {
			if (session !=null) {
				if (session.isOpen()) {
					try {
						session.close();
					} catch (HibernateException e) {
						System.out.println("erro ao fechar a sessão: " + e.getMessage());
					}
				}
			}
		}
	}
	
	public <t> List<t> lista(String nomeClasseObjeto) {
		Session session = null;
		List<t> lista = null;
		
		if (!nomeClasseObjeto.equals("Usuario") && !nomeClasseObjeto.equals("Departamento") &&  !nomeClasseObjeto.equals("Solicitacao")) {
			System.err.println("Selecione uma opção valida: Usuario, Departamento ou Solicitacao");
			return null;
		}
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			lista = (List<t>) session.createQuery("from " + nomeClasseObjeto).list();
			
			if (lista.isEmpty()) {
				System.out.println("Lista Vazia");
			} 
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					try {
						session.close();
					} catch (HibernateException e) {
						System.out.println("erro ao fechar a sessão: " + e.getMessage());
					}
				}
			}
		} 
		return lista;
	}
	
	public void edita(Object objeto) {
		Transaction transaction = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			session.update(objeto);
			
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Erro ao criar sessão: " + e.getMessage());
			try {
				transaction.rollback();
			} catch (HibernateException he) {
				System.out.println("rollback da transação deu errado: " + he.getMessage());
			}
		} finally {
			if (session !=null) {
				if (session.isOpen()) {
					try {
						session.close();
					} catch (HibernateException e) {
						System.out.println("erro ao fechar a sessão: " + e.getMessage());
					}
				}
			}
		}
	}
	
	public Object buscaPorId(String nomeClasseObjeto, int id) {
		Transaction transaction = null;
		Session session = null;
		Object objeto = null;
		
		if (!nomeClasseObjeto.equals("Usuario") && !nomeClasseObjeto.equals("Departamento") &&  !nomeClasseObjeto.equals("Solicitacao")) {
			System.err.println("Selecione uma opção valida: Usuario, Departamento ou Solicitacao");
			return null;
		}
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			objeto = session.createQuery("from " + nomeClasseObjeto + " where id = "+ id).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					try {
						session.close();
					} catch (HibernateException e) {
						System.out.println("erro ao fechar a sessão: " + e.getMessage());
					}
				}
			}
		} 
		
		return objeto;
	}
	
	public int contarTotal(String NomeClasseObjeto){
		Session session = null;
		Integer total = 0;
		try {
			if (!NomeClasseObjeto.equals("Usuario") && !NomeClasseObjeto.equals("Departamento") &&  !NomeClasseObjeto.equals("Solicitacao")) {
				System.err.println("Selecione uma opção valida: Usuario, Departamento ou Solicitacao");
				return 0;
			}
	    	session = HibernateUtil.getSessionFactory().openSession();
	        Query query = session.createQuery("select count(*) from " + NomeClasseObjeto);
	        total = ((Number) query.uniqueResult()).intValue();
	        return total;
	    } catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					try {
						session.close();
					} catch (HibernateException e) {
						System.out.println("erro ao fechar a sessão: " + e.getMessage());
					}
				}
			}
		} 
		return total;
	}
	
	public void deleta(Object objeto) {
		Transaction transaction = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(objeto);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Erro ao criar sessão: " + e.getMessage());
			try {
				transaction.rollback();
			} catch (HibernateException he) {
				System.out.println("rollback da transação deu errado: " + he.getMessage());
			}
		} finally {
			if (session !=null) {
				if (session.isOpen()) {
					try {
						session.close();
					} catch (HibernateException e) {
						System.out.println("erro ao fechar a sessão: " + e.getMessage());
					}
				}
			}
		}
	}
}

