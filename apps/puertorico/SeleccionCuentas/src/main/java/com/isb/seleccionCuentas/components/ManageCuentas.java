package com.isb.seleccionCuentas.components;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isb.global.components.Account;
import com.isb.global.components.CallData;

@Component
@Scope("prototype")
public class ManageCuentas 
{
	@Inject
	Text text;
	@Inject
	AuxClass aux;
	CallData data;
	
	
	public String manageTexts()
	{		
		StringBuffer sbText = new StringBuffer();
		List<Account> lCuen = data.getListaCuentas();
		// se listan las 5 primeras
		if (aux.getVuelta().equals("1"))
		{
			for (int i = 0; i < lCuen.size()&& i < 5; i++) 
			{
				Account accounti = lCuen.get(i);
				sbText.append("Para la cuenta que termina en "+accounti.getNumCuenta().substring(accounti.getNumCuenta().length()-4)+", presione "+(i+1)+". ");
			}
			if (lCuen.size()>5)
			{
				sbText.append("Para listar las siguientes cuentas presione 6.");
			}
		}
		// se listan las 5 segundas
		else
		{
			for (int i = 5; i < lCuen.size()&& i < 10; i++) 
			{
				Account accounti = lCuen.get(i);
				sbText.append("Para la cuenta que termina en "+accounti.getNumCuenta().substring(accounti.getNumCuenta().length()-4)+", presione "+(i+1)+". ");
			}			
		}
		aux.incVuelta();	
		text.setTexto(sbText.toString());
		return null;
	}
}
