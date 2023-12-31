package gov.psn.wpintegration.serviceImpl;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.google.gson.Gson;

import gov.psn.wpintegration.exception.WhatsappException;
import gov.psn.wpintegration.service.WhatsAppMessageService;
import gov.psn.wpintegration.utill.SharingMessageUsingWhatsApp;

@ApplicationScoped
public class WhatsAppMessageServiceImpl implements WhatsAppMessageService{
	
	@Inject
	@ConfigProperty(name = "whatsapp.api.url")
	private String url;
	
	@Inject
	@ConfigProperty(name = "whatsapp.api.token")
	private String token;
	
	@Override
	public String sendWhatsAppMessage(String whatsAppMessageRequest) throws WhatsappException {
		
		String sendWhatsAppMessage = SharingMessageUsingWhatsApp.sendWhatsAppMessage(token, url, whatsAppMessageRequest);
		System.out.println(sendWhatsAppMessage);
		
		return sendWhatsAppMessage;
		
	}
	
}
