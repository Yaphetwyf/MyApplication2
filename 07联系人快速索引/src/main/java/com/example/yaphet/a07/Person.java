package com.example.yaphet.a07;

/**
 * Created by WYF on 2017/10/9.
 * 设置ListView的数据类，
 */

public class Person {
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", pingying='" + pingying + '\'' +
                '}';
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPingying(String pingying) {
        this.pingying = pingying;
    }

    public String getName() {

        return name;
    }

    public String getPingying() {
        return pingying;
    }

    public Person(String name) {
        this.name = name;
        this.pingying=PinYinUtils.getPinYin(name);
    }

    private String name;
    private String pingying;

}
