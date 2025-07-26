
# Final Proyek Pemrograman Berorientasi Obyek 2
<ul>
  <li>Mata Kuliah: Pemrograman Berorientasi Obyek 2</li>
  <li>Dosen Pengampu: <a href="https://github.com/Muhammad-Ikhwan-Fathulloh">Muhammad Ikhwan Fathulloh</a></li>
</ul>

## Profil
<ul>
  <li>Nama: Bangbang Ramadhan </li>
  <li>NIM: 23552011177 </li>
  <li>Studi Kasus: Kasir Restoran</li>
</ul>

## Judul Studi Kasus
<p>Kasir Restoran.</p>

## Penjelasan Studi Kasus
<p>Sebuah restoran ingin mengelola proses pemesanan dan pembayaran makanan/minuman secara terkomputerisasi. Seorang kasir akan:
- Melihat daftar menu (makanan & minuman)
- Mencatat pesanan pelanggan (berapa meja, apa saja yang dipesan, berapa jumlahnya)
- Sistem akan menghitung total yang harus dibayarkan
- Seluruh pesanan tersimpan dalam database agar mudah diolah atau direkap.</p>

## Penjelasan 4 Pilar OOP dalam Studi Kasus

### 1. Inheritance
<p>Inheritance adalah konsep turunan dari satu kelas ke kelas lain.

Implementasi di Studi Kasus:
- "Kelas ItemMakanan" adalah kelas abstrak yang menjadi dasar untuk menu apapun.
- "Kelas Menu" mewarisi (extends) ItemMakanan, sehingga otomatis punya properti dan method dari ItemMakanan.
- "Kelas Makanan" dan "Minuman" mewarisi kelas Menu.
  Contoh Kodenya 
public abstract class ItemMakanan {}
public class Menu extends ItemMakanan {}
public class Makanan extends Menu {}
public class Minuman extends Menu {}.</p>

### 2. Encapsulation
<p>Encapsulation adalah membungkus data dan method dalam satu unit (class), serta membatasi akses langsung ke data.

Implementasi di Studi Kasus:
- Semua data pesanan, menu, dan detail pesanan di-private atau protected.
- Akses data dilakukan melalui getter/setter (misal: getNama(), getHarga()).
- Data pesanan pelanggan (meja, status, daftar pesanan) tidak bisa diubah sembarangan dari luar class.
  Contoh Kodenya:
  private Menu menu;
  private int jumlah;
  private int id;
  private int meja;
  private String status;
  private ArrayList<DetailPesanan> detailPesananList = new ArrayList<>();.</p>

### 3. Polymorphism
<p>Polymorphism adalah kemampuan objek untuk memiliki banyak bentuk, terutama pada method yang sama tapi perilaku berbeda.

Implementasi di Studi Kasus:
- Method hitungHarga(int jumlah) didefinisikan di kelas abstrak ItemMakanan, lalu di-override di kelas anak (Menu).
- Jika nanti ada diskon khusus untuk minuman atau makanan tertentu, method ini bisa di-override lagi di kelas anak tanpa mengubah kode di kelas induk.
  Contoh Kodenya
  public abstract double hitungHarga(int jumlah); // di ItemMakanan
@Override
public double hitungHarga(int jumlah) { // di Menu
    return harga * jumlah;
}.</p>

### 4. Abstract
<p>Abstraction adalah menyembunyikan detail implementasi dan hanya menampilkan fitur penting ke pengguna.

Implementasi di Studi Kasus:
- ItemMakanan adalah kelas abstrak yang tidak bisa diinstansiasi langsung, hanya berfungsi sebagai cetakan dasar bagi menu-menu restoran.
- Kelas abstrak ini mendefinisikan method abstract hitungHarga, sehingga kelas turunannya wajib mengimplementasikannya.
Contoh Kodenya
public abstract class ItemMakanan {
    public abstract double hitungHarga(int jumlah);
}.</p>


## Demo Proyek
<ul>
  <li>Github: <a href="">https://github.com/Bangbang04/-UAS_PBO2_K-23-B-TIF_23552011177</a></li>
  <li>Youtube: <a href="">https://youtu.be/_WWS4mCiNpM?si=hRTGugqLrOU0rIN2</a></li>
</ul>
