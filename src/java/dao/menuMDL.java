package dao;

import dto.menuDTO;
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

public class menuMDL implements CRUD<menuDTO>{
    private PreparedStatement ps;
    private ResultSet rs;
    private String query;
    private final ConexionBD conec;

    public menuMDL() {
        conec=new ConexionBD(TipoMotorBD.MYSQL);
    }
    
    @Override
    public Boolean agregar(menuDTO objeto) {
        try {
            query="INSERT INTO menu(nombre, obsv) VALUES (?, ?);";
            ps=conec.getConnection().prepareStatement(query);
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getObsv());
            return ps.executeUpdate() > 0  ;
        } catch (SQLException ex) {
            Logger.getLogger(menuMDL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            conec.cerrarConexionBD();
        }
    }

    @Override
    public Boolean modificar(menuDTO objeto) {
        try {
            query="UPDATE menu SET nombre=?, obsv=? WHERE id=?;";
            ps=conec.getConnection().prepareStatement(query);
            ps.setString(1, objeto.getNombre());
            ps.setString(2, objeto.getObsv());
            ps.setInt(3, objeto.getId());
            return ps.executeUpdate() > 0  ;
        } catch (SQLException ex) {
            Logger.getLogger(menuMDL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            conec.cerrarConexionBD();
        }
    }

    @Override
    public Boolean eliminar(menuDTO objeto) {
        try {
            query="DELETE FROM menu WHERE id=?;";
            ps=conec.getConnection().prepareStatement(query);
            ps.setInt(1, objeto.getId());
            return ps.executeUpdate() > 0  ;
        } catch (SQLException ex) {
            Logger.getLogger(menuMDL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            conec.cerrarConexionBD();
        }
    }

    @Override
    public List<menuDTO> consultarTodos() {
        List<menuDTO> lista=null;
        menuDTO dto;
        try {    
            query="SELECT id, nombre, obsv FROM menu ORDER BY id;";
            ps=conec.getConnection().prepareStatement(query);
            rs=ps.executeQuery();
            lista=new ArrayList<>();
            while (rs.next()) {                
                dto=new menuDTO();
                dto.setId(rs.getInt("id"));
                dto.setNombre(rs.getString("nombre"));
                dto.setObsv(rs.getString("obsv"));
                lista.add(dto);
                
              //  System.out.println(dto.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(menuMDL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
                rs.close();
             //   conec.cerrarConexionBD();
            } catch (SQLException ex) {
                Logger.getLogger(menuMDL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public menuDTO consultarSegunID(menuDTO objeto) {
        menuDTO dto = null;
        try {    
            query="SELECT id, nombre, obsv FROM menu WHERE id=?;";
            ps=conec.getConnection().prepareStatement(query);
            ps.setInt(1, objeto.getId());
            rs=ps.executeQuery();
            if (rs.next()) {                
                dto=new menuDTO();
                dto.setId(rs.getInt("id"));
                dto.setNombre(rs.getString("nombre"));
                dto.setObsv(rs.getString("obsv"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(menuMDL.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
                rs.close();
                conec.cerrarConexionBD();
            } catch (SQLException ex) {
                Logger.getLogger(menuMDL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dto;
    }
    
}
