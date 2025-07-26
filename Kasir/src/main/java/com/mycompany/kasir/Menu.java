
package com.mycompany.kasir;

public abstract class Menu {
    protected int id;
    protected String nama;
    protected double harga;

    public Menu(int id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public abstract double hitungHarga(int jumlah);

    @Override
    public String toString() {
        return nama + " (Rp " + harga + ")";
    }
}
