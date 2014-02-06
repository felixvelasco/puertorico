package com.isb.cuentas.components;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.ivrpr.ws.JwsprBalanceAhorroRequest;
import com.ivrpr.ws.JwsprBalanceAhorroResponse2;
import com.ivrpr.ws.ObjectFactory;
import com.ivrpr.ws.WebServices;
import com.vectorsf.jvoiceframework.core.service.ws.WebServiceProvider;



@Component
public class JWServiceGetBalances 
{
	@Inject
	private WebServiceProvider webServiceProvider;
	
	public Accounts getAccountBalance(Accounts in_cuen)
	{
		String saldo="0";
		try
		{
			WebServices webService = webServiceProvider.getClient(WebServices.class, "WebServicesPort");

			ObjectFactory of = new ObjectFactory();
			JwsprBalanceAhorroRequest req = of.createJwsprBalanceAhorroRequest();
			req.setCallID("");
			req.setConnID("");
			req.setNumCtaAhorros(in_cuen.getNumCuenta());
			
			JwsprBalanceAhorroResponse2 res = webService.jwsprBalanceAhorro(req);
			in_cuen.setSaldo(res.getSaldoDisponible());
			System.out.println("########### saldo ############### " + saldo);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return in_cuen;
		
	}
}
