
import java.util.concurrent.locks.LockSupport;

/**
 * Created by 215941826@qq.com on 2017/12/4.
 */
public class LockSupportTest {

    public static void main(String[] args){
        new Thread(new ThreadA()).start();
//        new Thread(new ThreadB()).start();
        System.out.println();


        System.out.println();
    }

    public static void test(){
        System.out.println("当前线程："+Thread.currentThread().getId());
        LockSupport.park();

        LockSupport.unpark(Thread.currentThread());//靠，自己都被挂起了还怎么能自己解锁自己
    }

}
class ThreadA implements Runnable{

    @Override
    public void run() {
        LockSupportTest.test();
        System.out.println("A");
    }
}

class ThreadB implements Runnable{

    @Override
    public void run() {
        LockSupportTest.test();
//        LockSupport.unpark(Thread.currentThread());
        System.out.println("B");
    }
}
