package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Bill;

@Path("/Bill")
public class Bill_API {
	Bill Bill_Obj = new Bill();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBilling() {
		return Bill_Obj.readBilling();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBilling(@FormParam("uid") String uid,		
	 @FormParam("Billusage") String Billusage,
	 @FormParam("value") String value,
	 @FormParam("vat") String vat,
	 @FormParam("total") String total)
	{
	 String output = Bill_Obj.insertBilling(uid, Billusage, value, vat, total);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBilling(String billapiData)
	{
	//Convert the input string to a JSON object
	 JsonObject BillAPIObject = new JsonParser().parse(billapiData).getAsJsonObject();
	//Read the values from the JSON object
	 String bid = BillAPIObject.get("bid").getAsString();
	 String uid = BillAPIObject.get("uid").getAsString();
	 String Billusage = BillAPIObject.get("Billusage").getAsString();
	 String value = BillAPIObject.get("value").getAsString();
	 String vat = BillAPIObject.get("vat").getAsString();
	 String total = BillAPIObject.get("total").getAsString();
	 String output = Bill_Obj.updateBilling(bid, uid, Billusage, value, vat, total);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBilling(String billapiData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billapiData, "", Parser.xmlParser());

	//Read the value from the element <BillID>
	 String bid = doc.select("bid").text();
	 String output = Bill_Obj.deleteBilling(bid);
	return output;
	}
	
}
