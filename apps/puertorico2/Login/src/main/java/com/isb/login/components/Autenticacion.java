package com.isb.login.components;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isb.global.components.CallData;

@Component
@Scope("session")
public class Autenticacion implements Serializable {

	private static final long serialVersionUID = -2346944620778121389L;
	
	@Inject
	private CallData callData;

	public CallData getCallData() {
		return callData;
	}

	public void setCallData(CallData callData) {
		this.callData = callData;
	}
	
	public void setPin(String pin) {
		callData.getUser().setPin(pin);
	}
}
