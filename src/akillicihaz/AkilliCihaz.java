package akillicihaz;

import akillicihaz.algilayici.Algilayici;
import akillicihaz.algilayici.Sensor;
import akillicihaz.eyleyici.Eyleyici;
import akillicihaz.eyleyici.Sogutucu;
import akillicihaz.gui.KullaniciArayuzu;
import akillicihaz.gui.KullaniciBilgileri;

public class AkilliCihaz {

    private static AkilliCihaz single_instance = null;

    AkilliCihazDurumu akilliCihazDurumu = AkilliCihazDurumu.KAPALI;
    KullaniciArayuzu kullaniciArayuzu;
    MerkeziIslemBirimi merkeziIslemBirimi;
    Eyleyici eyleyici;
    Algilayici algilayici;

    private AkilliCihaz() {
        initialize();
    }

    public static AkilliCihaz getInstance() {
        if (single_instance == null)
            single_instance = new AkilliCihaz();

        return single_instance;
    }

    public void start() {
        if (akilliCihazDurumu.equals(AkilliCihazDurumu.KAPALI)) {
            akilliCihazDurumu = AkilliCihazDurumu.BEKLEME;
        } else {
            return;
        }
        process();
    }

    private void initialize() {
        kullaniciArayuzu = new KullaniciArayuzu();
        eyleyici = new Sogutucu();
        algilayici = new Sensor();
        merkeziIslemBirimi = new MerkeziIslemBirimi(akilliCihazDurumu, eyleyici, algilayici);
    }

    private void process() {
        while (true) {

            int secenek = kullaniciArayuzu.anaMenuGoster();
            switch (secenek) {
                case 1:
                    KullaniciBilgileri kullaniciKayitBilgileri = kullaniciArayuzu.kayitOl();
                    if (merkeziIslemBirimi.kayitEt(kullaniciKayitBilgileri)) {
                        System.out.println("Kayit Basarili..");
                    } else {
                        System.out.println("Kayit Basarisiz..");
                    }
                    break;
                case 2:
                    KullaniciBilgileri kullaniciGirisBilgileri = kullaniciArayuzu.girisYap();
                    if (merkeziIslemBirimi.girisYap(kullaniciGirisBilgileri)) {
                        System.out.println("Giris Basarili..");
                        komutIslemeKontrolu();

                    } else {
                        System.out.println("Giris Basarisiz..");
                    }
                    break;
                case 3:
                    System.out.println("Akilli Cihazdan Çıkış Yapılıyor..");
                    return;
            }

        }
    }

    private void komutIslemeKontrolu() {
        while (true) {
            int komut = kullaniciArayuzu.komutVer();
            if (komut == 4) {
                System.out.println("Oturumdan cikis yapildi");
                break;
            }
            merkeziIslemBirimi.komutCalistir(komut);
        }
    }
}
