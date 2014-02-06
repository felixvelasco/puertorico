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
	private boolean moreOptions;

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
		if(moreOptions) {
			sb.append("Para escuchar los siguientes, presione 6.");
		}
		return sb.toString();
	}

	public boolean isMoreOptions() {
		return moreOptions;
	}

	public void setMoreOptions(boolean moreOptions) {
		this.moreOptions = moreOptions;
	}

}
