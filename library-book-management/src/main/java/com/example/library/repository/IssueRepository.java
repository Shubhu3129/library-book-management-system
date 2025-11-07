package com.example.library.repository;
import com.example.library.entity.Issue;
import com.example.library.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByStudentId(Long studentId);
    List<Issue> findByDueDateBeforeAndReturnDateIsNull(LocalDate date);
    boolean existsByBookIdAndReturnDateIsNull(Long bookId);
    List<Issue> findByStudent(AppUser user);
}
