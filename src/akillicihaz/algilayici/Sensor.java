package akillicihaz.algilayici;

import java.util.Random;

public class Sensor implements Algilayici {

    Random rnd = new Random();

    public Sensor(){

    }

    @Override
    public int sicaklikOku() {
        // 0 - 80 arasi deger uretir, +20 ile bu 20-100 arasi olur.
        return rnd.nextInt(80) + 20;
    }
}
