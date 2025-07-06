package ifmt.cba.projetoGestao.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PaginasTag extends TagSupport {
	private String classe;
	private int paginaAtual;
	private int totalPaginas;
	
	public int getPaginaAtual() {
		return paginaAtual;
	}
	public void setPaginaAtual(int paginaAtual) {
		this.paginaAtual = paginaAtual;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	
	
	
	 public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	@Override
	    public int doStartTag() throws JspException {
	        try {
	            JspWriter out = pageContext.getOut();

	            for (int i = 1; i <= totalPaginas; i++) {
	                if (i == paginaAtual) {
	                    out.write("<span style='font-weight:bold; color:#3c0f73; font-size:1.2em; margin: 0 5px;'>" + i + "</span>");
	                } else {
	                    out.write("<a href='?page" + classe + "=" + i + "' style='margin: 0 5px; background-color: #5b10c4; color:white; padding: 2px; border-radius: 2px;'>" + i + "</a>");
	                }
	            }

	        } catch (IOException e) {
	            throw new JspException(e);
	        }
	        return SKIP_BODY;
	    }
	
	
}
