package me.zyq.phonebook.springboot.controller;

import io.jsonwebtoken.Claims;
import me.zyq.phonebook.springboot.constant.SystemConstant;
import me.zyq.phonebook.springboot.model.R;
import me.zyq.phonebook.springboot.model.TAdmin;
import me.zyq.phonebook.springboot.utils.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author djin
 *   TAdmin控制器
 * @date 2020-12-05 08:49:13
 */
@Controller
@RequestMapping("/tadmin")
public class TAdminController extends BaseController<TAdmin>{

    /**
     *   系统用户登录
     * @param tAdmin 页面传来的登录数据
     * @return 登录结果
     */
    @PostMapping("/login")
    public @ResponseBody R login(@RequestBody TAdmin tAdmin){
        try {
            //进行业务层的查询数据
            TAdmin loginTAdmin = tAdminService.findObjectByPramas(tAdmin);
            if(loginTAdmin==null){
                return R.error("用户名或者密码错误！");
            }else {
                //把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带cookie参数
                String JWT = JwtUtils.createJWT("1",loginTAdmin.getUsername(), SystemConstant.JWT_TTL);
                return R.ok(JWT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("系统异常！");
        }
    }

    /**
     * 刷新用户token
     * @param request
     * @return
     */
    @GetMapping(value = "/refreshToken")
    public @ResponseBody R refreshToken(HttpServletRequest request){
        Claims claims = JwtUtils.validateJWT(request.getHeader("token")).getClaims();
        String JWT = JwtUtils.createJWT(claims.getId(),claims.getSubject(), SystemConstant.JWT_TTL);
        logger.info("新token"+JWT);
        return R.ok(JWT);
    }
}
