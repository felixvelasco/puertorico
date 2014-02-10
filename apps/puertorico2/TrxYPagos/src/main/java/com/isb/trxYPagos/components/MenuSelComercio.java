package com.isb.trxYPagos.components;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class MenuSelComercio implements Serializable {

	private static final long serialVersionUID = 5601957698327244373L;
	
	@Inject
	private PagoComercio pagoComercio;
	private int paginaSel;

	public int getPaginaSel() {
		return paginaSel;
	}

	public void setPaginaSel(int paginaSel) {
		this.paginaSel = paginaSel;
	}

	public PagoComercio getPagoComercio() {
		return pagoComercio;
	}

	public void setPagoComercio(PagoComercio pagoComercio) {
		this.pagoComercio = pagoComercio;
	}

	public String getTts() {
		StringBuilder sb = new StringBuilder();
		for(int i= 0; i < 5; i++ ) {
			if(paginaSel * 5 + i >= pagoComercio.getListaComercios().size()) {
				break;
			}
			Comercio comercio = pagoComercio.getListaComercios().get(paginaSel * 5 + i);
			sb.append("Para realizar un pago a ");
			sb.append(comercio.getMerchantName());
			sb.append(" presione ");
			sb.append(String.valueOf(i+1));
			sb.append(". ");
		}
		if(hasMore5Options()) {
			sb.append("Para escuchar los siguientes, presione 6.");
		}
		return sb.toString();
	}

	public boolean hasMore5Options() {
		return pagoComercio.getListaComercios().size() > 5;
	}

	public boolean hasMorePages() {
		return (this.paginaSel * 5 < pagoComercio.getListaComercios().size());
	}
	
	public boolean isValidOption(String option) {
		int intOption = 0;
		intOption = Integer.parseInt(option);
		if(intOption > 5 ) {
			return false;
		}
		if((this.paginaSel * 5 + intOption) <= pagoComercio.getListaComercios().size()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void incPaginaSel() {
		this.paginaSel++;
	}
	
	public Comercio getSelectedComercio(String option) {
		return pagoComercio.getListaComercios().get(this.paginaSel * 5 + Integer.parseInt(option) - 1);
	}
}
