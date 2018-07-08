package JVM;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jiangyunxiong on 2018/7/5.
 * <p>
 * 写代码实现
 * <p>
 * 1. 栈溢出(StackOverflowError)
 * 2. 堆溢出(OutOfMemoryError:Java heap space)
 * 3. 永久代溢出(OutOfMemoryError: PermGen space)
 * 4. 直接内存溢出
 */
public class JVMExceptionDemo {

    /**
     * 堆溢出 VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     */
    public void OutOfMemoryTest() {
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(new byte[5 * 1024 * 1024]);
            System.out.println("分配次数：" + (++i));
        }
    }


    /**
     * 栈溢出
     */
    int depth = 0;

    public void softMethod() {
        depth++;
        softMethod();
    }

    public void StackOverflowTest() {
        JVMExceptionDemo test = null;
        try {
            test = new JVMExceptionDemo();
            test.softMethod();
        } finally {
            System.out.println("递归次数：" + test.depth);
        }
    }

    /**
     * 永久代溢出  VM Args:-XX:PermSize=10m -XX:MaxPermSize=10m
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 1;
        try {
            while (true) {
                list.add(UUID.randomUUID().toString().intern());
                i++;
            }
        } finally {
            System.out.println("运行次数：" + i);
        }
    }
}
