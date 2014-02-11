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
import org.tempuri.ServicesSoap;

import com.isb.global.components.CallData;
import com.vectorsf.jvoiceframework.core.service.ws.WebServiceProvider;

@Component
@Scope("session")
public class PagoComercio implements Serializable {

	private static final long serialVersionUID = 5601957698327244373L;
	
	@Inject
	private WebServiceProvider webServiceProvider;
	@Inject
	private CallData callData;
	
	private int wsMerchantListResult;
	private List<Comercio> listaComercios;
	private Comercio comercioSel;
	
	private String merchantId;
	private String idMerchantCta;
	private String montoPay;
	private String fechProcPay;
	private String referenciaPago;
	
	public void wsMerchantList() {
		setWsMerchantListResult(0);
		listaComercios = new ArrayList<Comercio>();
		try
		{
			ServicesSoap webService = webServiceProvider.getClient(ServicesSoap.class, "ServicesSoap");
			
			JWServiceMerchListPRResponse2 res = webService.jwServiceMerchListPR("", "", callData.getUser().getSsid());
			if(res.getCodigoError().isEmpty() && res.getMSJERROR().isEmpty()) {
				setWsMerchantListResult(0);
				ArrayOfJWServiceMerchListPRResponseMerchantItem merchantL = res.getMerchantL();
				for(JWServiceMerchListPRResponseMerchantItem i: merchantL.getJWServiceMerchListPRResponseMerchantItem()) {
					Comercio com = new Comercio();
					com.setMerchantId(i.getMerchantId());
					com.setMerchantName(i.getMerchantName());
					com.setVruNumber(i.getVRUNumber());
					listaComercios.add(com);
				}
			} else if(res.getCodigoError().equals("01")) {
				setWsMerchantListResult(1);
			}
		}
		catch (Exception e)
		{
			setWsMerchantListResult(2);
			e.printStackTrace();
		}
	}

	public int getWsMerchantListResult() {
		return wsMerchantListResult;
	}

	public void setWsMerchantListResult(int wsMerchantListResult) {
		this.wsMerchantListResult = wsMerchantListResult;
	}
	
	public List<Comercio> getListaComercios() {
		return listaComercios;
	}

	public void setListaComercios(List<Comercio> listaComercios) {
		this.listaComercios = listaComercios;
	}

	public Comercio getComercioSel() {
		return comercioSel;
	}

	public void setComercioSel(Comercio comercioSel) {
		this.comercioSel = comercioSel;
	}
	
	public boolean isMerchantListOk() {
		return wsMerchantListResult == 0;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getIdMerchantCta() {
		return idMerchantCta;
	}

	public void setIdMerchantCta(String idMerchantCta) {
		this.idMerchantCta = idMerchantCta;
	}

	public String getMontoPay() {
		return montoPay;
	}

	public void setMontoPay(String montoPay) {
		this.montoPay = montoPay;
	}

	public String getFechProcPay() {
		return fechProcPay;
	}

	public void setFechProcPay(String fechProcPay) {
		this.fechProcPay = fechProcPay;
	}

	public String getReferenciaPago() {
		return referenciaPago;
	}

	public void setReferenciaPago(String referenciaPago) {
		this.referenciaPago = referenciaPago;
	}
}
