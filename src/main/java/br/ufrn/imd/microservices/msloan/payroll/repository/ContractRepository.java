package br.ufrn.imd.microservices.msloan.payroll.repository;

import br.ufrn.imd.microservices.msloan.payroll.model.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends MongoRepository<Contract, String> {
}
