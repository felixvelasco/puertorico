package com.isb.trxYPagos.components;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class Comercio implements Serializable {

	private static final long serialVersionUID = -3886902150917105314L;
	
	private String merchantId;
	private String merchantName;
	private String vruNumber;
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getVruNumber() {
		return vruNumber;
	}
	public void setVruNumber(String vruNumber) {
		this.vruNumber = vruNumber;
	}
	
	
}
