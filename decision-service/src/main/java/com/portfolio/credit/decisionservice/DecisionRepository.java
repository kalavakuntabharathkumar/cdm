package com.portfolio.credit.decisionservice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface DecisionRepository extends JpaRepository<DecisionEntity,Long> { Optional<DecisionEntity> findByApplicantId(String applicantId); }
