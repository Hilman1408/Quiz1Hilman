package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String namaPelanggan = intent.getStringExtra("Selamat Datang");
        String tipePelanggan = intent.getStringExtra("Tipe Pelanggan");
        TextView textViewTipePelanggan = findViewById(R.id.TipeBarang);
        textViewTipePelanggan.setText("Tipe Pelanggan: " + tipePelanggan);
        String judultipepelanggan = intent.getStringExtra("Tipe Barang");
        String kodeBarang = intent.getStringExtra("Kode Barang");
        String namaBarang = intent.getStringExtra("Nama Barang");
        long harga = intent.getLongExtra("Harga", 0);
        int jumlahBarang = intent.getIntExtra("Jumlah Barang", 0);
        long totalHarga = intent.getLongExtra("Total Harga", 0);
        long diskonHarga = intent.getLongExtra("Diskon Harga", 0);
        long diskonMember = intent.getLongExtra("Diskon Member", 0);
        long jumlahBayar = intent.getLongExtra("Jumlah Bayar", 0);

        // Menetapkan nilai-nilai ke TextView di activity_detail.xml
        TextView textViewNama = findViewById(R.id.Nama);
        TextView textViewKodeBarang = findViewById(R.id.KodeBarang);
        TextView textViewNamaBarang = findViewById(R.id.NamaBarang);
        TextView textViewHarga = findViewById(R.id.Harga);
        TextView textViewTotalHarga = findViewById(R.id.TotalHarga);
        TextView textViewDiskonHarga = findViewById(R.id.DiscoundHarga);
        TextView textViewDiskonMember = findViewById(R.id.DiscounMember);
        TextView textViewJumlahBayar = findViewById(R.id.JumlahBayar);

        textViewNama.setText("Nama Pelanggan: " + namaPelanggan);
        textViewKodeBarang.setText("Kode Barang: " + kodeBarang);
        textViewNamaBarang.setText("Nama Barang: " + namaBarang);
        textViewHarga.setText("Harga: " + harga);
        textViewTotalHarga.setText("Total Harga: " + totalHarga);
        textViewDiskonHarga.setText("Diskon Harga: " + diskonHarga);
        textViewDiskonMember.setText("Diskon Member: " + diskonMember);
        textViewJumlahBayar.setText("Jumlah Bayar: " + jumlahBayar);

        // Menampilkan ciri-ciri setiap barang berdasarkan kode barang
        TextView textViewCiri = findViewById(R.id.CiriBarang);
        String ciriBarang = getCiriBarang(kodeBarang);
        textViewCiri.setText("Ciri Barang: " + ciriBarang);
        // Mendapatkan referensi ke tombol "Share"
        Button buttonShare = findViewById(R.id.share);

        // Menambahkan listener onClick untuk tombol "Share"
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buat pesan yang ingin Anda bagikan
                String message = "Halo, saya ingin berbagi detail pembelian:\n"
                        + "Nama Pelanggan: " + namaPelanggan + "\n"
                        + "Nama Barang: " + namaBarang + "\n"
                        + "Jumlah Bayar: " + jumlahBayar;

                // Buat Intent untuk membagikan pesan
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, message);

                // Cek apakah ada aplikasi yang bisa menangani Intent
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    // Start aktivitas untuk membagikan pesan
                    startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"));
                } else {
                    // Jika tidak ada aplikasi yang bisa menangani Intent
                    Toast.makeText(detail.this, "Tidak ada aplikasi yang tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String getCiriBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "IPX":
                return "Smartphone high-end dengan kamera canggih dan desain premium.";
            case "PCO":
                return "Smartphone dengan performa tangguh dan baterai tahan lama.";
            case "LV3":
                return "Laptop dengan desain stylish dan performa handal untuk produktivitas sehari-hari.";
            default:
                return "";
        }
    }
}
