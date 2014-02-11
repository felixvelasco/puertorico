package com.isb.trxYPagos.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tempuri.ArrayOfJWServiceMerchListPRResponseMerchantItem;
import org.tempuri.JWServiceMerchListPRResponse2;
import org.tempuri.JWServiceMerchListPRResponseMerchantItem;
import org.tempuri.JWServiceMerchantPayResponse2;
import org.tempuri.ServicesSoap;

import com.isb.global.components.CallData;
import com.vectorsf.jvoiceframework.core.service.ws.WebServiceProvider;

@Component
@Scope("session")
public class JwsMerchantPay implements Serializable {
	
	private static final long serialVersionUID = 5094852748578008545L;
	
	@Inject
	private WebServiceProvider webServiceProvider;
	@Inject
	private PagoComercio pagoComercio;
	private int result;
	
	public void wsMerchantPay() {
		setResult(0);
		try
		{
			ServicesSoap webService = webServiceProvider.getClient(ServicesSoap.class, "ServicesSoap");
			
			String merchantId = pagoComercio.getMerchantId();
			String idMerchantCta = pagoComercio.getIdMerchantCta();
			String montoPay = pagoComercio.getMontoPay();
			String fechProcPay = pagoComercio.getFechProcPay();
			
			JWServiceMerchantPayResponse2 response = webService.jwServiceMerchantPay("", "", "", "", merchantId, idMerchantCta, montoPay, fechProcPay);
			if(response.getCodigoError().isEmpty() && response.getMSJERROR().isEmpty()) {
				pagoComercio.setReferenciaPago(response.getNumConfirmacion());
			} else {
				setResult(1);
			}
		}
		catch (Exception e)
		{
			setResult(2);
			e.printStackTrace();
		}
	}


	public PagoComercio getPagoComercio() {
		return pagoComercio;
	}


	public void setPagoComercio(PagoComercio pagoComercio) {
		this.pagoComercio = pagoComercio;
	}


	public int getResult() {
		return result;
	}


	public void setResult(int result) {
		this.result = result;
	}
}
