package akillicihaz.db;

import akillicihaz.gui.KullaniciBilgileri;

public interface DbArayuzu {

    public boolean baglantiAc();

    public boolean baglantiKapat();

    public boolean yeniKayitOlustur(KullaniciBilgileri kullaniciBilgileri);

    public String parolaGetir(String kullaniciAdi);

}
