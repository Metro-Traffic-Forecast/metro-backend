package cn.edu.whu.metro.api;

import lombok.Getter;

/**
 * @author thomas
 * @version 1.0
 * @date 2021/3/26 18:45
 * 错误码
 **/
@Getter
public enum ResultCode {
    /**
     * 默认成功值
     */
    SUCCESS("00000","操作成功！"),

    /**
     * http错误
     */
    NOT_FOUND("H0404", "请求的页面不存在！"),
    METHOD_NOT_ALLOWED("H0405", "需要先带用户名和密码点击登录，登录相应账号后才能访问！"),
    UNSUPPORTED_MEDIA_TYPE("H0415", "不支持的数据类型！"),
    INTERNAL_SERVER_ERROR("H0500", "内部服务器错误！"),

    OSS_SIGN_FAIL("O0000", "OSS签名出错！"),

    USER_OR_PASSWORD_ERROR("A0003","用户名或密码错误"),

    DB_ERROR("A0005","数据库查询错误"),

    NO_HANDLER_FOUND("N0001","没找到处理异常的handler"),

    PARAMETER_NEEDED("P0002","请输入用户名和密码"),


    USER_DEFAULT("A0001", "用户端错误"),

    USER_NOT_EXISTED("A0201", "用户不存在"),

    NULL_POINTER_EXCEPTION("E0000","空指针异常"),

    UNKNOWN_EXCEPTION("N0006","未设置处理函数的异常");




    private final String code;

    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


}
