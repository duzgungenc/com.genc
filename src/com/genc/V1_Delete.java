package com.genc;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.genc.dao.CrudDb;


@Path("v1/delete")
public class V1_Delete {

	
	@Path("/{item_number}")
	@DELETE
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(	@PathParam("item_number") int item_number,
									String incomingData) 
								throws Exception {
		
		
		
		int id;
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		CrudDb dao = new CrudDb();
		
		try {
			
			JSONObject partsData = new JSONObject(incomingData);
			id = partsData.optInt("id", 0);

			http_code = dao.deleteCar(id);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been deleted successfully");
			} else {
				return Response.status(500).entity("Server can not work").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server can not work").build();
		}
		
		return Response.ok(returnString).build();
	}
}
