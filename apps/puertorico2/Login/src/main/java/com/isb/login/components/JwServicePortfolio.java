package com.isb.login.components;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.isb.global.components.Account;
import com.isb.global.components.CallData;
import com.ivrpr.ws.JwsprPortafolioRequest;
import com.ivrpr.ws.JwsprPortafolioResponse2;
import com.ivrpr.ws.ListaCuentas;
import com.ivrpr.ws.ObjectFactory;
import com.ivrpr.ws.WebServices;
import com.vectorsf.jvoiceframework.core.service.ws.WebServiceProvider;

@Component()
public class JwServicePortfolio implements Serializable {

	private static final long serialVersionUID = 6673215030467377034L;
	
	@Inject
	private WebServiceProvider webServiceProvider;
	@Inject
	private CallData callData;
	
	public void callPortafolio() {
		try
		{
			WebServices webService = webServiceProvider.getClient(WebServices.class, "WebServicesPort");

			ObjectFactory of = new ObjectFactory();
			JwsprPortafolioRequest req = of.createJwsprPortafolioRequest();
			req.setCallID("");
			req.setConnID("");
			req.setIdContacto("");
			req.setNSS(callData.getUser().getSsid());

			JwsprPortafolioResponse2 res = webService.jwsprPortafolio(req);
			List<ListaCuentas> listaCuentas = res.getListaCuentas();
			System.out.println("########### listaCuentas ############### " + listaCuentas);
			for (ListaCuentas cueni : listaCuentas) 
			{
				callData.getListaCuentas().add(new Account(cueni.getNumCuenta()));
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
