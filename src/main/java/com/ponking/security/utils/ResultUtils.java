package com.ponking.security.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应结果工具类
 * @author Peng
 * @date 2020/7/15--16:12
 **/
public class ResultUtils {

    public static void setResponseJson(HttpServletResponse response,Result result){
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Result{
        private Integer status;
        private String message;

        public Result(Integer status, String message) {
            this.status = status;
            this.message = message;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
