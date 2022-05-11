package akillicihaz;

import akillicihaz.algilayici.Algilayici;
import akillicihaz.db.DbArayuzu;
import akillicihaz.db.dbPostgreSql;
import akillicihaz.eyleyici.Eyleyici;
import akillicihaz.gui.KullaniciBilgileri;

public class MerkeziIslemBirimi {

    AkilliCihazDurumu akilliCihazDurumu;
    Eyleyici eyleyici;
    Algilayici algilayici;
    DbArayuzu dbArayuzu;

    public MerkeziIslemBirimi(AkilliCihazDurumu akilliCihazDurumu, Eyleyici eyleyici, Algilayici algilayici) {
        this.akilliCihazDurumu = akilliCihazDurumu;
        this.eyleyici = eyleyici;
        this.algilayici = algilayici;

        dbArayuzu = new dbPostgreSql();
    }

    public boolean kayitEt(KullaniciBilgileri kullaniciBilgileri) {

        boolean isConnectionOpen = dbArayuzu.baglantiAc();
        if (isConnectionOpen) {
            boolean sonuc = dbArayuzu.yeniKayitOlustur(kullaniciBilgileri);
            dbArayuzu.baglantiKapat();
            return sonuc;
        } else {
            return false;
        }
    }

    public boolean girisYap(KullaniciBilgileri kullaniciBilgileri) {
        String kullaniciAdi = kullaniciBilgileri.getKullaniciAdi();
        String parola = kullaniciBilgileri.getParola();

        dbArayuzu.baglantiAc();

        String parola2 = dbArayuzu.parolaGetir(kullaniciAdi);

        dbArayuzu.baglantiKapat();

        if (parola.equals(parola2)) {
            return true;
        } else {
            return false;
        }
    }

    public void komutCalistir(int komut) {

        switch (komut) {
            case 1:
                int sicaklik = algilayici.sicaklikOku();
                System.out.println("Sicaklik: " + sicaklik);
                break;

            case 2:
                if (eyleyici.sogutucuAc()) {
                    System.out.println("Sogutucu Acildi.");
                } else {
                    System.out.println("Sogutucu Zaten Acik!");
                }
                break;

            case 3:
                if (eyleyici.sogutucuKapat()) {
                    System.out.println("Sogutucu Kapatildi.");
                } else {
                    System.out.println("Sogutucu Zaten Kapali!");
                }
                break;
        }
    }
}
