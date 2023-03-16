package magazin_animale.database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DBDataAnimale {
    static DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{},
            new Object[]{"id", "nume", "specie", "rasa", "pret", "numar"}
    );

    public static void loadDataAnimale(JTable tabel) {
        try (Connection con = DBConnection.getConnection()){
            Statement st = con.createStatement();
            ResultSet response = st.executeQuery("select * from animale order by id");

            tableModel.setRowCount(0);

            while (response.next()) {
                tableModel.addRow(new Object[]{
                        response.getString("id"),
                        response.getString("nume"),
                        response.getString("specie"),
                        response.getString("rasa"),
                        response.getString("pret"),
                        response.getString("numar")});
            }
            tabel.setModel(tableModel);

            response.close();
            st.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addAnimal(String specie, String rasa, Double pret, String nume, Integer numar) {
        try(Connection con = DBConnection.getConnection()) {
            String sql = "insert into animale(nume, specie, rasa, pret, numar) values(?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nume);
            st.setString(2, specie);
            st.setString(3, rasa);
            st.setDouble(4, pret);
            st.setInt(5, numar);

            st.execute();
            st.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateAnimal(String specie, String rasa, Double pret, String nume, Integer numar, Integer id) {
        try(Connection con = DBConnection.getConnection()) {
            String sql = "update animale set nume = ?, specie =?, rasa=?, pret=?, numar =? where id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nume);
            st.setString(2, specie);
            st.setString(3, rasa);
            st.setDouble(4, pret);
            st.setInt(5, numar);
            st.setInt(6, id);

            st.execute();
            st.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAnimal(Integer id){
        try(Connection con = DBConnection.getConnection()) {
            String sql = "delete from animale where id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            st.execute();
            st.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getAnimals(){
        try(Connection con = DBConnection.getConnection()) {
            String sql = "select id, nume from animale";
            PreparedStatement st = con.prepareStatement(sql);

            return st.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getIDByName(String nume){
        try(Connection con = DBConnection.getConnection()) {
            String sql = "select id from animale where nume=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nume);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getInt(1);
            return -1;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public  static boolean isAnimal(String nume){
        try(Connection con = DBConnection.getConnection()) {
            String sql = "select numar from animale where nume=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nume);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getInt(1) > 0;
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reduceAnimalsNumber(Integer id){
        try(Connection con = DBConnection.getConnection()) {
            String sql = "update animale set numar = numar-1 where id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            st.execute();
            st.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
