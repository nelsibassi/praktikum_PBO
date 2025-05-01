import java.util.*;

abstract class Barang {
    protected String id;
    protected String nama;

    public Barang(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public abstract void infoBarang();
}

class BahanBaku extends Barang {
    private int stok;
    private double harga;

    public BahanBaku(String id, String nama, int stok, double harga) {
        super(id, nama);
        this.stok = Math.max(stok, 0);
        this.harga = Math.max(harga, 0);
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

    @Override
    public void infoBarang() {
        System.out.println("Bahan Baku: " + nama + " (ID: " + id + ")");
    }
}

abstract class Orang {
    protected String nama;
    protected String kontak;

    public Orang(String nama, String kontak) {
        this.nama = nama;
        this.kontak = kontak;
    }

    public String getNama() {
        return nama;
    }

    public String getKontak() {
        return kontak;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public abstract void tampilkanInfo();
}

class Supplier extends Orang {
    private String idSupplier;

    public Supplier(String idSupplier, String nama, String kontak) {
        super(nama, kontak);
        this.idSupplier = idSupplier;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    @Override
    public String toString() {
        return "ID Supplier: " + idSupplier + ", Nama: " + nama + ", Kontak: " + kontak;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Supplier: " + nama + " (ID: " + idSupplier + ")");
    }
}

final class Transaksi {
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
        return "ID Transaksi: " + idTransaksi + ", ID Bahan: " + idBahan +
                ", Jumlah: " + jumlah + ", Total Harga: " + totalHarga;
    }
}

public class SistemWarung {
    private static final ArrayList<BahanBaku> daftarBahan = new ArrayList<>();
    private static final ArrayList<Supplier> daftarSupplier = new ArrayList<>();
    private static final ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== Sistem Pengelolaan Warung Sembako ===");
            System.out.println("1. Tambah Bahan Baku");
            System.out.println("2. Hapus Bahan Baku");
            System.out.println("3. Edit Bahan Baku");
            System.out.println("4. Tambah Supplier");
            System.out.println("5. Hapus Supplier");
            System.out.println("6. Edit Supplier");
            System.out.println("7. Tambah Transaksi");
            System.out.println("8. Tampilkan Semua Data");
            System.out.println("9. Cari Supplier");
            System.out.println("10. Cari Transaksi");
            System.out.println("11. Urutkan Bahan Baku");
            System.out.println("12. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> tambahBahan();
                case 2 -> hapusBahan();
                case 3 -> editBahan();
                case 4 -> tambahSupplier();
                case 5 -> hapusSupplier();
                case 6 -> editSupplier();
                case 7 -> tambahTransaksi();
                case 8 -> tampilkanSemuaData();
                case 9 -> cariSupplier();
                case 10 -> cariTransaksi();
                case 11 -> urutkanBahan();
                case 12 -> System.out.println("Keluar dari program...");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 12);
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
        boolean removed = daftarBahan.removeIf(bahan -> bahan.getId().equals(id));
        if (removed) {
            System.out.println("Bahan berhasil dihapus.");
        } else {
            System.out.println("ID tidak ditemukan.");
        }
    }

    private static void editBahan() {
        System.out.print("Masukkan ID Bahan yang ingin diedit: ");
        String id = scanner.nextLine();
        for (BahanBaku bahan : daftarBahan) {
            if (bahan.getId().equals(id)) {
                System.out.print("Masukkan Nama Baru: ");
                String namaBaru = scanner.nextLine();
                System.out.print("Masukkan Stok Baru: ");
                int stokBaru = scanner.nextInt();
                System.out.print("Masukkan Harga Baru: ");
                double hargaBaru = scanner.nextDouble();
                scanner.nextLine();
                bahan.setNama(namaBaru);
                bahan.setStok(stokBaru);
                bahan.setHarga(hargaBaru);
                System.out.println("Data Bahan Berhasil Diubah!");
                return;
            }
        }
        System.out.println("Bahan tidak ditemukan.");
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
        boolean removed = daftarSupplier.removeIf(supplier -> supplier.getIdSupplier().equals(id));
        if (removed) {
            System.out.println("Supplier berhasil dihapus.");
        } else {
            System.out.println("ID tidak ditemukan.");
        }
    }

    private static void editSupplier() {
        System.out.print("Masukkan ID Supplier yang ingin diedit: ");
        String id = scanner.nextLine();
        for (Supplier supplier : daftarSupplier) {
            if (supplier.getIdSupplier().equals(id)) {
                System.out.print("Masukkan Nama Baru: ");
                String namaBaru = scanner.nextLine();
                System.out.print("Masukkan Kontak Baru: ");
                String kontakBaru = scanner.nextLine();
                supplier.setNama(namaBaru);
                supplier.setKontak(kontakBaru);
                System.out.println("Data Supplier Berhasil Diubah!");
                return;
            }
        }
        System.out.println("Supplier tidak ditemukan.");
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
            if (bahan.getId().equals(idBahan)) {
                double total = bahan.getHarga() * jumlah;
                daftarTransaksi.add(new Transaksi(idTransaksi, idBahan, jumlah, total));
                System.out.println("Transaksi Berhasil Ditambahkan!");
                return;
            }
        }
        System.out.println("ID Bahan tidak ditemukan.");
    }

    private final static void tampilkanSemuaData() {
        System.out.println("\n== Daftar Bahan Baku ==");
        daftarBahan.forEach(System.out::println);
        System.out.println("\n== Daftar Supplier ==");
        daftarSupplier.forEach(System.out::println);
        System.out.println("\n== Daftar Transaksi ==");
        daftarTransaksi.forEach(System.out::println);
    }

    private static void cariSupplier() {
        System.out.print("Masukkan ID Supplier: ");
        String id = scanner.nextLine();
        daftarSupplier.stream()
                .filter(s -> s.getIdSupplier().equals(id))
                .forEach(System.out::println);
    }

    private static void cariTransaksi() {
        System.out.print("Masukkan ID Transaksi: ");
        String id = scanner.nextLine();
        daftarTransaksi.stream()
                .filter(t -> t.toString().contains(id))
                .forEach(System.out::println);
    }

    private static void urutkanBahan() {
        System.out.println("Urutkan berdasarkan: ");
        System.out.println("1. Nama (A-Z)");
        System.out.println("2. Nama (Z-A)");
        System.out.println("3. Harga (Termurah)");
        System.out.println("4. Harga (Termahal)");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        Comparator<BahanBaku> comparator = switch (pilihan) {
            case 1 -> Comparator.comparing(BahanBaku::getNama);
            case 2 -> Comparator.comparing(BahanBaku::getNama).reversed();
            case 3 -> Comparator.comparingDouble(BahanBaku::getHarga);
            case 4 -> Comparator.comparingDouble(BahanBaku::getHarga).reversed();
            default -> null;
        };

        if (comparator != null) {
            daftarBahan.sort(comparator);
            System.out.println("Bahan Baku setelah diurutkan:");
            daftarBahan.forEach(System.out::println);
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }
}
