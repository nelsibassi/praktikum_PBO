import java.util.ArrayList;
import java.util.Scanner;

class BahanBaku {
    String nama;
    int stok;
    double harga;

    public BahanBaku(String nama, int stok, double harga) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    public void updateStok(int jumlah) {
        this.stok += jumlah;
    }
}

class Supplier {
    String nama;
    String kontak;

    public Supplier(String nama, String kontak) {
        this.nama = nama;
        this.kontak = kontak;
    }
}

class Transaksi {
    String jenis; // "Pembelian" atau "Penjualan"
    String namaBahan;
    int jumlah;
    double totalHarga;

    public Transaksi(String jenis, String namaBahan, int jumlah, double totalHarga) {
        this.jenis = jenis;
        this.namaBahan = namaBahan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
    }
}

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<BahanBaku> bahanBakuList = new ArrayList<>();
    static ArrayList<Supplier> supplierList = new ArrayList<>();
    static ArrayList<Transaksi> transaksiList = new ArrayList<>();

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== Sistem Pengelolaan Warung Sembako ===");
            System.out.println("1. Tambah Bahan Baku");
            System.out.println("2. Tampilkan Bahan Baku");
            System.out.println("3. Tambah Supplier");
            System.out.println("4. Tampilkan Supplier");
            System.out.println("5. Tambah Transaksi");
            System.out.println("6. Tampilkan Transaksi");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahBahanBaku();
                    break;
                case 2:
                    tampilkanBahanBaku();
                    break;
                case 3:
                    tambahSupplier();
                    break;
                case 4:
                    tampilkanSupplier();
                    break;
                case 5:
                    tambahTransaksi();
                    break;
                case 6:
                    tampilkanTransaksi();
                    break;
                case 7:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 7);
    }

    public static void tambahBahanBaku() {
        System.out.print("Masukkan nama bahan baku: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan stok: ");
        int stok = scanner.nextInt();
        System.out.print("Masukkan harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();
        bahanBakuList.add(new BahanBaku(nama, stok, harga));
        System.out.println("Bahan baku berhasil ditambahkan!");
    }

    public static void tampilkanBahanBaku() {
        System.out.println("\n=== Daftar Bahan Baku ===");
        for (BahanBaku bahan : bahanBakuList) {
            System.out.println("Nama: " + bahan.nama + ", Stok: " + bahan.stok + ", Harga: Rp" + bahan.harga);
        }
    }

    public static void tambahSupplier() {
        System.out.print("Masukkan nama supplier: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan kontak supplier: ");
        String kontak = scanner.nextLine();
        supplierList.add(new Supplier(nama, kontak));
        System.out.println("Supplier berhasil ditambahkan!");
    }

    public static void tampilkanSupplier() {
        System.out.println("\n=== Daftar Supplier ===");
        for (Supplier supplier : supplierList) {
            System.out.println("Nama: " + supplier.nama + ", Kontak: " + supplier.kontak);
        }
    }

    public static void tambahTransaksi() {
        System.out.println("\nPilih jenis transaksi: 1. Pembelian, 2. Penjualan");
        int jenisTransaksi = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Masukkan nama bahan baku: ");
        String namaBahan = scanner.nextLine();
        System.out.print("Masukkan jumlah: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        BahanBaku bahanDitemukan = null;
        for (BahanBaku bahan : bahanBakuList) {
            if (bahan.nama.equalsIgnoreCase(namaBahan)) {
                bahanDitemukan = bahan;
                break;
            }
        }

        if (bahanDitemukan == null) {
            System.out.println("Bahan baku tidak ditemukan!");
            return;
        }

        double totalHarga = jumlah * bahanDitemukan.harga;
        String jenis = (jenisTransaksi == 1) ? "Pembelian" : "Penjualan";

        if (jenis.equals("Penjualan") && bahanDitemukan.stok < jumlah) {
            System.out.println("Stok tidak mencukupi untuk penjualan!");
            return;
        }

        bahanDitemukan.updateStok(jenis.equals("Pembelian") ? jumlah : -jumlah);
        transaksiList.add(new Transaksi(jenis, namaBahan, jumlah, totalHarga));
        System.out.println("Transaksi berhasil dicatat!");
    }

    public static void tampilkanTransaksi() {
        System.out.println("\n=== Daftar Transaksi ===");
        for (Transaksi transaksi : transaksiList) {
            System.out.println(transaksi.jenis + " - Bahan: " + transaksi.namaBahan + ", Jumlah: " + transaksi.jumlah
                    + ", Total Harga: Rp" + transaksi.totalHarga);
        }
    }
}
