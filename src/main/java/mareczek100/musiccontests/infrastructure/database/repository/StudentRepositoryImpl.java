package mareczek100.musiccontests.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.StudentRepositoryDAO;
import mareczek100.musiccontests.domain.Student;
import mareczek100.musiccontests.infrastructure.database.entity.StudentEntity;
import mareczek100.musiccontests.infrastructure.database.mapper.StudentEntityMapper;
import mareczek100.musiccontests.infrastructure.database.repository.jpaRepository.StudentJpaRepository;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class StudentRepositoryImpl implements StudentRepositoryDAO {

    private final StudentJpaRepository studentJpaRepository;
    private final StudentEntityMapper studentEntityMapper;


    @Override
    public Student insertStudent(Student student)
    {
        StudentEntity studentEntity = studentEntityMapper.mapFromDomainToEntity(student);
        StudentEntity savedStudentEntity = studentJpaRepository.saveAndFlush(studentEntity);
        return studentEntityMapper.mapFromEntityToDomain(savedStudentEntity);
    }

    @Override
    public List<Student> findAllStudents()
    {
        return studentJpaRepository.findAll().stream()
                .map(studentEntityMapper::mapFromEntityToDomain)
                .toList();
    }

    @Override
    public Optional<Student> findStudentByPesel(String pesel)
    {
        return studentJpaRepository.findStudentByPesel(pesel)
                .map(studentEntityMapper::mapFromEntityToDomain);
    }
}