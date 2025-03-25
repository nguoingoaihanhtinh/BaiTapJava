package QuanLyNhanSu;
import java.util.ArrayList;
import java.util.Scanner;

class NhanVien {
    protected String hoTen;
    protected String ngaySinh; // dd/MM/yyyy
    protected double heSoLuong;

    public NhanVien(String hoTen, String ngaySinh, double heSoLuong) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.heSoLuong = heSoLuong;
    }

    public double tinhLuong() {
        return heSoLuong * 1200000;
    }

    public boolean sinhTrongThangHai() {
        return ngaySinh.split("/")[1].equals("02");
    }

    public boolean tenLaAn() {
        return hoTen.split(" ")[hoTen.split(" ").length - 1].equalsIgnoreCase("An");
    }

    public void hienThiThongTin() {
        System.out.println("Họ tên: " + hoTen);
        System.out.println("Ngày sinh: " + ngaySinh);
        System.out.println("Hệ số lương: " + heSoLuong);
        System.out.println("Lương: " + tinhLuong());
        System.out.println("--------------------------");
    }
}

class GiamDoc extends NhanVien {
    private double heSoChucVu;

    public GiamDoc(String hoTen, String ngaySinh, double heSoLuong, double heSoChucVu) {
        super(hoTen, ngaySinh, heSoLuong);
        this.heSoChucVu = heSoChucVu;
    }

    @Override
    public double tinhLuong() {
        return (heSoLuong + heSoChucVu) * 1200000;
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Hệ số chức vụ: " + heSoChucVu);
        System.out.println("--------------------------");
    }
}

class QuanLy extends NhanVien {
    private int soLuongNhanVienQuanLy;

    public QuanLy(String hoTen, String ngaySinh, double heSoLuong, int soLuongNhanVienQuanLy) {
        super(hoTen, ngaySinh, heSoLuong);
        this.soLuongNhanVienQuanLy = soLuongNhanVienQuanLy;
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Số nhân viên quản lý: " + soLuongNhanVienQuanLy);
        System.out.println("--------------------------");
    }
}

class NhanVienThuong extends NhanVien {
    private String tenDonVi;

    public NhanVienThuong(String hoTen, String ngaySinh, double heSoLuong, String tenDonVi) {
        super(hoTen, ngaySinh, heSoLuong);
        this.tenDonVi = tenDonVi;
    }

    public boolean thuocPhongKeToan() {
        return tenDonVi.equalsIgnoreCase("Kế toán");
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Phòng ban: " + tenDonVi);
        System.out.println("--------------------------");
    }
}

public class QuanLyNhanSu {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<NhanVien> danhSachNhanVien = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== MENU QUẢN LÝ NHÂN SỰ =====");
            System.out.println("1. Nhập danh sách nhân viên");
            System.out.println("2. Hiển thị danh sách nhân viên");
            System.out.println("3. Xuất nhân viên có lương cao nhất");
            System.out.println("4. Hiển thị nhân viên sinh trong tháng 2");
            System.out.println("5. Xuất nhân viên thuộc phòng Kế toán");
            System.out.println("6. Đếm số lượng nhân viên tên 'An'");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            int chon = scanner.nextInt();
            scanner.nextLine(); // Bỏ dòng trống

            switch (chon) {
                case 1:
                    nhapDanhSachNhanVien();
                    break;
                case 2:
                    hienThiDanhSachNhanVien();
                    break;
                case 3:
                    xuatNhanVienLuongCaoNhat();
                    break;
                case 4:
                    hienThiNhanVienThangHai();
                    break;
                case 5:
                    hienThiNhanVienPhongKeToan();
                    break;
                case 6:
                    demNhanVienTenAn();
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

    private static void nhapDanhSachNhanVien() {
        System.out.print("Nhập số lượng nhân viên: ");
        int soLuong = scanner.nextInt();
        scanner.nextLine(); // Bỏ dòng trống

        for (int i = 0; i < soLuong; i++) {
            System.out.println("Nhập thông tin nhân viên thứ " + (i + 1) + ":");
            System.out.print("Loại nhân viên (1: Giám đốc, 2: Quản lý, 3: Nhân viên): ");
            int loai = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Họ tên: ");
            String hoTen = scanner.nextLine();
            System.out.print("Ngày sinh (dd/MM/yyyy): ");
            String ngaySinh = scanner.nextLine();
            System.out.print("Hệ số lương: ");
            double heSoLuong = scanner.nextDouble();
            scanner.nextLine();

            if (loai == 1) {
                System.out.print("Hệ số chức vụ: ");
                double heSoChucVu = scanner.nextDouble();
                scanner.nextLine();
                danhSachNhanVien.add(new GiamDoc(hoTen, ngaySinh, heSoLuong, heSoChucVu));
            } else if (loai == 2) {
                System.out.print("Số lượng nhân viên quản lý: ");
                int soLuongNhanVienQuanLy = scanner.nextInt();
                scanner.nextLine();
                danhSachNhanVien.add(new QuanLy(hoTen, ngaySinh, heSoLuong, soLuongNhanVienQuanLy));
            } else {
                System.out.print("Tên đơn vị (phòng/ban): ");
                String tenDonVi = scanner.nextLine();
                danhSachNhanVien.add(new NhanVienThuong(hoTen, ngaySinh, heSoLuong, tenDonVi));
            }
        }
    }

    private static void hienThiDanhSachNhanVien() {
        for (NhanVien nv : danhSachNhanVien) {
            nv.hienThiThongTin();
        }
    }

    private static void xuatNhanVienLuongCaoNhat() {
        NhanVien caoNhat = danhSachNhanVien.stream().max((a, b) -> Double.compare(a.tinhLuong(), b.tinhLuong())).orElse(null);
        if (caoNhat != null) {
            System.out.println("Nhân viên có lương cao nhất:");
            caoNhat.hienThiThongTin();
        }
    }

    private static void hienThiNhanVienThangHai() {
        danhSachNhanVien.stream().filter(NhanVien::sinhTrongThangHai).forEach(NhanVien::hienThiThongTin);
    }

    private static void hienThiNhanVienPhongKeToan() {
        danhSachNhanVien.stream().filter(nv -> nv instanceof NhanVienThuong && ((NhanVienThuong) nv).thuocPhongKeToan()).forEach(NhanVien::hienThiThongTin);
    }

    private static void demNhanVienTenAn() {
        long count = danhSachNhanVien.stream().filter(NhanVien::tenLaAn).count();
        System.out.println("Số nhân viên tên 'An': " + count);
    }
}
