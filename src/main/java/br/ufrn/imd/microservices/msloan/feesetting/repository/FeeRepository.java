package br.ufrn.imd.microservices.msloan.feesetting.repository;

import br.ufrn.imd.microservices.msloan.feesetting.model.Fee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends MongoRepository<Fee, String> {
    Fee findByActiveEquals(boolean active);
}
