package com.mingmingcome.designpattern.behavioral.memento;

/**
 * @who luhaoming
 * @when 2020/11/29 20:25
 * @what 我
 */
public class I {
    private int age;
    private String doingWhat;

    public Photo createPhoto() {
        return new Photo(this.age, this.doingWhat);
    }

    public void backToThePast(Photo photo) {
        this.age =  photo.getAge();
        this.doingWhat = photo.getDoingSomething();
        System.out.println("时光倒流，我现在是" + age + "岁，可以：" + doingWhat);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDoingWhat(String doingWhat) {
        this.doingWhat = doingWhat;
    }

    public void print() {
        System.out.println("我现在是" + age + "岁，可以：" + doingWhat);
    }

    // 照片
    public class Photo {
        private int age;
        private String doingWhat;

        public Photo(int age, String doingWhat) {
            this.age = age;
            this.doingWhat = doingWhat;
        }

        private int getAge() {
            return age;
        }

        private String getDoingSomething() {
            return doingWhat;
        }

    }
}
