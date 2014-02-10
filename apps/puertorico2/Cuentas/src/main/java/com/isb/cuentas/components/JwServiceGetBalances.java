package com.isb.cuentas.components;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isb.global.components.Account;
import com.isb.global.components.User;
import com.ivrpr.ws.JwsprBalanceAhorroRequest;
import com.ivrpr.ws.JwsprBalanceAhorroResponse2;
import com.ivrpr.ws.ObjectFactory;
import com.ivrpr.ws.WebServices;
import com.vectorsf.jvoiceframework.core.service.ws.WebServiceProvider;

@Component
@Scope("session")
public class JwServiceGetBalances implements Serializable
{

	private static final long serialVersionUID = 1226266381288260956L;

	
	@Inject
	private WebServiceProvider webServiceProvider;
	
	public String getAccountBalance(Account account)
	{
		String saldo="0";
		try
		{
			WebServices webService = webServiceProvider.getClient(WebServices.class, "WebServicesPort");

			ObjectFactory of = new ObjectFactory();
			JwsprBalanceAhorroRequest req = of.createJwsprBalanceAhorroRequest();
			req.setCallID("");
			req.setConnID("");
			System.out.println("########### Num Cuenta ###############" + account.getNumCuenta());
			req.setNumCtaAhorros(account.getNumCuenta());
			
			JwsprBalanceAhorroResponse2 res = webService.jwsprBalanceAhorro(req);
			saldo = res.getSaldoDisponible();
			System.out.println("########### saldo ############### " + saldo);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return saldo;
		
	}
}
