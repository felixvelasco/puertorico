package com.isb.main.components;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isb.global.components.CallData;

@Component
@Scope("session")
public class Init {

	@Inject
	private CallData callData;
	

	public void init() {
		callData.init();
	}
	
	public CallData getCallData() {
		return callData;
	}

	public void setCallData(CallData callData) {
		this.callData = callData;
	}
	
}
