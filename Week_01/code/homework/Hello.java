package homework;
public class Hello {

    public static void main(String[] args) {
        Hello hello = new Hello();
        int[] intArr = {5,4,3,2,1};
        int sum = 0;
        for (int i : intArr) {
            sum = hello.add(sum, i);
            if(sum >= 10){
                break;
            }
        }
        for (int i = 0; i < intArr.length; i++) {
            sum = hello.sub(sum, i);
        }
        double ret = 1;
        for (int i : intArr) {
            ret = hello.mul((int) ret, i);
        }
        for (int i = 0; i < intArr.length; i++) {
            ret = hello.div(ret, i);
        }
    }

    private int add(int a, int b){
        return a + b;
    }

    private int sub(int a, int b){
        return a - b;
    }

    private int mul(int a, int b){
        return a * b;
    }

    private double div(double a, double b){
        return a / b;
    }
}