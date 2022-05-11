package akillicihaz.gui;

import java.util.Scanner;

public class KullaniciArayuzu {

    Scanner scan = new Scanner(System.in);

    public int anaMenuGoster() {
        System.out.println("Hosgeldiniz.Numaralardan birini tuslayiniz");
        System.out.println("1....Sisteme kayıt olmak icin");
        System.out.println("2....Sisteme giris yapmak icin");
        System.out.println("3....Cihazı sonlandırmak icin");
        return scan.nextInt();
    }

    public KullaniciBilgileri kayitOl() {
        return kullaniciBilgileriAl();
    }

    public KullaniciBilgileri girisYap() {
        return kullaniciBilgileriAl();
    }

    private KullaniciBilgileri kullaniciBilgileriAl() {
        scan = new Scanner(System.in);
        System.out.println("Kullanici adi giriniz:");
        String kullaniciAdi = scan.nextLine();
        System.out.println("Parola giriniz:");
        String parola = scan.nextLine();
        return new KullaniciBilgileri(kullaniciAdi, parola);
    }

    public int komutVer() {
        System.out.println("Komutlar");
        System.out.println("1.Sicakligi Goruntule");
        System.out.println("2.Sogutucuyu Ac");
        System.out.println("3.Sogutucuyu Kapat");
        System.out.println("4.Oturumdan Cikis Yap");
        return scan.nextInt();
    }
}
