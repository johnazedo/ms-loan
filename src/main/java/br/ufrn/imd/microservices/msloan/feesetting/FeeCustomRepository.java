package br.ufrn.imd.microservices.msloan.feesetting;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class FeeCustomRepository {

    private final MongoTemplate mongoTemplate;

    public FeeCustomRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    void updateAllActive(){
        Query query = new Query().addCriteria(Criteria.where("active").is(true));
        Update update = new Update().set("active", false);
        mongoTemplate.update(Fee.class).matching(query).apply(update).all();
    }
}
