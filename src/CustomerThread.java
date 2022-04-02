public class CustomerThread extends Thread {
    String customer;

    public CustomerThread(String cus) {
        customer = cus;
    }


    public static void main(String[] args) {
            new CustomerThread("顾客1").start();
            new CustomerThread("顾客2").start();
    }

    @Override
    public void run() {
        System.out.println("欢迎："+customer);
        for (int i = 0; i < 3; i++) {
            System.out.println(customer+"要求服务！");
        }
        System.out.println(customer+"离开！");
    }
}
