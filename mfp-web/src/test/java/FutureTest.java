/**
 * Created by 215941826@qq.com on 2017/12/1.
 */

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        //launch(args);
        FutureTask future = null;
        LockSupport lockSup = null;
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        conditionA.await();
    }

}
