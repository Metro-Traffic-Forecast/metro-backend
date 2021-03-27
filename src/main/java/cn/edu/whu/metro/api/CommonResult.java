package cn.edu.whu.metro.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author thomas
 * @version 1.0
 * @date 2021/3/26 18:45
 * 统一返回结果
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {

    private String code;
    private String message;
    private T data;

    public static <T> CommonResult<T> success() {
        return new CommonResult<T>()
                .setCode("00000")
                .setMessage("处理成功！");
    }

    public static <T> CommonResult<T> fail(ResultCode resultCode) {
        return new CommonResult<T>()
                .setCode(resultCode.getCode())
                .setMessage(resultCode.getMessage());
    }

    public CommonResult<T> append(T data) {
        this.data = data;
        return this;
    }


}
