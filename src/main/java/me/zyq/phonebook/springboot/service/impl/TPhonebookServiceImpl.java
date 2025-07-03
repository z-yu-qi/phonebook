package me.zyq.phonebook.springboot.service.impl;

import me.zyq.phonebook.springboot.model.TPhonebook;
import me.zyq.phonebook.springboot.service.TPhonebookService;
import me.zyq.phonebook.springboot.utils.PinYinUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author djin
 *    TPhonebook业务层实现类
 * @date 2020-12-05 08:49:13
 */
@Service
@Transactional
public class TPhonebookServiceImpl extends BaseServiceImpl<TPhonebook> implements TPhonebookService {

    //重写父类BaseServiceImpl中的save方法
    @Override
    public String save(TPhonebook tPhonebook) throws Exception {
        //设置通讯录数据的姓氏首字母
        tPhonebook.setInitial(PinYinUtil.getFirstByName(tPhonebook.getName()));
        return super.save(tPhonebook);
    }

    //重写基础的修改方法
    @Override
    public String upd(TPhonebook tPhonebook) throws Exception {
        //设置名字的拼音首字母
        tPhonebook.setInitial(PinYinUtil.getFirstByName(tPhonebook.getName()));
        //执行父类中的修改方法
        return super.upd(tPhonebook);
    }
}
