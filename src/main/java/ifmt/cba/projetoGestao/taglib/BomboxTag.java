package ifmt.cba.projetoGestao.taglib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class BomboxTag extends TagSupport {
	private List<Object> lista;
	private String atributoName;
		
	public String getAtributoName() {
		return atributoName;
	}
	public void setAtributoName(String atributoName) {
		this.atributoName = atributoName;
	}
	public List getLista() {
		return lista;
	}
	public void setLista(List lista) {
		this.lista = lista;
	}
	
	@Override
	public int doStartTag() {	
		try {
			JspWriter out = pageContext.getOut();
			List<String> listaDeNomes = pegaNomes(lista);
			List<Integer> listaDeIds = pegaIds(lista);
			out.write("<select required name='" + atributoName + "' >");
			out.write("<option disabled selected >----</option>");
			int i = 0;
			for (String nome : listaDeNomes) {
				out.write("<option value='" + listaDeIds.get(i) + "'>"+ nome +"</option>");
				i++;
			}
			out.write("</select>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}
	
	private List<String> pegaNomes(List<Object> listaDeObjetos) {
		List<String> listaNomes = new ArrayList<>();
		if (listaDeObjetos != null && !listaDeObjetos.isEmpty()) {
			Class classeDoObjeto = listaDeObjetos.get(0).getClass();
			Method metodoGetNome = null;
			
			try {
				metodoGetNome = classeDoObjeto.getMethod("getNome");
				
				for (Object objeto : listaDeObjetos) {
					String nome = (String) metodoGetNome.invoke(objeto);
					listaNomes.add(nome);
				}
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException | SecurityException e) {
				System.out.println("Não deu pra achar o metodo!! " + e.getMessage());
			}
		} 
		return listaNomes;
	}
	
	private List<Integer> pegaIds(List<Object> listaDeObjetos) {
		List<Integer> listaIds = new ArrayList<>();
		if (listaDeObjetos != null && !listaDeObjetos.isEmpty()) {
			Class classeDoObjeto = listaDeObjetos.get(0).getClass();
			Method metodoGetId = null;
			try {
				metodoGetId = classeDoObjeto.getMethod("getId");
				
				for (Object objeto : listaDeObjetos) {
					Integer id = (Integer) metodoGetId.invoke(objeto);
					listaIds.add(id);
				}
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException | SecurityException e) {
				System.out.println("Não deu pra achar o metodo!! " + e.getMessage());
			}
		} else {
			listaIds.add(0);
		}
		
		return listaIds;
	}
	
	
	
	
}
