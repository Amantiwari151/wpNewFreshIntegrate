package gov.psn.wpintegration.service;

import gov.psn.wpintegration.exception.WhatsappException;

public interface WhatsAppMessageService {
	
	public String sendWhatsAppMessage(String whatsAppMessageRequest) throws WhatsappException;
	
}
