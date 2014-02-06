package com.isb.global.components;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CallData implements Serializable {
	
	private static final long serialVersionUID = -3391627210285633515L;
	
	private String application;
	private boolean promoActive;
	@Inject
	private User user;
	private List<Account> listaCuentas;
	
	public void init() {
		this.application = "retail";
		this.promoActive = false;
	}
	
	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public boolean isPromoActive() {
		return promoActive;
	}

	public void setPromoActive(boolean promoActive) {
		this.promoActive = promoActive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Account> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(List<Account> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	
	
}
