package com.isb.seleccionCuentas.components;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isb.global.components.Account;
import com.isb.global.components.User;

@Component
@Scope("prototype")
public class ManageCuentas implements Serializable
{

	private static final long serialVersionUID = 836507688272067287L;
	
	@Inject
	private User user;
	
	private String text;
	
	private int round = 1;
	
	public void manageTexts()
	{		
		StringBuilder sbText = new StringBuilder();
		List<Account> lCuen = user.getListaCuentas();
		
		sbText.append(getTextFromAccounts(lCuen));
		
		round++;
		setText(sbText.toString());
	}
	
	public void getDebitAccountsText(){
		
		StringBuilder sbText = new StringBuilder();
		List<Account> cuentasDebito = user.getCuentasDebito();
		
		sbText.append("Por favor, seleccione la cuenta desde la que desea hacer el pago.");
		sbText.append(getTextFromAccounts(cuentasDebito));
		
		setText(sbText.toString());

	}
	
	private StringBuilder getTextFromAccounts(List<Account> accounts){
		
		StringBuilder sbText = new StringBuilder();

		// se listan las 5 primeras
		if (round == 1)
		{
			for (int i = 0; i < accounts.size()&& i < 5; i++) 
			{
				Account accounti = accounts.get(i);
				sbText.append("Para la cuenta que termina en ");
				String numCuentaLast4 = accounti.getNumCuenta().substring(accounti.getNumCuenta().length()-4);
				for (int count=0;count<4;count++){
					sbText.append(numCuentaLast4.charAt(count) + " ");
				}
				sbText.append(", presione "+(i+1)+". ");
			}
			if (accounts.size()>5)
			{
				sbText.append("Para listar las siguientes cuentas presione 6.");
			}
		}
		// se listan las 5 segundas
		else
		{
			for (int i = 5; i < accounts.size()&& i < 10; i++) 
			{
				Account accounti = accounts.get(i);
				sbText.append("Para la cuenta que termina en ");
				String numCuentaLast4 = accounti.getNumCuenta().substring(accounti.getNumCuenta().length()-4);
				for (int count=0;count<4;count++){
					sbText.append(numCuentaLast4.charAt(count) + " ");
				}
				sbText.append(", presione "+(i+1)+". ");
			}			
		}
		
		return sbText;
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
