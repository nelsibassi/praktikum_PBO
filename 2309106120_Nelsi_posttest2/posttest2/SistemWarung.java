import java.util.*;

class BahanBaku {
    private String id;
    private String nama;
    private int stok;
    private double harga;

    public BahanBaku(String id, String nama, int stok, double harga) {
        this.id = id;
        this.nama = nama;
        this.stok = Math.max(stok, 0);
        this.harga = Math.max(harga, 0);
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
        this.stok = Math.max(stok, 0);
    }

    public void setHarga(double harga) {
        this.harga = Math.max(harga, 0);
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

    public void setKontak(String kontak) {
        this.kontak = kontak;
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

    @Override
    public String toString() {
        return "ID Transaksi: " + idTransaksi + ", ID Bahan: " + idBahan + ", Jumlah: " + jumlah + ", Total Harga: "
                + totalHarga;
    }
}

public class SistemWarung {
    private static ArrayList<BahanBaku> daftarBahan = new ArrayList<>();
    private static ArrayList<Supplier> daftarSupplier = new ArrayList<>();
    private static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== Sistem Pengelolaan Warung Sembako ===");
            System.out.println("1. Tambah Bahan Baku");
            System.out.println("2. Hapus Bahan Baku");
            System.out.println("3. Tambah Supplier");
            System.out.println("4. Hapus Supplier");
            System.out.println("5. Tambah Transaksi");
            System.out.println("6. Tampilkan Semua Data");
            System.out.println("7. Cari Supplier");
            System.out.println("8. Cari Transaksi");
            System.out.println("9. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahBahan();
                case 2 -> hapusBahan();
                case 3 -> tambahSupplier();
                case 4 -> hapusSupplier();
                case 5 -> tambahTransaksi();
                case 6 -> tampilkanSemuaData();
                case 7 -> cariSupplier();
                case 8 -> cariTransaksi();
                case 9 -> System.out.println("Keluar dari program...");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 9);
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

    private static void hapusBahan() {
        System.out.print("Masukkan ID Bahan yang ingin dihapus: ");
        String id = scanner.nextLine();
        daftarBahan.removeIf(bahan -> bahan.getId().equals(id));
        System.out.println("Bahan berhasil dihapus.");
    }

    private static void tambahSupplier() {
        System.out.print("Masukkan ID Supplier: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Supplier: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Kontak: ");
        String kontak = scanner.nextLine();

        daftarSupplier.add(new Supplier(id, nama, kontak));
        System.out.println("Supplier Berhasil Ditambahkan!");
    }

    private static void hapusSupplier() {
        System.out.print("Masukkan ID Supplier yang ingin dihapus: ");
        String id = scanner.nextLine();
        daftarSupplier.removeIf(supplier -> supplier.getIdSupplier().equals(id));
        System.out.println("Supplier berhasil dihapus.");
    }

    private static void tambahTransaksi() {
        System.out.print("Masukkan ID Transaksi: ");
        String idTransaksi = scanner.nextLine();
        System.out.print("Masukkan ID Bahan: ");
        String idBahan = scanner.nextLine();
        System.out.print("Masukkan Jumlah: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();

        daftarTransaksi.add(new Transaksi(idTransaksi, idBahan, jumlah, 0));
        System.out.println("Transaksi Berhasil Ditambahkan!");
    }

    private static void tampilkanSemuaData() {
        daftarBahan.forEach(System.out::println);
        daftarSupplier.forEach(System.out::println);
        daftarTransaksi.forEach(System.out::println);
    }

    private static void cariSupplier() {
        System.out.print("Masukkan ID Supplier: ");
        String id = scanner.nextLine();
        daftarSupplier.stream().filter(s -> s.getIdSupplier().equals(id)).forEach(System.out::println);
    }

    private static void cariTransaksi() {
        System.out.print("Masukkan ID Transaksi: ");
        String id = scanner.nextLine();
        daftarTransaksi.stream().filter(t -> t.toString().contains(id)).forEach(System.out::println);
    }
}
