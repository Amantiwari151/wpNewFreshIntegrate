package gov.psn.wpintegration.controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.google.gson.JsonObject;

import gov.psn.wpintegration.common.ApiResponse;
import gov.psn.wpintegration.common.Error;
import gov.psn.wpintegration.exception.WhatsappException;
import gov.psn.wpintegration.request.Change;
import gov.psn.wpintegration.request.Entry;
import gov.psn.wpintegration.request.Message;
import gov.psn.wpintegration.request.Metadata;
import gov.psn.wpintegration.request.Value;
import gov.psn.wpintegration.request.WhatsAppMessageReplyRequest;
import gov.psn.wpintegration.service.WhatsAppMessageService;
import gov.psn.wpintegration.service.WhatsAppResponseService;
import gov.psn.wpintegration.utill.SharingMessageUsingWhatsApp;
import io.helidon.common.http.Http;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;

@Path("/call")
public class WhatsAppMessageController {

	@Inject
	private WhatsAppMessageService whatsAppMsgService;

	@Inject
	@ConfigProperty(name = "whatsapp.api.token")
	private String configtoken;
	
	
	@Inject
	private WhatsAppResponseService whatsAppResponseService;

	@Path("/get")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendData() {
		System.out.println("Hello bro");
		return Response.ok(ApiResponse.success("great hola")).build();
	}

	@Path("/getResp")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendWhatsAppMessage(@RequestBody String whatsAppMessageRequest) {

		try {
			String sendWhatsAppMessage = whatsAppMsgService.sendWhatsAppMessage(whatsAppMessageRequest);
			return Response.ok(ApiResponse.success(sendWhatsAppMessage)).build();
		} catch (WhatsappException e) {
			e.printStackTrace();
			return Response.ok(ApiResponse.error(Error.create("400", "some error occurred"))).build();
		}

	}

	@GET
	@Path("/webhooks")
	@Produces(MediaType.APPLICATION_JSON)
	public Response handleWebhook(@QueryParam("hub.mode") String mode, @QueryParam("hub.challenge") String challenge,
			@QueryParam("hub.verify_token") String token) {

		final String myToken = "aman"; // Your token here

		if ("subscribe".equals(mode) && myToken.equals(token)) {
			return Response.ok(ApiResponse.success(challenge)).build();
		} else {
			return Response.ok(ApiResponse.error(Error.create("403", "forbidden"))).build();
		}
	}

	@POST
	@Path(("/webhooks"))
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response replyWhatsAppMessage(@RequestBody WhatsAppMessageReplyRequest whatsAppMessageReplyRequest) {
		
		try {
			String sendReplyWhatsappMsg = whatsAppResponseService.sendReplyWhatsappMsg(whatsAppMessageReplyRequest);
			return Response.ok(ApiResponse.success(sendReplyWhatsappMsg)).build();
		} catch (WhatsappException e) {
			return Response.ok(ApiResponse.error(Error.create("403", "forbidden"))).build();
		}
	
	}

}
