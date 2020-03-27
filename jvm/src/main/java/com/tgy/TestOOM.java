package com.tgy;

/**
 * 实验是这样的：想测试在指定的栈大小（160k）下通过不断创建多线程观察其造成的 OOM 类型
 *
 * @author DragonSwimDiving
 * @program jvm
 * @Date 2020-03-26 15:20
 **/

public class TestOOM {
    private void dontStop() {
        while(true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static  void main(String[] args) {
        TestOOM oom = new TestOOM();
        oom.stackLeakByThread();
    }
}
