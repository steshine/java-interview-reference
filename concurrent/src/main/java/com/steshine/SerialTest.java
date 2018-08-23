package com.steshine;

import com.steshine.entity.Period;

import java.io.*;
import java.util.Date;

public class SerialTest {
    public static void main(String[] args) {
        File file = new File("D://period.bin");
        Date start = new Date(1535037994940L);
        Date end = new Date(1535037994941L);
        Period period = new Period(start,end);
        print(period);
        try {
            OutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(period);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = ((ByteArrayOutputStream) os).toByteArray();
            fos.write(bytes);
            fos.flush();
            Period deserialize = (Period) deserialize(bytes);
            print(deserialize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object deserialize(byte[] df){
        try {
            InputStream is = new ByteArrayInputStream(df);
            ObjectInputStream ois = new ObjectInputStream(is);
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void print(Period period){
        System.out.println(period.getStart().getTime()+" ~"+period.getEnd().getTime());
    }
}
