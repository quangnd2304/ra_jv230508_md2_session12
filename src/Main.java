import ra.exception.MyException;

public class Main {
    public static void main(String[] args) throws MyException {
        int a = 10;
        int b = 0;
        if (b==0){
            throw  new MyException("Lỗi chia cho 0");
        }
    }
}