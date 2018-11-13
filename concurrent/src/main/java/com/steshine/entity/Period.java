package com.steshine.entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Period implements Serializable {
    private final Date start;
    private final Date end;
    private HashMap map = new HashMap();

    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if(start.compareTo(end) > 0){
            throw new  IllegalArgumentException("stat after end");
        }
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        System.out.println("i am exert readObject");
    }

    private Object writeReplace() {
        System.out.println("i am exert writeReplace");
        return new Period(new Date(),new Date());
    }
}
