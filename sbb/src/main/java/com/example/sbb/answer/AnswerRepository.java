package com.example.sbb.answer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
