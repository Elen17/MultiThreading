public class AddAndSubInOrder {
    static  int global = 10;
    static  boolean  flag = true;
    static  boolean print = true;

    public static void main(String[] args) {
        Thread t1= new Thread(new Runnable() {
            public void run() {
                while(true){
                    if(flag) {
                        if(print) {
                            global -= 10;
                            System.out.println(global);
                            print = false;
                        }
                    }
                    flag = false;
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while(true){
                    if(!flag) {
                        if(!print){
                            global += 10;
                            System.out.println(global);
                            print = true;
                        }
                    }
                    flag = true;

                }
            }
        });
        t1.start();
        t2.start();
    }
}

