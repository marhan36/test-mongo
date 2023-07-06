package com.example.testmongo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ResponseInfo {
    public HttpHeaders httpHeaders;
    public HttpStatus httpStatus;
    public ResponseBody body = new ResponseBody();

    public void setSuccess(){
        this.httpStatus = HttpStatus.OK;
        body.setSuccess();
    }

    public void setSuccess(Object data){
        this.httpStatus = HttpStatus.OK;
        body.setSuccess(data);
    }

    public void setException(HttpStatus httpStatus, Exception e){
        this.httpStatus = httpStatus;
        body.setException(e);
    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class ResponseBody{
        public String status;
        public String code;
        public String message;
        public Object data;

        public void setSuccess(){
            this.status = "ok";
            this.code = "00";
            this.message = "success";
        }

        public void setSuccess(Object data){
            setSuccess();
            this.data = data;
        }

        public void setException(Exception e){
            this.status = "error";
            this.code = "99";
            this.message = e.getMessage();
        }
    }
}
