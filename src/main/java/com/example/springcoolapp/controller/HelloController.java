package com.example.springcoolapp.controller;

import com.example.springcoolapp.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/hello/")
public class HelloController {

    List<Student> students = new ArrayList<>(List.of(
            new Student("1", "Sahed", 26),
            new Student("2", "Muslim", 25),
            new Student("3", "Anton", 23)
    ));

    @GetMapping
    public String hello(@RequestParam String name,@RequestParam(defaultValue = "0") int age) {
        if (age == 0) {
            return "Hello, my name is " + name;
        } else if (String.valueOf(age) != "0") {
            return "Hello, my name is " + name + " and I'm " + age + " years old.";
        } else {
            return "Hello";
        }
    }

    @GetMapping("{id}")
    public String myPath(@PathVariable String id) {
        return "my id is " + id;
    }

    @GetMapping("all/")
    public List<Student> listStudents() {
        return this.students;
    }

    @PostMapping
    public Student getPostRequest(@RequestBody Student studentObject) {
        return studentObject;
    }

    @DeleteMapping("{id}")
    public List<Student> deleteStudent(@PathVariable String id) {
        for (Student student : this.students) {
            if (student.getId().equals(id)) {
                students.remove(student);
            }
        }
        return this.students;
    }

    @PutMapping
    public List<Student> updateStudents(@RequestBody Student theStudent){

        for (Student student : this.students) {
            if (student.getId().equals(theStudent.getId())) {
                student.setAge(theStudent.getAge());
                student.setName(theStudent.getName());
            }
        }
        return this.students;
    }
}
