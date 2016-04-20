package com.genc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.genc.dao.CrudDb;

@Path("v1/search")
public class V1_SearchModel {

	@Path("/{keyid}/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
		public Response searchBrand(@PathParam("keyid") int keyid, @PathParam("brand") String brand){
			String returnString=null;
			JSONArray json = new JSONArray();
			try{
				CrudDb search = new CrudDb();
				json = search.searchProducts(keyid, brand);
				returnString = json.toString();
			}catch(Exception e){
				e.printStackTrace();
				return Response.status(500).entity("Server can not work").build();
				
			}
			return Response.ok(returnString).build();
		}
	
	@Path("/{keyid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
		public Response searchBrand(@PathParam("keyid") int keyid){
			String returnString=null;
			JSONArray json = new JSONArray();
			try{
				CrudDb search = new CrudDb();
				json = search.searchProducts(keyid);
				returnString = json.toString();
			}catch(Exception e){
				e.printStackTrace();
				return Response.status(500).entity("Server can not work").build();
				
			}
			return Response.ok(returnString).build();
		}
}
