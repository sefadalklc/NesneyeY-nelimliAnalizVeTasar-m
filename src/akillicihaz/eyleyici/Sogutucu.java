package akillicihaz.eyleyici;

public class Sogutucu implements Eyleyici {

    SogutucuDurumu sogutucuDurumu = SogutucuDurumu.KAPALI;

    @Override
    public boolean sogutucuAc() {
        if (sogutucuDurumu.equals(SogutucuDurumu.KAPALI)) {
            sogutucuDurumu = SogutucuDurumu.ACIK;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean sogutucuKapat() {
        if (sogutucuDurumu.equals(SogutucuDurumu.ACIK)) {
            sogutucuDurumu = SogutucuDurumu.KAPALI;
            return true;
        } else {
            return false;
        }
    }
}

