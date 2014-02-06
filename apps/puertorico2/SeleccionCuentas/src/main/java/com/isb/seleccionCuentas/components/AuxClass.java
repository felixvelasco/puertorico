package com.isb.seleccionCuentas.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AuxClass 
{


	String vuelta;
	String indice;
	
	public String getVuelta() {
		return vuelta;
	}

	public void setVuelta(String vuelta) {
		this.vuelta = vuelta;
	}
	public void incVuelta() {
		this.vuelta=""+Integer.parseInt(this.vuelta)+1;
	}

	public String getIndice() {
		return indice;
	}

	public void setIndice(String indice) {
		this.indice = indice;
	}

}
