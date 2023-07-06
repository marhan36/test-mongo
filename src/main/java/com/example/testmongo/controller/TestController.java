package com.example.testmongo.controller;

import com.example.testmongo.model.db.Product;
import com.example.testmongo.model.response.ResponseInfo;
import com.example.testmongo.usecase.TestUsecase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Mongo Template")
public class TestController {

    private final TestUsecase testUsecase;

    public TestController(TestUsecase testUsecase) {
        this.testUsecase = testUsecase;
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<ResponseInfo.ResponseBody> insert(@RequestHeader(name = "request-id", required = false) String requestId,
                                                            @RequestBody Product product){
        log.info("=======================================");
        log.info("[REQUEST START][insertData][{}]", product);
        ResponseInfo responseInfo = testUsecase.insert(product);
        log.info("[REQUEST END][insertData]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseInfo.ResponseBody> save(@RequestHeader(name = "request-id", required = false) String requestId,
                                                          @RequestBody Product product){
        log.info("=======================================");
        log.info("[REQUEST START][insertData][{}]", product);
        ResponseInfo responseInfo = testUsecase.save(product);
        log.info("[REQUEST END][insertData]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseInfo.ResponseBody> get(@RequestHeader(name = "request-id", required = false) String requestId,
                                                         @RequestParam(name = "productId", required = false) String productId){
        log.info("=======================================");
        log.info("[REQUEST START][getData][{}]", productId);
        ResponseInfo responseInfo = testUsecase.get(productId);
        log.info("[REQUEST END][getData]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }

    @GetMapping(value = "/exist")
    public ResponseEntity<ResponseInfo.ResponseBody> exist(@RequestHeader(name = "request-id", required = false) String requestId,
                                                           @RequestParam(name = "productId") String productId){
        log.info("=======================================");
        log.info("[REQUEST START][getData][{}]", productId);
        ResponseInfo responseInfo = testUsecase.exist(productId);
        log.info("[REQUEST END][getData]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }

    @GetMapping(value = "/count")
    public ResponseEntity<ResponseInfo.ResponseBody> count(@RequestHeader(name = "request-id", required = false) String requestId,
                                                           @RequestParam(name = "qty") int qty){
        log.info("=======================================");
        log.info("[REQUEST START][getData][{}]", qty);
        ResponseInfo responseInfo = testUsecase.count(qty);
        log.info("[REQUEST END][getData]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }

    @PutMapping(value = "/update-first")
    public ResponseEntity<ResponseInfo.ResponseBody> updateFirst(@RequestHeader(name = "request-id", required = false) String requestId,
                                                                 @RequestParam(name = "productId") String productId,
                                                                 @RequestParam(name = "qty") int qty) {
        log.info("=======================================");
        log.info("[REQUEST START][update-first][{} | {}]", productId, qty);
        ResponseInfo responseInfo = testUsecase.updateFirst(productId, qty);
        log.info("[REQUEST END][update-first]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }

    @PutMapping(value = "/update-multi")
    public ResponseEntity<ResponseInfo.ResponseBody> updateMulti(@RequestHeader(name = "request-id", required = false) String requestId,
                                                                 @RequestParam(name = "productId") String productId,
                                                                 @RequestParam(name = "qty") int qty) {
        log.info("=======================================");
        log.info("[REQUEST START][update-multi][{} | {}]", productId, qty);
        ResponseInfo responseInfo = testUsecase.updateMulti(productId, qty);
        log.info("[REQUEST END][update-multi]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }

    @PutMapping(value = "/upsert")
    public ResponseEntity<ResponseInfo.ResponseBody> upsert(@RequestHeader(name = "request-id", required = false) String requestId,
                                                                 @RequestParam(name = "productId") String productId,
                                                                 @RequestParam(name = "qty") int qty) {
        log.info("=======================================");
        log.info("[REQUEST START][upsert][{} | {}]", productId, qty);
        ResponseInfo responseInfo = testUsecase.upsert(productId, qty);
        log.info("[REQUEST END][upsert]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseInfo.ResponseBody> deleteData(@RequestHeader(name = "request-id", required = false) String requestId,
                                                                @RequestParam(name = "productId") String productId){
        log.info("=======================================");
        log.info("[REQUEST START][deleteData][{}]", productId);
        ResponseInfo responseInfo = testUsecase.delete(productId);
        log.info("[REQUEST END][deleteData]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }

    @DeleteMapping(value = "/delete-all")
    public ResponseEntity<ResponseInfo.ResponseBody> deleteAll(@RequestHeader(name = "request-id", required = false) String requestId,
                                                               @RequestParam(name = "productId") String productId){
        log.info("=======================================");
        log.info("[REQUEST START][deleteData][{}]", productId);
        ResponseInfo responseInfo = testUsecase.findAllRemove(productId);
        log.info("[REQUEST END][deleteData]");
        return new ResponseEntity<>(responseInfo.getBody(), responseInfo.getHttpHeaders(), responseInfo.getHttpStatus());
    }
}
