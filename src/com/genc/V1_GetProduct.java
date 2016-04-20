package com.genc;


import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.genc.dao.GetDbConnection;
import com.genc.util.ConvertToJSON;

@Path("/v1/getproduct")

public class V1_GetProduct {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts() throws Exception{
		

			
			String returnString = null;
			
			Response rb = null;
			try{
				ConvertToJSON converter = new ConvertToJSON();
				JSONArray json = new JSONArray();
				
				ResultSet rs = GetDbConnection.returnResultSet("car");				
				json = converter.toJSONArray(rs);
				
				returnString = json.toString();
				rb = Response.ok(returnString).build();
				
			}catch(Exception e ){
				e.printStackTrace();
				System.out.println("ERROR");
			}
			return rb;
		}

}
