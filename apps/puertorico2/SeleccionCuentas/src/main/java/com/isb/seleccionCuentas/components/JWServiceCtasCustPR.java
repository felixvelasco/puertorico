package com.isb.seleccionCuentas.components;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tempuri.ArrayOfJWServiceCtasCustPRResponseCtaItem;
import org.tempuri.JWServiceCtasCustPRResponse;
import org.tempuri.JWServiceCtasCustPRResponse2;
import org.tempuri.JWServiceCtasCustPRResponseCtaItem;
import org.tempuri.ObjectFactory;
import org.tempuri.Services;
import org.tempuri.ServicesSoap;

import com.isb.global.components.User;
import com.vectorsf.jvoiceframework.core.service.ws.WebServiceProvider;

@Component
@Scope("session")
public class JWServiceCtasCustPR {
	
	@Inject
	WebServiceProvider webServiceProvider;
	
	@Inject
	User user;
	
	public void invokeCtasCustPR(){
		
		ServicesSoap services = webServiceProvider.getClient(ServicesSoap.class, "ServicesSoap");	
				
		String connID = "";
		String callID = "";
		String customerId = "";
		String idMerchantCta = "";
		
		JWServiceCtasCustPRResponse2 respBody = services.jwServiceCtasCustPR(connID, callID, customerId, idMerchantCta);		

		//		user.setCuentasDebito(respBody.getCuentasL());
				
	}

}
