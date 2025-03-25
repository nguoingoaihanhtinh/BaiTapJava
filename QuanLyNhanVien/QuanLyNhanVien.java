package BT3.QuanLyNhanVien;
import java.util.ArrayList;
import java.util.Scanner;

// Lớp Person
class Person {
    protected String hoTen;
    protected int tuoi;
    protected String diaChi;

    public Person(String hoTen, int tuoi, String diaChi) {
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.diaChi = diaChi;
    }

    public void hienThiThongTin() {
        System.out.println("Họ tên: " + hoTen);
        System.out.println("Tuổi: " + tuoi);
        System.out.println("Địa chỉ: " + diaChi);
    }
}

// Lớp NhanVien kế thừa từ Person
class NhanVien extends Person {
    private double luongCoBan;
    private double heSoLuong;

    public NhanVien(String hoTen, int tuoi, String diaChi, double luongCoBan, double heSoLuong) {
        super(hoTen, tuoi, diaChi);
        this.luongCoBan = luongCoBan;
        this.heSoLuong = heSoLuong;
    }

    public double tinhLuong() {
        return luongCoBan * heSoLuong;
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Lương cơ bản: " + luongCoBan);
        System.out.println("Hệ số lương: " + heSoLuong);
        System.out.println("Lương: " + tinhLuong());
    }
}

public class QuanLyNhanVien {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();

        System.out.print("Nhập số lượng nhân viên: ");
        int soLuong = scanner.nextInt();
        scanner.nextLine(); // Đọc bỏ dòng thừa

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhập thông tin nhân viên thứ " + (i + 1) + ":");
            System.out.print("Họ tên: ");
            String hoTen = scanner.nextLine();
            System.out.print("Tuổi: ");
            int tuoi = scanner.nextInt();
            scanner.nextLine(); // Đọc bỏ dòng thừa
            System.out.print("Địa chỉ: ");
            String diaChi = scanner.nextLine();
            System.out.print("Lương cơ bản: ");
            double luongCoBan = scanner.nextDouble();
            System.out.print("Hệ số lương: ");
            double heSoLuong = scanner.nextDouble();
            scanner.nextLine(); // Đọc bỏ dòng thừa

            danhSachNhanVien.add(new NhanVien(hoTen, tuoi, diaChi, luongCoBan, heSoLuong));
        }

        // Tính tổng lương và tìm nhân viên có lương cao nhất
        double tongLuong = 0;
        NhanVien nhanVienLuongCaoNhat = danhSachNhanVien.get(0);

        for (NhanVien nv : danhSachNhanVien) {
            tongLuong += nv.tinhLuong();
            if (nv.tinhLuong() > nhanVienLuongCaoNhat.tinhLuong()) {
                nhanVienLuongCaoNhat = nv;
            }
        }

        // Xuất kết quả
        System.out.println("\nTổng lương của tất cả nhân viên: " + tongLuong);
        System.out.println("Nhân viên có lương cao nhất:");
        nhanVienLuongCaoNhat.hienThiThongTin();

        scanner.close();
    }
}
