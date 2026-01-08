package tn.poste.myship.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.poste.myship.entity.Operation;

public interface OperationRepo extends JpaRepository<Operation, Long>{

}
