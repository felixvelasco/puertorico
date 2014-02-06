package com.isb.main.components;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isb.global.components.CallData;
import com.nortel.pso.cti.genesys.driver.GenIVRDriver;

@Component
@Scope("session")
public class Inicializacion implements Serializable {

	private static final long serialVersionUID = -6163513898600067213L;

	@Inject
	private CallData callData;
//	private GenesysAdapter cti = null;
	
	public void init() {
		callData.init();

//		// Initialize CTI connector
//		cti = new GenesysAdapter(ivrSession);
//			try {
//				cti.init(); // initialize the driver (only the first time it
//							// does something)
//				cti.newCall(1000); // notify Genesys
// 
//			} catch (CTIException e) {
//				cti = null;
//			}
//		}
	}
}
