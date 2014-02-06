package com.isb.cuentas.components;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Accounts implements Serializable {

	private static final long serialVersionUID = -5322130833243268248L;
	
	private String numCuenta;
	private String saldo;
	
	public Accounts(String numCuenta2) 
	{
		numCuenta=numCuenta2;
	}
	public String getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	
}
