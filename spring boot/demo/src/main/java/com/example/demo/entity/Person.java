package com.example.demo.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ConfigurationProperties 将配置文件中的每个属性值映射到这个组件中，告诉springBoot将本类中的属性与配置文件进行绑定
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private int age;
    private Dog dog;
    private List<String> friends;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dog=" + dog +
                ", friends=" + friends +
                '}';
    }
}
