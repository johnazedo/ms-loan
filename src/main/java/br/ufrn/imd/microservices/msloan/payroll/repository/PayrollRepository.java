package br.ufrn.imd.microservices.msloan.payroll.repository;

import br.ufrn.imd.microservices.msloan.payroll.model.Payroll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends MongoRepository<Payroll, String> {
    List<Payroll> findAllByAccountId(Integer id);
}
