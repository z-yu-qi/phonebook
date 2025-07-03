package me.zyq.phonebook.springboot.interceptor;

import me.zyq.phonebook.springboot.constant.SystemConstant;
import me.zyq.phonebook.springboot.model.CheckResult;
import me.zyq.phonebook.springboot.model.R;
import me.zyq.phonebook.springboot.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class SysInterceptor implements HandlerInterceptor {
    //日志对象
    private final static Logger logger= LoggerFactory.getLogger(SysInterceptor.class);

    //拦截的核心方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("执行了拦截的核心方法");
        String contextPath = request.getRequestURI();
        logger.info("路径"+contextPath);
        if (handler instanceof HandlerMethod){
            //从请求头中取到token令牌
            String authHeader = request.getHeader("token");
            logger.info(authHeader);
            if (StringUtils.isEmpty(authHeader)) {
                logger.info("验证失败");
                print(response, R.error(SystemConstant.JWT_ERRCODE_NULL,"签名验证不存在"));
                return false;  //请求在此处就被拦截下来
            }else{
                //验证JWT的签名，返回CheckResult对象
                CheckResult checkResult = JwtUtils.validateJWT(authHeader);
                if (checkResult.isSuccess()) {
                    logger.info("签名验证通过");
                    return true;
                } else {
                    switch (checkResult.getErrCode()) {
                        // 签名验证不通过
                        case SystemConstant.JWT_ERRCODE_FAIL:
                            logger.info("签名验证不通过");
                            print(response,R.error(checkResult.getErrCode(),"签名验证不通过"));
                            break;
                        // 签名过期，返回过期提示码
                        case SystemConstant.JWT_ERRCODE_EXPIRE:
                            logger.info("签名过期");
                            print(response,R.error(checkResult.getErrCode(),"签名过期"));
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            }
        }else{
            return true;
        }
    }

    //拦截的执行POST请求的方法
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("拦截的执行POST请求的方法");
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("执行了afterCompletion方法");
    }

    public void print(HttpServletResponse response, Object message){
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            PrintWriter writer = response.getWriter();
            writer.write(message.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





