package com.isb.trxYPagos.components;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class MenuSelFechaCantidad implements Serializable {

	private static final long serialVersionUID = -368229476425516531L;
	
	@Inject
	private PagoComercio pagoComercio;

	public PagoComercio getPagoComercio() {
		return pagoComercio;
	}

	public void setPagoComercio(PagoComercio pagoComercio) {
		this.pagoComercio = pagoComercio;
	}
	
	public void setPagoHoy() {
		pagoComercio.setFechProcPay(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	}
	
	public void setPagoOtroDia(String fecha) {
		pagoComercio.setFechProcPay(fecha);
	}
	
	public void setCantidadPago(String cantidad) {
		pagoComercio.setMontoPay(cantidad);
	}
}
