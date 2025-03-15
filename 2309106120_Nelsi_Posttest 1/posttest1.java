import java.util.*;

class BahanBaku {
    private String id;
    private String nama;
    private int stok;
    private double harga;

    public BahanBaku(String id, String nama, int stok, double harga) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    public double getHarga() {
        return harga;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Stok: " + stok + ", Harga: " + harga;
    }
}

class Supplier {
    private String idSupplier;
    private String namaSupplier;
    private String kontak;

    public Supplier(String idSupplier, String namaSupplier, String kontak) {
        this.idSupplier = idSupplier;
        this.namaSupplier = namaSupplier;
        this.kontak = kontak;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public String getKontak() {
        return kontak;
    }

    @Override
    public String toString() {
        return "ID Supplier: " + idSupplier + ", Nama: " + namaSupplier + ", Kontak: " + kontak;
    }
}

class Transaksi {
    private String idTransaksi;
    private String idBahan;
    private int jumlah;
    private double totalHarga;

    public Transaksi(String idTransaksi, String idBahan, int jumlah, double totalHarga) {
        this.idTransaksi = idTransaksi;
        this.idBahan = idBahan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getIdBahan() {
        return idBahan;
    }

    @Override
    public String toString() {
        return "ID Transaksi: " + idTransaksi + ", ID Bahan: " + idBahan + ", Jumlah: " + jumlah + ", Total Harga: "
                + totalHarga;
    }
}

public class Transaksi {
    private static ArrayList<BahanBaku> daftarBahan = new ArrayList<>();
    private static ArrayList<Supplier> daftarSupplier = new ArrayList<>();
    private static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

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
                    tambahBahan();
                    break;
                case 2:
                    tampilkanBahan();
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
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 7);
    }

    private static void tambahBahan() {
        System.out.print("Masukkan ID Bahan: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Bahan: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Stok: ");
        int stok = scanner.nextInt();
        System.out.print("Masukkan Harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();

        daftarBahan.add(new BahanBaku(id, nama, stok, harga));
        System.out.println("Bahan Baku Berhasil Ditambahkan!");
    }

    private static void tampilkanBahan() {
        if (daftarBahan.isEmpty())
            System.out.println("Tidak ada data bahan baku.");
        else
            daftarBahan.forEach(System.out::println);
    }

    private static void tambahSupplier() {
        System.out.print("Masukkan ID Supplier: ");
        String idSupplier = scanner.nextLine();
        System.out.print("Masukkan Nama Supplier: ");
        String namaSupplier = scanner.nextLine();
        System.out.print("Masukkan Kontak: ");
        String kontak = scanner.nextLine();

        daftarSupplier.add(new Supplier(idSupplier, namaSupplier, kontak));
        System.out.println("Supplier Berhasil Ditambahkan!");
    }

    private static void tampilkanSupplier() {
        if (daftarSupplier.isEmpty())
            System.out.println("Tidak ada data supplier.");
        else
            daftarSupplier.forEach(System.out::println);
    }

    private static void tambahTransaksi() {
        System.out.print("Masukkan ID Transaksi: ");
        String idTransaksi = scanner.nextLine();
        System.out.print("Masukkan ID Bahan: ");
        String idBahan = scanner.nextLine();
        System.out.print("Masukkan Jumlah: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        for (BahanBaku bahan : daftarBahan) {
            if (bahan.getId().equals(idBahan) && bahan.getStok() >= jumlah) {
                double totalHarga = jumlah * bahan.getHarga();
                bahan.setStok(bahan.getStok() - jumlah);
                daftarTransaksi.add(new Transaksi(idTransaksi, idBahan, jumlah, totalHarga));
                System.out.println("Transaksi Berhasil Ditambahkan!");
                return;
            }
        }
        System.out.println("ID Bahan tidak ditemukan atau stok tidak mencukupi.");
    }

    private static void tampilkanTransaksi() {
        if (daftarTransaksi.isEmpty())
            System.out.println("Tidak ada data transaksi.");
        else
            daftarTransaksi.forEach(System.out::println);
    }
}
