package me.zyq.phonebook.springboot.controller;

import me.zyq.phonebook.springboot.model.R;
import me.zyq.phonebook.springboot.model.TPhonebook;
import me.zyq.phonebook.springboot.utils.QiniuUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author djin
 *   TPhonebook控制器
 * @date 2020-12-05 08:49:13
 */
@Controller
@RequestMapping("/tphonebook")
public class TPhonebookController extends BaseController<TPhonebook>{
    /**
     * 根据姓名首字母查询电话簿
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/loadAllTPhonebook")
    public @ResponseBody R loadAllTPhonebook() throws Exception{
        Map<String,Object> map=new HashMap<>();
        char [] letters={'A','B','C','D','E','F','G','H','I','J','K',
                'L','M','N','O','P','Q','R','S','T','U','V',
                'W','X','Y','Z'};
        for(int i=0;i<letters.length;i++){
            String letter=String.valueOf(letters[i]);
            //构建查询的条件对象
            TPhonebook queryTPhonebook = new TPhonebook();
            queryTPhonebook.setInitial(letter);
            //根据用户姓名拼音首字母条件进行查询
            List<TPhonebook> phoneBooks = baseService.findManyByPramas(queryTPhonebook);
            if(!phoneBooks.isEmpty()){
                map.put(letter,phoneBooks);
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",map);
        return R.ok(resultMap);
    }
    /**
     * 上传图片
     * @param file
     * @return  上传结果
     * @throws Exception
     */
    @RequestMapping("/uploadImage")
    public @ResponseBody Map<String,Object> uploadImage(MultipartFile file){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            map = QiniuUploadUtils.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("code",0);
            map.put("msg","服务器异常！！");
        }
        return map;
    }
}
