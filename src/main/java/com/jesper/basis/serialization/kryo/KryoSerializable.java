package com.jesper.basis.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.jesper.basis.serialization.Student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * Created by jiangyunxiong on 2018/7/16.
 */

/**
 * 序列化的过程中主要有3个指标：
 *
 * 1、对象序列化后的大小,一个对象会被序列化工具序列化为一串byte数组，这其中包含了对象的field值以及元数据信息，使其可以被反序列化回一个对象
 * <p>
 * 2、序列化与反序列化的速度,一个对象被序列化成byte数组的时间取决于它生成/解析byte数组的方法
 * <p>
 * 3、序列化工具本身的速度,序列化工具本身创建会有一定的消耗。
 */
public class KryoSerializable {
    public static void main(String[] args) throws FileNotFoundException {
        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("student.db"));
        Student jesper = new Student("jesper");
        kryo.writeObject(output, jesper);
        output.close();

        Input input = new Input(new FileInputStream("student.db"));
        Student jesperBak = kryo.readObject(input, Student.class);
        input.close();

        System.out.println("jesper".equals(jesperBak.getName()));
    }
}
