package ra.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDemo {
    public static void main(String[] args) {
        List<Student> listStudent = new ArrayList<>();
        Student student1 = new Student("SV001", "Nguyễn Văn A", 20);
        Student student2 = new Student("SV002", "Nguyễn Văn B", 22);
        listStudent.add(student1);
        listStudent.add(student2);
        FileDemo.writeDataToFile(listStudent);
        List<Student> listStudentRead = FileDemo.readDataFromFile();
        for (Student student : listStudentRead) {
            System.out.println(student);
        }
    }

    public static void writeDataToFile(List<Student> listStudent) {
        //1. Khởi tạo đối tượng file để làm việc với file - tương đối
        File file = new File("demo.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //2. Khởi tạo đối tượng FileOutputStream từ file - Checked Excetion
            fos = new FileOutputStream(file);
            //3. Khởi tạo đối tượng ObjectOutputStream từ fos
            oos = new ObjectOutputStream(fos);
            //4. Sử dụng writeObject để ghi object ra file
            oos.writeObject(listStudent);
            //5. Đẩy dữ liệu từ Stream xuống file
            oos.flush();
        } catch (FileNotFoundException ex1) {
            System.err.println("File không tồn tại");
        } catch (IOException ex2) {
            System.err.println("Lỗi khi ghi dữ liệu ra file");
        } catch (Exception ex) {
            System.err.println("Xảy ra lỗi trong quá trình ghi dữ liệu ra file");
        } finally {
            //6. Đóng các stream
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex1) {
                System.err.println("Xảy ra lỗi khi đóng các stream");
            } catch (Exception ex) {
                System.err.println("Xảy ra lỗi trong quá trình đóng các stream");
            }
        }
    }

    public static List<Student> readDataFromFile() {
        //1. Khởi tạo đối tượng File
        File file = new File("demo.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<Student> listStudentRead = null;
        try {
            //2. Khởi tạo đối tượng FileInputStream
            fis = new FileInputStream(file);
            //3. Khởi tạo đối tượng ObjectInputStream
            ois = new ObjectInputStream(fis);
            //4. Đọc dữ liệu object từ file (readObject())
            listStudentRead = (List<Student>) ois.readObject();
        } catch (FileNotFoundException ex1) {
            System.err.println("Không tồn tại file");
        } catch (IOException ex2) {
            System.err.println("Lỗi khi đọc file");
        } catch (Exception ex) {
            System.err.println("Có lỗi trong quá trình đọc dữ liệu từ file");
        } finally {
            //6. Đóng các stream
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex1) {
                System.err.println("Có lỗi khi đóng stream");
            } catch (Exception ex) {
                System.err.println("Có lỗi trong quá trình đóng các stream");
            }
            return listStudentRead;
        }
    }

}
