package com.isb.trxYPagos.components;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class InfoPago implements Serializable {
	
	private static final long serialVersionUID = 4046208282375297622L;
	
	@Inject
	private PagoComercio pagoComercio;

	public PagoComercio getPagoComercio() {
		return pagoComercio;
	}

	public void setPagoComercio(PagoComercio pagoComercio) {
		this.pagoComercio = pagoComercio;
	}
	
	public String getTts() {
		StringBuilder sb = new StringBuilder();
		sb.append("Su pago ha sido procesado correctamente. Por favor, anote su número de comprobante para referencia futura: ");
		sb.append(pagoComercio.getReferenciaPago());
		return sb.toString();
	}
	
}
