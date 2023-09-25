package gov.psn.wpintegration.serviceImpl;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.google.gson.JsonObject;

import gov.psn.wpintegration.exception.WhatsappException;
import gov.psn.wpintegration.request.Change;
import gov.psn.wpintegration.request.Entry;
import gov.psn.wpintegration.request.Message;
import gov.psn.wpintegration.request.Value;
import gov.psn.wpintegration.request.WhatsAppMessageReplyRequest;
import gov.psn.wpintegration.service.WhatsAppResponseService;
import gov.psn.wpintegration.utill.SharingMessageUsingWhatsApp;

@ApplicationScoped
public class WhatsAppResponseServiceImpl implements WhatsAppResponseService{
	
	@Inject
	@ConfigProperty(name = "whatsapp.api.url")
	private String url;
	
	@Inject
	@ConfigProperty(name = "whatsapp.api.reply.token")
	private String replyToken;
	
	
	@Override
	public String sendReplyWhatsappMsg(WhatsAppMessageReplyRequest whatsAppMessageReplyRequest)
			throws WhatsappException {
		String msg ="";
		
		try {
			String object = whatsAppMessageReplyRequest.getObject();
			System.out.println(object);

			if ("whatsapp_business_account".equals(object)) { // Use the 'object' variable directly
				ArrayList<Entry> entries = whatsAppMessageReplyRequest.getEntry();

				if (entries != null && !entries.isEmpty()) {
					Entry entry = entries.get(0);
					ArrayList<Change> changes = entry.getChanges();

					if (changes != null && !changes.isEmpty()) {
						Change change = changes.get(0);
						Value value = change.getValue();

						if (value != null && value.getMessages() != null && !value.getMessages().isEmpty()) {
							Message message = value.getMessages().get(0);

							String phoneNumberId = value.getMetadata().getPhone_number_id();
							String from = message.getFrom();
							String text = message.getText().getBody();

							// Assuming you have the 'token' variable defined somewhere
							System.out.println("here it is : " + replyToken);

							// Construct your response JSON or perform other actions here
							String url = "https://graph.facebook.com/v17.0/" + phoneNumberId + "/messages?access_token="
									+ replyToken;

							JsonObject json = new JsonObject();
							json.addProperty("messaging_product", "whatsapp");
							json.addProperty("to", from);

							JsonObject textObject = new JsonObject();
							textObject.addProperty("body", "Hey, this is for testing");

							json.add("text", textObject);

							String jsonString = json.toString();
							System.out.println(jsonString);

							// Sending a success response
							msg = SharingMessageUsingWhatsApp.sendWhatsAppMessage(replyToken, url, jsonString);
							
							System.out.println(msg);
							
							return msg;
						}
					}
				}
			}
			msg ="Invalid JSON structure.";
			return msg;
		} catch (Exception ex) {
			msg = "Some error occurred:" + ex.getMessage();
			return msg;
		}
		
	
	}

}
