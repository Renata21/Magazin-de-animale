package magazin_animale.database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import static magazin_animale.database.DBDataAnimale.getIDByName;
import static magazin_animale.database.DBDataAnimale.reduceAnimalsNumber;

public class DBDataVanzare {
    static DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][]{},
            new Object[]{"id", "Nume Prenume Client", "Telefon Client", "Data Vanzarii", "Animal"}
    );

    public static void loadDataVanzare(JTable tabel) {
        try (Connection con = DBConnection.getConnection()) {
            Statement st = con.createStatement();
            ResultSet response = st.executeQuery(
                    "select vanzare.id, nume_prenume_client, tel_client, data_vanzarii,a.nume as nume_animal " +
                            "from vanzare inner join animale a on a.id = vanzare.animal order by id"
            );

            tableModel.setRowCount(0);

            while (response.next()) {
                tableModel.addRow(new Object[]{
                        response.getString("id"),
                        response.getString("nume_prenume_client"),
                        response.getString("tel_client"),
                        response.getString("data_vanzarii"),
                        response.getString("nume_animal")});
            }
            tabel.setModel(tableModel);

            response.close();
            st.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addVanzare(String animal, String data_vanzarii, String numeClient, String telClient) {
        try (Connection con = DBConnection.getConnection()) {
            Integer animalID = getIDByName(animal);

            if (animalID != -1) {
                String sql = "insert into vanzare(animal, data_vanzarii, nume_prenume_client, tel_client) values(?,?,?,?)";
                PreparedStatement st = con.prepareStatement(sql);
                st.setInt(1, animalID);
                st.setDate(2, Date.valueOf(data_vanzarii));
                st.setString(3, numeClient);
                st.setString(4, telClient);

                st.execute();
                st.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
