package BT3.QuanLySinhVien;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class SinhVien {
    private String hoTen;
    private int tuoi;
    private String diaChi;
    private double diemTrungBinh;

    public SinhVien(String hoTen, int tuoi, String diaChi, double diemTrungBinh) {
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.diaChi = diaChi;
        this.diemTrungBinh = diemTrungBinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public boolean coHoLe() {
        return hoTen.startsWith("Lê ");
    }

    public void hienThiThongTin() {
        System.out.println("Họ tên: " + hoTen);
        System.out.println("Tuổi: " + tuoi);
        System.out.println("Địa chỉ: " + diaChi);
        System.out.println("Điểm trung bình: " + diemTrungBinh);
        System.out.println("--------------------------");
    }
}

public class QuanLySinhVien {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<SinhVien> danhSachSinhVien = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU QUẢN LÝ SINH VIÊN =====");
            System.out.println("1. Nhập danh sách sinh viên");
            System.out.println("2. Xem danh sách sinh viên");
            System.out.println("3. Sắp xếp theo điểm trung bình");
            System.out.println("4. Tìm kiếm sinh viên theo tên");
            System.out.println("5. Xuất danh sách sinh viên có họ 'Lê'");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            
            int chon = scanner.nextInt();
            scanner.nextLine(); // Bỏ dòng trống sau khi nhập số
            
            switch (chon) {
                case 1:
                    nhapDanhSachSinhVien();
                    break;
                case 2:
                    hienThiDanhSachSinhVien();
                    break;
                case 3:
                    sapXepTheoDiemTrungBinh();
                    break;
                case 4:
                    timKiemSinhVien();
                    break;
                case 5:
                    hienThiSinhVienHoLe();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        }
    }

    private static void nhapDanhSachSinhVien() {
        System.out.print("Nhập số lượng sinh viên: ");
        int soLuong = scanner.nextInt();
        scanner.nextLine(); // Bỏ dòng trống

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhập thông tin sinh viên thứ " + (i + 1) + ":");
            System.out.print("Họ tên: ");
            String hoTen = scanner.nextLine();
            System.out.print("Tuổi: ");
            int tuoi = scanner.nextInt();
            scanner.nextLine(); // Bỏ dòng trống
            System.out.print("Địa chỉ: ");
            String diaChi = scanner.nextLine();
            System.out.print("Điểm trung bình: ");
            double diemTrungBinh = scanner.nextDouble();
            scanner.nextLine(); // Bỏ dòng trống
            
            danhSachSinhVien.add(new SinhVien(hoTen, tuoi, diaChi, diemTrungBinh));
        }
    }

    private static void hienThiDanhSachSinhVien() {
        if (danhSachSinhVien.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
            return;
        }
        System.out.println("\nDanh sách sinh viên:");
        for (SinhVien sv : danhSachSinhVien) {
            sv.hienThiThongTin();
        }
    }

    private static void sapXepTheoDiemTrungBinh() {
        if (danhSachSinhVien.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
            return;
        }
        Collections.sort(danhSachSinhVien, Comparator.comparingDouble(SinhVien::getDiemTrungBinh));
        System.out.println("Danh sách đã được sắp xếp theo điểm trung bình tăng dần:");
        hienThiDanhSachSinhVien();
    }

    private static void timKiemSinhVien() {
        if (danhSachSinhVien.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
            return;
        }
        System.out.print("Nhập tên sinh viên cần tìm: ");
        String tenCanTim = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.getHoTen().toLowerCase().contains(tenCanTim)) {
                sv.hienThiThongTin();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sinh viên có tên chứa '" + tenCanTim + "'.");
        }
    }

    private static void hienThiSinhVienHoLe() {
        if (danhSachSinhVien.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
            return;
        }
        boolean found = false;
        System.out.println("Danh sách sinh viên có họ 'Lê':");
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.coHoLe()) {
                sv.hienThiThongTin();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không có sinh viên nào có họ 'Lê'.");
        }
    }
}
