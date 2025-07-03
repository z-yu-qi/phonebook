package me.zyq.phonebook.springboot.model;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.Getter;

/**
 * jwt验证信息实体封装类
 * @author djin
 * @create 2019-08-13 上午 10:00
 */
@Data
public class CheckResult {

    private int errCode;

    private boolean success;

    private Claims claims;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

}