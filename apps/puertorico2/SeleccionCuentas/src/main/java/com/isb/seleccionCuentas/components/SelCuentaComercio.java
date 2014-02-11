package com.isb.seleccionCuentas.components;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SelCuentaComercio {
	
	@Inject
	private JwsCuentasMerch jwsCuentaMerch;
	private CtaComercio seleccion;
	private int pagina;

	public CtaComercio getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(CtaComercio seleccion) {
		this.seleccion = seleccion;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	public String getTts() {
		StringBuilder sb = new StringBuilder();
		for(int i= 0; i < 5; i++ ) {
			if(pagina * 5 + i >= jwsCuentaMerch.getListaCtaComercio().size()) {
				break;
			}
			CtaComercio comercio = jwsCuentaMerch.getListaCtaComercio().get(pagina * 5 + i);
			sb.append("Para la cuenta que termina en  ");
			sb.append( lastFourDigits(comercio.getId()) );
			sb.append(" presione ");
			sb.append(String.valueOf(i+1));
			sb.append(". ");
		}
		if(hasMore5Options()) {
			sb.append("Para escuchar los siguientes, presione 6.");
		}
		return sb.toString();
	}
	
	private String lastFourDigits(String account) {
		if(account.length() > 4) {
			return account.substring(account.length() - 4);
		} else {
			return account;
		}
	}
	
	public boolean isValidOption(String option) {
		int intOption = 0;
		intOption = Integer.parseInt(option);
		if(intOption > 5 ) {
			return false;
		}
		if((this.pagina * 5 + intOption) <= jwsCuentaMerch.getListaCtaComercio().size()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasMore5Options() {
		return jwsCuentaMerch.getListaCtaComercio().size() > 5;
	}
	
	public void incPagina() {
		this.pagina++;
	}
	
	public boolean hasMorePages() {
		return (this.pagina * 5 < jwsCuentaMerch.getListaCtaComercio().size());
	}
}
