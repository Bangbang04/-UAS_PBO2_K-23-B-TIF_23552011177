
package com.mycompany.kasir;

public class DetailPesanan {
    private int id;
    private Menu menu;
    private int jumlah;

    public DetailPesanan(int id, Menu menu, int jumlah) {
        this.id = id;
        this.menu = menu;
        this.jumlah = jumlah;
    }

    public int getId() { return id; }
    public Menu getMenu() { return menu; }
    public int getJumlah() { return jumlah; }
    public double getSubTotal() { return menu.hitungHarga(jumlah); }
}
