package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class controller {

    private final service service;

    @Autowired
    public controller(service service) {
        this.service = service;
    }

    @GetMapping
    public List<student> getStudents(){
        return service.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody student student){
        service.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deletStudent(@PathVariable("studentId") Long studentId){
        service.deletStudent(studentId);
   }

   @PutMapping(path = "{studentId}")
   public void updatestudent(@PathVariable("studentId") Long studentId,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String email){
        service.updateStudent(studentId,name,email);
   }
}
