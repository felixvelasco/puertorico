package com.isb.seleccionCuentas.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tempuri.ArrayOfJWServiceCtasMerchPRResponseMerchantItem;
import org.tempuri.JWServiceCtasMerchPRResponse2;
import org.tempuri.JWServiceCtasMerchPRResponseMerchantItem;
import org.tempuri.ServicesSoap;

import com.vectorsf.jvoiceframework.core.service.ws.WebServiceProvider;

@Component
@Scope("session")
public class JwsCuentasMerch implements Serializable {

	private static final long serialVersionUID = 7592637692480717933L;
	
	@Inject
	private WebServiceProvider webServiceProvider;
	private int result;
	private List<CtaComercio> listaCtaComercio;
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void serviceCuentasMerch(String merchantId) {
		listaCtaComercio = new ArrayList<CtaComercio>();
		try
		{
			ServicesSoap webService = webServiceProvider.getClient(ServicesSoap.class, "ServicesSoap");
			
			JWServiceCtasMerchPRResponse2 res = webService.jwServiceCtasMerchPR("", "", "", merchantId);
			if(res.getCodigoError().isEmpty() && res.getMSJERROR().isEmpty()) {
				setResult(0);
				ArrayOfJWServiceCtasMerchPRResponseMerchantItem merchantL = res.getMerchantCtaL();
				for(JWServiceCtasMerchPRResponseMerchantItem i: merchantL.getJWServiceCtasMerchPRResponseMerchantItem()) {
					CtaComercio cta = new CtaComercio();
					cta.setId(i.getIdMerchantCta());
					cta.setCuenta(i.getMerchantCta());
					listaCtaComercio.add(cta);
				}
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

	public List<CtaComercio> getListaCtaComercio() {
		return listaCtaComercio;
	}

	public void setListaCtaComercio(List<CtaComercio> listaCtaComercio) {
		this.listaCtaComercio = listaCtaComercio;
	}
}
