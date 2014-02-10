package com.isb.trxYPagos.components;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class MenuConfPagoComercio implements Serializable {
	
	private static final long serialVersionUID = 4144805160307719336L;
	
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
		sb.append("Usted ha seleccionado realizar un pago a ");
		sb.append(pagoComercio.getComercioSel().getMerchantName());
		sb.append(" para debitar la cuenta corriente de ahorro terminada en ");
		//TODO Añadir cuenta debito
		sb.append(" para la fecha de ");
		//TODO Añadir fecha
		sb.append(" por la cantidad de ");
		//TODO Añadir cantidad
		sb.append(".  Para confirmar el pago, presione 1.  Para cancelar y seleccionar otro pago, presione 2. Para volver al menú principal, presione 9. Para hablar con un representante de servicio al cliente, presione 0.");
		
		return sb.toString();
	}
}
