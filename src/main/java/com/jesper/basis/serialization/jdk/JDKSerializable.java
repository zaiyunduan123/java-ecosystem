package com.jesper.basis.serialization.jdk;

import com.jesper.basis.serialization.Student;

import java.io.*;

/**
 * Created by jiangyunxiong on 2018/7/16.
 * <p>
 * JDK 序列化
 */
public class JDKSerializable {

    /**
     * 在使用JDK提供的序列化机制时需要借助一对I/O流, ObjectOutputStream和ObjectInputStream,
     * 这两个流分别是进行序列化和反序列化操作, 通过ObjectOutputStream类的writeObject(obj)方法
     * 可以将对象写入到输出流中, 通过ObjectInputStream类的readObject()方法可以从该输入流中反序列化该对象出来
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // create a Student
        Student st = new Student("Jesper");
        // serialize the st to student.db file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.db"));
        oos.writeObject(st);
        oos.close();
        // deserialize the object from student.db
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.db"));
        Student kirito = (Student) ois.readObject();
        ois.close();
        System.out.println("Jesper".equals(kirito.getName()));
    }
}
