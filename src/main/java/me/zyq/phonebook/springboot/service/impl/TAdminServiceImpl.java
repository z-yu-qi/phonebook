package me.zyq.phonebook.springboot.service.impl;

import me.zyq.phonebook.springboot.model.TAdmin;
import me.zyq.phonebook.springboot.service.TAdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author djin
 *    TAdmin业务层实现类
 * @date 2020-12-05 08:49:13
 */
@Service
@Transactional
public class TAdminServiceImpl extends BaseServiceImpl<TAdmin> implements TAdminService {
	
}
