package com.example.testmongo.usecase;

import com.example.testmongo.model.db.Product;
import com.example.testmongo.model.response.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TestUsecase {

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

    public ResponseInfo insert(Product product){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[INSERT]");

            mongoTemplate.insert(product);

            log.info("[SUCCESS INSERT]");
            responseInfo.setSuccess();

        } catch (Exception e){
            log.info("[FAILED INSERT][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }

    public ResponseInfo save(Product product){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[SAVE INSERT]");

            mongoTemplate.save(product);

            log.info("[SUCCESS SAVE INSERT]");
            responseInfo.setSuccess();

        } catch (Exception e){
            log.info("[FAILED SAVE INSERT][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }

    public ResponseInfo get(String productId){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[GET DATA]");
            List<Product> listProduct;
            Query query = new Query();
            if(!StringUtils.isEmpty(productId)){
                query.addCriteria(Criteria.where("productId").is(productId));
                query.fields().include("productId").include("qty");

                listProduct = mongoTemplate.find(query, Product.class);
            }else{
                query.with(Sort.by("productId").descending());

                listProduct = mongoTemplate.find(query, Product.class);
            }
            log.info("[SUCCESS GET DATA][{}]", listProduct);
            responseInfo.setSuccess(listProduct);

        }catch (Exception e){
            log.info("[FAILED GET DATA][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }

    public ResponseInfo updateFirst(String productId, int qty){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[UPDATE DATA]");

            Query query = new Query();
            query.addCriteria(Criteria.where("productId").is(productId));

            Update update = new Update();
            update.set("qty", qty);

            mongoTemplate.updateFirst(query, update, Product.class);

            log.info("[SUCCESS UPDATE DATA][{} | {}]", productId, qty);
            responseInfo.setSuccess();

        }catch (Exception e){
            log.info("[FAILED UPDATE DATA][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }

    public ResponseInfo updateMulti(String productId, int qty){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[UPDATE DATA]");

            Query query = new Query();
            query.addCriteria(Criteria.where("productId").is(productId));

            Update update = new Update();
            update.set("qty", qty);

            mongoTemplate.updateMulti(query, update, Product.class);

            log.info("[SUCCESS UPDATE DATA][{} | {}]", productId, qty);
            responseInfo.setSuccess();

        }catch (Exception e){
            log.info("[FAILED UPDATE DATA][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }

    public ResponseInfo upsert(String productId, int qty){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[UPDATE DATA]");

            Query query = new Query();
            query.addCriteria(Criteria.where("productId").is(productId));
            Update update = new Update();
            update.set("qty", qty);

            mongoTemplate.upsert(query, update, Product.class);

            log.info("[SUCCESS UPDATE DATA][{} | {}]", productId, qty);
            responseInfo.setSuccess();

        }catch (Exception e){
            log.info("[FAILED UPDATE DATA][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }

    public ResponseInfo exist(String productId){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[CHECK DATA EXIST]");

            Query query = new Query();
            query.addCriteria(Criteria.where("productId").is(productId));

            boolean dataExists = mongoTemplate.exists(query, Product.class);

            log.info("[SUCCESS CHECK DATA EXIST][{}]", productId);
            responseInfo.setSuccess(dataExists);

        }catch (Exception e){
            log.info("[FAILED CHECK DATA EXIST][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }

    public ResponseInfo count(int qty){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[COUNT DATA]");

            Query query = new Query();
            query.addCriteria(Criteria.where("qty").is(qty));

            long count = mongoTemplate.count(query, Product.class);

            log.info("[SUCCESS COUNT DATA][{}]", qty);
            responseInfo.setSuccess(count);

        }catch (Exception e){
            log.info("[FAILED COUNT DATA][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }

    public ResponseInfo delete(String productId){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[DELETE]");

            Query query = new Query();
            query.addCriteria(Criteria.where("productId").is(productId));

            mongoTemplate.findAndRemove(query, Product.class);

            log.info("[SUCCESS DELETE]");
            responseInfo.setSuccess();

        }catch (Exception e){
            log.info("[FAILED DELETE][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }

    public ResponseInfo findAllRemove(String productId){
        ResponseInfo responseInfo = new ResponseInfo();

        try{
            log.info("[DELETE]");

            Query query = new Query();
            query.addCriteria(Criteria.where("productId").is(productId));

            mongoTemplate.findAllAndRemove(query, Product.class);

            log.info("[SUCCESS DELETE]");
            responseInfo.setSuccess();

        }catch (Exception e){
            log.info("[FAILED DELETE][{}]", e.getMessage());
            responseInfo.setException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

        return responseInfo;
    }
}
