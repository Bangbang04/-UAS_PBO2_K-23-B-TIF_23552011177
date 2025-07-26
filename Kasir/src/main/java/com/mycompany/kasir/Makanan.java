
package com.mycompany.kasir;

public class Makanan extends Menu {
    public Makanan(int id, String nama, double harga) {
        super(id, nama, harga);
    }
    @Override
    public double hitungHarga(int jumlah) {
        return harga * jumlah;
    }
}
