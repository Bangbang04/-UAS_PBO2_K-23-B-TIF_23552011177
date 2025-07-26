
package com.mycompany.kasir;

import java.util.ArrayList;
import java.util.List;

public class Pesanan {
    private int id;
    private String meja;
    private String status;
    private List<DetailPesanan> detailList;

    public Pesanan(int id, String meja, String status) {
        this.id = id;
        this.meja = meja;
        this.status = status;
        this.detailList = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getMeja() { return meja; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<DetailPesanan> getDetailList() { return detailList; }

    public void tambahDetail(DetailPesanan dp) {
        detailList.add(dp);
    }

    public double hitungTotal() {
        double total = 0;
        for (DetailPesanan dp : detailList) {
            total += dp.getSubTotal();
        }
        return total;
    }
}
