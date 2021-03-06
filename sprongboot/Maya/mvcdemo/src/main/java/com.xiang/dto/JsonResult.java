package com.xiang.dto;

public class JsonResult {
    private Meta meta;
    private Object data;

    public JsonResult success() {
        this.meta = Meta.SUCCESS;
        return this;
    }

    public JsonResult success(Object data) {
        this.meta = Meta.SUCCESS;
        this.data = data;
        return this;
    }

    public JsonResult failure() {
        this.meta = Meta.Err400;
        return this;
    }

    public JsonResult failure(Meta meta) {
        this.meta = meta;
        return this;
    }

    public JsonResult failure(Meta meta, Object data) {
        this.meta = meta;
        this.data = data;
        return this;
    }

    public enum Meta {
        /**
         * 请求成功
         */
        SUCCESS(200, "请求成功。"),

        /**
         * 请求错误
         */
        Err400(400, "请求错误。"),
        Err401(401, "当前请求需要用户验证。"),
        Err404(404, "请求失败，请求所希望得到的资源未被在服务器上发现。"),

        /**
         * 服务器错误
         */
        Err500(500, "服务器遇到了一个未曾预料的状况。"),
        Err501(501, "服务器不支持当前请求所需要的某个功能。"),
        Err502(502, "从上游服务器接收到无效的响应。"),

        /**
         * 业务错误
         */
        Err1(1, "未登录！"),
        Err3(3,"权限不足");
        Integer code;
        String message;

        Meta(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("{\"code\":\"").append(this.code).append("\",\"message\":\"")
                    .append(this.message).append("\"}").toString();
        }
    }
}
