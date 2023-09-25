package gov.psn.wpintegration.service;

import gov.psn.wpintegration.exception.WhatsappException;
import gov.psn.wpintegration.request.WhatsAppMessageReplyRequest;

public interface WhatsAppResponseService {

	public String sendReplyWhatsappMsg(WhatsAppMessageReplyRequest whatsAppMessageReplyRequest) throws WhatsappException;
	
}
