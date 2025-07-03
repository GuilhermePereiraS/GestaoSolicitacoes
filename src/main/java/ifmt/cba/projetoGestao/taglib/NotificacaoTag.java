package ifmt.cba.projetoGestao.taglib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

public class NotificacaoTag extends BodyTagSupport {
	private String tipoAlerta;
		
	public String getTipoAlerta() {
		return tipoAlerta;
	}

	public void setTipoAlerta(String tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}

	@Override
	public int doAfterBody() {
		String tipoAlerta = this.getTipoAlerta();
		BodyContent body = getBodyContent();
		String corpo = body.getString();
		String sucessoConf = "background-color: #86eb34;'>" + corpo + "<br><button onclick=\"this.closest('.aviso').style.display='none'\">Ok</button>";  
		String erroConf = "background-color: #eb3455;'>" + corpo + "<br><button onclick=\"window.location.href='/'\">Voltar pagina inicial</button>";  
		String avisoConf = "background-color: #c6d61a;'>" + corpo + "<br><button onclick=\"this.closest('.aviso').style.display='none'\">Ok</button>";
		
		switch (tipoAlerta) {
		case "Sucesso":
			tipoAlerta = sucessoConf;
			break;
		case "Aviso":
			tipoAlerta = avisoConf;
			break;
		case "Erro":
			tipoAlerta = erroConf;
			break;
		}
	
		try {
			corpo = "<div class='aviso' transition: 2s; style='min-width: 15cm; top: 10px; left: 50%; transform: translateX(-50%); text-align:center; color:white;"
					+ 
					tipoAlerta
					+ "</div>";
			
			body.getEnclosingWriter().write(corpo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_BODY;
			
	}
	
	@Override
	public int doStartTag() {	
		return EVAL_BODY_BUFFERED;
	}
	
	
	
	
	
	
	
}
