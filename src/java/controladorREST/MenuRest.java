/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorREST;

/**
 *
 * @author Pandita
 */
import com.google.gson.Gson;
import dao.menuMDL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import dto.menuDTO;

@Path("menu")
public class MenuRest {

    private menuMDL modelo;

    private menuDTO dto;

    @GET
    @Produces(MediaType.TEXT_PLAIN)

    public String getTestREST() {
        return "Conexion Establecida";
    }

    @GET
    @Path("getSid")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestREST(@QueryParam("id") String id) {
        return "Valor Recibido " + id;
    }

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertar(InputStream v) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(v));

            String json = "";
            if (br.ready()) {
                json = br.readLine();
            }
            System.out.println(" json obtenido " + json);

            menuDTO dto = new Gson().fromJson(json, menuDTO.class);

            modelo = new menuMDL();
            modelo.agregar(dto);
/*
            System.out.println("dto.id " + dto.getId());
            System.out.println("dto.nombre " + dto.getNombre());
            System.out.println("dto.obsv " + dto.getObsv());
*/
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

            menuDTO dto = new Gson().fromJson(json, menuDTO.class);

            System.out.println("dto.id actualizar" + dto.getId());
            System.out.println("dto.nombre actualizar" + dto.getNombre());
            System.out.println("dto.obsv actualizar" + dto.getObsv());

        } catch (IOException ex) {
            Logger.getLogger(MenuRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
