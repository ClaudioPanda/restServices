package dao;

import dto.menuItemsDTO;
import genericos.CRUD;
import genericos.ConexionBD;
import genericos.TipoMotorBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class menuItemMDL implements CRUD<menuItemsDTO> {

    private PreparedStatement ps;
    private ResultSet rs;
    private String query;
    private final ConexionBD conec;

    public menuItemMDL() {
        this.conec = new ConexionBD(TipoMotorBD.MYSQL);
    }

    @Override
    public Boolean agregar(menuItemsDTO objeto) {
        try {
            query = "INSERT INTO menu_item(nombre, obsv, Menu_id) "
                    + "VALUES ( ?, ?, ?);";
            ps = conec.getConnection().prepareStatement(query);
            ps.setString(1, objeto.getMenu_item());
            ps.setString(2, objeto.getMenu_item_obs());
            ps.setInt(3, objeto.getId_menu());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(menuItemMDL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            conec.cerrarConexionBD();
        }
    }

    @Override
    public Boolean modificar(menuItemsDTO objeto) {
        try {
            query = "UPDATE menu_item SET nombre=?, obsv=?, Menu_id=? WHERE id=?;";
            ps = conec.getConnection().prepareStatement(query);
            ps.setString(1, objeto.getMenu_item());
            ps.setString(2, objeto.getMenu_item_obs());
            ps.setInt(3, objeto.getId_menu());
            ps.setInt(4, objeto.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(menuItemMDL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            conec.cerrarConexionBD();
        }
    }

    @Override
    public Boolean eliminar(menuItemsDTO objeto) {
        try {
            query = "DELETE FROM menu_item WHERE id=?;";
            ps = conec.getConnection().prepareStatement(query);
            ps.setInt(1, objeto.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(menuItemMDL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            conec.cerrarConexionBD();
        }
    }

    @Override
    public List<menuItemsDTO> consultarTodos() {
        List<menuItemsDTO> lista = null;
        menuItemsDTO dto;
        try {
            query = "SELECT a.id, a.nombre as menuitem, a.obsv, a.id, b.nombre as menu\n"
                    + "FROM menu_item a INNER JOIN menu b ON a.id=b.id\n"
                    + "ORDER BY a.id; ";
            System.out.println("sql " + query);
            ps = conec.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            //if (rs != null) {
            lista = new ArrayList<>();
            while (rs.next()) {
                dto = new menuItemsDTO();
                dto.setId(rs.getInt("id"));
                dto.setMenu_item(rs.getString("menuitem"));
                dto.setMenu_item_obs(rs.getString("obsv"));
                dto.setId_menu(rs.getInt("id"));
                dto.setNombre_menu(rs.getString("menu"));
                lista.add(dto);
            }
            //}
        } catch (SQLException ex) {
            Logger.getLogger(menuItemMDL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //conec.cerrarConexionBD();
//            try {
//           //     ps.close();
//            //    rs.close();
//              
//            conec.cerrarConexionBD();
//            } catch (SQLException ex) {
//                Logger.getLogger(menuItemMDL.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        return lista;
    }

    @Override
    public menuItemsDTO consultarSegunID(menuItemsDTO objeto) {

        menuItemsDTO dto = null;
        try {
            query = "SELECT a.id, a.nombre as menuitem, a.obsv, a.id, b.nombre as menu\n"
                    + "FROM menu_item a INNER JOIN menu b ON a.id=b.id\n"
                    + "WHERE a.id = ?;";

            ps = conec.getConnection().prepareStatement(query);
            ps.setInt(1, objeto.getId());

            rs = ps.executeQuery();
            //if (rs != null) {
            if (rs.next()) {
                dto = new menuItemsDTO();
                dto.setId(rs.getInt("id"));
                dto.setMenu_item(rs.getString("menuitem"));
                dto.setMenu_item_obs(rs.getString("obsv"));
                dto.setId_menu(rs.getInt("id"));
                dto.setNombre_menu(rs.getString("menu"));
            }
            //}
        } catch (SQLException ex) {
            Logger.getLogger(menuItemMDL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //conec.cerrarConexionBD();
//            try {
//           //     ps.close();
//            //    rs.close();
//              
//            conec.cerrarConexionBD();
//            } catch (SQLException ex) {
//                Logger.getLogger(menuItemMDL.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
        return dto;

    }

}
