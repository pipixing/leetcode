public class JoinExample {
    private class A extends Thread {
        @Override
        public void run() {
            System.out.println("A干自己的活儿");
        }
    }

    private class B extends Thread {

        private A a;

        B(A a) {
            this.a = a;
        }

        @Override
        public void run() {
            try {
                //如果需要a来协作，那么b会挂起，而不是忙等待
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("B干自己的活儿");
        }
    }

    public void test() {
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
    }
}
