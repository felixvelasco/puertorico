package com.isb.login.components;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isb.global.components.CallData;
import com.ivrpr.ws.JwServiceAutenticaRequest;
import com.ivrpr.ws.JwServiceAutenticaResponse2;
import com.ivrpr.ws.ObjectFactory;
import com.ivrpr.ws.WebServices;
import com.vectorsf.jvoiceframework.core.service.ws.WebServiceProvider;
import com.vectorsf.jvoiceframework.isban.logger.log.StatisticsLogger;

@Component()
@Scope("session")
public class JwsAutenticaSevice implements Serializable {

	private static final long serialVersionUID = 6673215030467377034L;
	
	@Inject
	private WebServiceProvider webServiceProvider;
	@Inject
	private CallData callData;
	private int result;
	
	public void autenticacion() {
		try
		{
			WebServices webService = webServiceProvider.getClient(WebServices.class, "WebServicesPort");

			ObjectFactory of = new ObjectFactory();
			JwServiceAutenticaRequest req = of.createJwServiceAutenticaRequest();
			req.setANI("");
			req.setCallID("");
			req.setConnID("");
			req.setIdContacto("");
			req.setNSS(callData.getUser().getSsid());
			req.setNIP(callData.getUser().getPin());

			JwServiceAutenticaResponse2 res = webService.jwServiceAutentica(req);
			System.out.println("########################## " + res.getDescTipoSegmento());
			if(res.getCodigoError().isEmpty() && res.getMsjError().isEmpty()) {
				setResult(0);
				callData.getUser().setLogged(true);
			} else if(res.getCodigoError().equals("01")) {
				setResult(1);
			} else if(res.getCodigoError().equals("02")) {
				setResult(2);
			} else {
				setResult(3);
			}
		}
		catch (Exception e)
		{
			setResult(3);
			e.printStackTrace();
		}
		
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}

