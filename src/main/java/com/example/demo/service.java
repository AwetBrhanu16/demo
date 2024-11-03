package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class service {

    private final studentRepository studentRepository;

    public service(studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<student> getStudents(){
        return studentRepository.findAll();
    }
    
    public void addNewStudent(student student){
      Optional<student> studentOptional = studentRepository
              .findStudentByEmail(student.getEmail());
      if(studentOptional.isPresent()){

          throw new IllegalStateException("email is taken");

      }

      studentRepository.save(student);
    }

    public void deletStudent(Long studentId) {

       boolean exist = studentRepository.existsById(studentId);

        if (!exist){
            throw new IllegalStateException("student does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email
                              ){
        student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student "+studentId+" does not exist"));

        if(name != null &&
                !name.isEmpty() &&
                !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email != null &&
                !email.isEmpty() &&
                !Objects.equals(student.getEmail(), email)) {
            Optional<student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email is taken");
            }
            student.setEmail(email);
        }
    }
}
