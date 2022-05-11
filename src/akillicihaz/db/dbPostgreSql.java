package akillicihaz.db;

import akillicihaz.gui.KullaniciBilgileri;

import java.sql.*;

public class dbPostgreSql implements DbArayuzu {

    //jdbc:postgresql://localhost:5432/kullanicilar
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "123456";

    Connection connection;

    @Override
    public boolean baglantiAc() {

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean baglantiKapat() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private final String KULLANICILAR = "kullanicilar";
    private final String PAROLA = "parola";

    @Override
    public boolean yeniKayitOlustur(KullaniciBilgileri kullaniciBilgileri) {
        String kullaniciAdi = kullaniciBilgileri.getKullaniciAdi();
        String parola = kullaniciBilgileri.getParola();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sorgu = String.format("INSERT INTO kullanicilar (kullaniciadi,parola) VALUES('%s','%s');", kullaniciAdi, parola);
            statement.executeUpdate(sorgu);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String parolaGetir(String kullaniciAdi) {

        String parola = null;
        Statement statement = null;

        try {
            statement = connection.createStatement();
            String query = String.format("SELECT parola FROM kullanicilar WHERE kullaniciadi='%s'", kullaniciAdi);
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                parola = result.getString("parola");
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return parola;
    }
}
