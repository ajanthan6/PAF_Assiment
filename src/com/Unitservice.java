package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Unit;

@Path("/Units")
public class Unitservice {
	
	
	Unit unitsObj = new Unit();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return  unitsObj.readunit();
	}
	
	//POST
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUnit(@FormParam("AccountNo") String AccountNo,
	 @FormParam("CusName") String CusName,
	 @FormParam("Unit") String Unit,
	 @FormParam("Month") String Month,
	@FormParam("Amount") String Amount)
	{
	 String output =  unitsObj.insertUnit(AccountNo, CusName,  Unit, Month, Amount);
	return output;
	}
	
	//PUT
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateUnit(String unitData)
		{
		//Convert the input string to a JSON object
		 JsonObject unitObject = new JsonParser().parse(unitData).getAsJsonObject();
		//Read the values from the JSON object
		 String UnitID = unitObject.get("UnitID").getAsString();
		 String AccountNo = unitObject.get("AccountNo").getAsString();
		 String CusName = unitObject.get("CusName").getAsString();
		 String Unit = unitObject.get("Unit").getAsString();
		 String Month = unitObject.get("Month").getAsString();
		 String Amount = unitObject.get("Amount").getAsString();
		 
		 String output =  unitsObj. updateUnit(UnitID, AccountNo, CusName,  Unit, Month, Amount);
		return output;
		}
		
		//DELETE
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteItem(String unitData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(unitData, "", Parser.xmlParser());

		//Read the value from the element 
		 String UnitID = doc.select("UnitID").text();
		 String output =  unitsObj.deleteUnit(UnitID);
		return output;
		}

}
