
package com.mycompany.kasir;

public class Minuman extends Menu {
    public Minuman(int id, String nama, double harga) {
        super(id, nama, harga);
    }
    @Override
    public double hitungHarga(int jumlah) {
        // Diskon 10% jika beli lebih dari 3
        if (jumlah > 3) return harga * jumlah * 0.9;
        return harga * jumlah;
    }
}
