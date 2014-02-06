package com.isb.trxYPagos.components;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resources/TrxYPagos/grammars/dtmfGrammarController.grxml")
public class DtmfGrammarController {
	
	@RequestMapping(method = RequestMethod.POST, produces="application/srgs+xml")
	@ResponseBody
	public String requestGrammarPost(@RequestParam("options") String options) {
		return createGrammar(options);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces="application/srgs+xml")
	@ResponseBody
	public String requestGrammar(@RequestParam("options") String options) {
		return createGrammar(options);
	}

	private String createGrammar(String options) {
		String arrOptions[] = options.split(";");
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" ?>");
		sb.append("<grammar root=\"main\" version=\"1.0\" xml:lang=\"es-ES\" mode=\"dtmf\">");
		sb.append("\t<rule id=\"main\" scope=\"public\">");
		sb.append("\t\t<one-of>");
		for(String option: arrOptions) {
			sb.append("\t\t\t<item>" + option + "</item>");
		}
		sb.append("\t\t</one-of>");
		sb.append("\t\t</rule>");
		sb.append("\t\t</grammar>");
		
		return sb.toString();
	}
	
	
}
