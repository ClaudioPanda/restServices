/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorREST;

import com.google.gson.Gson;
import dao.menuItemMDL;
import dao.menuMDL;
import dto.menuDTO;
import dto.menuItemsDTO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pandita
 */
@Path("/menuItem")

public class MenuItemREST {

    private menuItemMDL dao;
    private menuItemsDTO dto;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestComunicacion() {
        return "OK";
    }

    @GET
    @Path("/listarTodos")
    @Produces(MediaType.TEXT_PLAIN)
    public String getListarComunicacion() {
        dao = new menuItemMDL();
        return new Gson().toJson(dao.consultarTodos());
    }

    @GET
    @Path("/obtenerUnico")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTestComunicacion(@QueryParam("id") String id) {
        dto = new menuItemsDTO();
        dto.setId(Integer.parseInt(id));
        dao = new menuItemMDL();
        return new Gson().toJson(dao.consultarSegunID(dto));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertar(InputStream v) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(v));

            String json = "";
            if (br.ready()) {
                json = br.readLine();
            }
            System.out.println(" json obtenido " + json);

            dto = new Gson().fromJson(json, menuItemsDTO.class);

            dao = new menuItemMDL();
            dao.agregar(dto);

        } catch (IOException ex) {
            Logger.getLogger(MenuRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @POST
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizar(InputStream v) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(v));

            String json = "";
            if (br.ready()) {
                json = br.readLine();
            }
            System.out.println(" json obtenido actualizar" + json);

            dto = new Gson().fromJson(json, menuItemsDTO.class);

            dao = new menuItemMDL();
            dao.modificar(dto);

        } catch (IOException ex) {
            Logger.getLogger(MenuRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @POST
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void eliminar(InputStream v) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(v));

            String json = "";
            if (br.ready()) {
                json = br.readLine();
            }
            System.out.println(" json obtenido eliminar" + json);

            dto = new Gson().fromJson(json, menuItemsDTO.class);

            dao = new menuItemMDL();
            dao.eliminar(dto);

        } catch (IOException ex) {
            Logger.getLogger(MenuItemREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
