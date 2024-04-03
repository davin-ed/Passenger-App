import java.util.Scanner;

class Driver {
    public String nama;
    public String noSIM;
    public int usia;

    public Driver(String nama, String noSIM, int usia) {
        this.nama = nama;
        this.noSIM = noSIM;
        this.usia = usia;
    }
}

class Passenger {
    public String nama;
    public int usia;

    public Passenger(String nama, int usia) {
        this.nama = nama;
        this.usia = usia;
    }
}

class Kendaraan {
    public String platNomor;
    public int jmlPenumpang;
    public int maxPenumpang;
    public Driver supir;
    public Passenger penumpang;

    public Kendaraan(String platNomor, int maxPenumpang) {
        this.platNomor = platNomor;
        this.jmlPenumpang = 0;
        this.maxPenumpang = maxPenumpang;
    }

    public void cekPenumpang() {
        System.out.println("Penumpang saat ini: " + this.jmlPenumpang);
    }

    public void penumpangNaik(int naik) {
        if (this.jmlPenumpang + naik > this.maxPenumpang) {
            System.out.println("Penumpang melebihi kapasitas");
        }
        else if (naik == 0) {
            System.out.println("Tidak ada penumpang yang naik");
        }
        else {
            this.jmlPenumpang += naik;
            System.out.println("Penumpang berhasil naik");
        }
        cekPenumpang();
        System.out.println();
    }

    public void penumpangTurun(int turun) {
        if (turun > this.jmlPenumpang) {
            System.out.println("Penumpang turun tidak bisa melebihi penumpang saat ini");
        }
        else if (turun == 0) {
            System.out.println("Tidak ada penumpang yang turun");
        }
        else {
            this.jmlPenumpang -= turun;
            System.out.println("Penumpang berhasil turun");
        }
        cekPenumpang();
        System.out.println();
    }

    public void showDriver() {
        if (this.supir != null) {
            System.out.println("Driver anda " + this.supir.nama + " yang berusia " + this.supir.usia + " tahun.");
        }
        else {
            System.out.println("Tidak ada supir yang terdaftar");
        }
    }

    public void showPlat() {
        System.out.println("Anda akan menaiki truk dengan plat nomor " + this.platNomor + ". ");
    }
}

class Truk extends Kendaraan {
    int kapasitasMuatan;

    public Truk(String platNomor, int maxPenumpang) {
        super(platNomor, maxPenumpang);
    }

    public void angkut() {
        System.out.println("Truk ini sedang mengangkut barang dengan berat 5 ton");
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitasMuatan = kapasitas;
        System.out.println("Kapasitas muatan truk ini " + this.kapasitasMuatan + " ton.");
    }
}

class Bus extends Kendaraan {
    boolean isToilet;

    public Bus(String platNomor, int maxPenumpang) {
        super(platNomor, maxPenumpang);
    }

    public void basuri() {
        System.out.println("Bus ini dapat membunyikan basuri.");
    }

    public void setToilet(boolean toilet) {
        if (toilet == true) {
            System.out.println("Bus ini dilengkapi dengan toilet.");
        }
        else {
            System.out.println("Bus ini tidak dilengkapi dengan toilet.");
        }
    }
}

public class PassengerMain {
    public static void main(String[] args) throws Exception {
        try (Scanner input = new Scanner(System.in)) {

            Truk t1 = new Truk("N 9999 YY", 2);
            Bus b1 = new Bus("N 8888 ZZ", 42);

            Driver s1 = new Driver("Mr. Lewandowski", "12345678", 30);
            Driver s2 = new Driver("Mr. Podziemski", "12344321", 31);
            
            t1.supir = s1;
            b1.supir = s2;

            int pilihJenis = 0;

            while (true) {
                System.out.println("Jenis kendaraan:");
                System.out.println("1. Truk");
                System.out.println("2. Bus");
                System.out.println("3. Keluar");
                System.out.print("Pilih jenis kendaraan (masukkan angka): ");
                pilihJenis = input.nextInt();
                System.out.println();

                switch (pilihJenis) {
                    case 1:
                        t1.showPlat();
                        t1.showDriver();
                        t1.setKapasitas(10);
                        t1.angkut();
                        t1.cekPenumpang();
                        System.out.println();
                        menuTruk(t1);
                        break;
                    case 2:
                        b1.showPlat();
                        b1.showDriver();
                        b1.setToilet(true);
                        b1.basuri();
                        b1.cekPenumpang();
                        System.out.println();
                        menuBus(b1);
                        break;
                    case 3:
                        System.out.println("Terima kasih. Program berhenti.");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih antara 1-3.");
                }
            }
        }
    }

    public static void menuTruk(Truk t1) throws Exception {
        try (Scanner input = new Scanner(System.in)) {
            int pilihMenu = 0;
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Naik");
                System.out.println("2. Turun");
                System.out.println("3. Cek Penumpang");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu (masukkan angka): ");
                pilihMenu = input.nextInt();
                System.out.println();

                switch (pilihMenu) {
                    case 1:
                        System.out.println("Berapa jumlah penumpang naik?");
                        System.out.print("(masukkan angka): ");
                        int naik = input.nextInt();
                        t1.penumpangNaik(naik);
                        break;
                    case 2:
                        System.out.println("Berapa jumlah penumpang turun?");
                        System.out.print("(masukkan angka): ");
                        int turun = input.nextInt();
                        t1.penumpangTurun(turun);
                        break;
                    case 3:
                        t1.cekPenumpang();
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("Terima kasih. Program berhenti.");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih antara 1-4.");
                }
            }
        }
    }
    
    public static void menuBus(Bus b1) throws Exception {
        try (Scanner input = new Scanner(System.in)) {
            int pilihMenu = 0;
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Naik");
                System.out.println("2. Turun");
                System.out.println("3. Cek Penumpang");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu (masukkan angka): ");
                pilihMenu = input.nextInt();
                System.out.println();

                switch (pilihMenu) {
                    case 1:
                        System.out.println("Berapa jumlah penumpang naik?");
                        System.out.print("(masukkan angka): ");
                        int naik = input.nextInt();
                        b1.penumpangNaik(naik);
                        break;
                    case 2:
                        System.out.println("Berapa jumlah penumpang turun?");
                        System.out.print("(masukkan angka): ");
                        int turun = input.nextInt();
                        b1.penumpangTurun(turun);
                        break;
                    case 3:
                        b1.cekPenumpang();
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("Terima kasih. Program berhenti.");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih antara 1-4.");
                }
            }
        }
    }
}