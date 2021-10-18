package com.bjpowdernode.domain;

import java.util.List;

/**
 * @author gjd
 * @create 2021/10/8  17:38:30
 */
public class Department {

    private Integer id;
    private String name;
    private List<Student1> students;

    public List<Student1> getStudents() {
        return students;
    }

    public void setStudents(List<Student1> students) {
        this.students = students;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
