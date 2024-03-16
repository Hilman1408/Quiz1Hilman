package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNamaPelanggan, etKodeBarang, etJumlahBarang;
    private RadioGroup radioGroup;
    private Button btnProses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNamaPelanggan = findViewById(R.id.namapelanggan);
        etKodeBarang = findViewById(R.id.kodebarang);
        etJumlahBarang = findViewById(R.id.jumlahbarang);
        radioGroup = findViewById(R.id.radiogroup);
        btnProses = findViewById(R.id.button);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesTransaksi();
            }
        });
    }

    private void prosesTransaksi() {
        String namaPelanggan = etNamaPelanggan.getText().toString();
        String kodeBarang = etKodeBarang.getText().toString();

        // Dapatkan ID RadioButton yang dipilih
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        // Cek apakah RadioButton dipilih
        if (selectedRadioButtonId == -1) {
            Toast.makeText(MainActivity.this, "Pilih tipe pelanggan terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Dapatkan RadioButton yang dipilih
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String tipePelanggan = selectedRadioButton.getText().toString(); // Ambil teks dari RadioButton yang dipilih

        int jumlahBarang = Integer.parseInt(etJumlahBarang.getText().toString());

        long harga = getHarga(kodeBarang);
        if (harga == -1) {
            Toast.makeText(MainActivity.this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        long totalHarga = harga * jumlahBarang;
        long diskonHarga = hitungDiskonHarga(totalHarga);
        long diskonMember = hitungDiskonMember(totalHarga);

        long jumlahBayar = totalHarga - diskonHarga - diskonMember;

        Intent intent = new Intent(MainActivity.this, detail.class);
        intent.putExtra("Nama Pelanggan", namaPelanggan);
        intent.putExtra("Selamat Datang", namaPelanggan);
        intent.putExtra("Kode Barang", kodeBarang);
        intent.putExtra("Tipe Pelanggan", tipePelanggan); // Setel tipe pelanggan sesuai dengan pilihan
        intent.putExtra("Nama Barang", getNamaBarang(kodeBarang));
        intent.putExtra("Harga", harga);
        intent.putExtra("Jumlah Barang", jumlahBarang);
        intent.putExtra("Total Harga", totalHarga);
        intent.putExtra("Diskon Harga", diskonHarga);
        intent.putExtra("Diskon Member", diskonMember);
        intent.putExtra("Jumlah Bayar", jumlahBayar);
        startActivity(intent);
    }

    private long getHarga(String kodeBarang) {
        switch (kodeBarang) {
            case "IPX":
                return 5725300;
            case "PCO":
                return 2730551;
            case "LV3":
                return 6666666;
            default:
                return -1;
        }
    }

    private String getNamaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "IPX":
                return "Iphone X";
            case "PCO":
                return "POCO M3";
            case "LV3":
                return "Lenovo V14 Gen 3";
            default:
                return "";
        }
    }

    private long hitungDiskonHarga(long totalHarga) {
        if (totalHarga > 10000000) {
            return 100000;
        }
        return 0;
    }

    private long hitungDiskonMember(long totalHarga) {
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String membership = radioButton.getText().toString();
        switch (membership) {
            case "Gold":
                return (long) (totalHarga * 0.30);
            case "Silver":
                return (long) (totalHarga * 0.20);
            case "Biasa":
                return (long) (totalHarga * 0.10);
            default:
                return 0;
        }
    }
}