package me.zyq.phonebook.springboot.controller;

import com.github.pagehelper.PageInfo;
import me.zyq.phonebook.springboot.service.BaseService;
import me.zyq.phonebook.springboot.service.TAdminService;
import me.zyq.phonebook.springboot.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author
 *    基础控制器层
 * @param <T>
 */

public class BaseController<T> {

	//访问数据成功的常量
	protected static final Integer SUCCESSCODE = 0;
	//访问数据失败的常量
	protected static final Integer FAILCODE = 200;
	//访问数据失败的数据条数
	protected static final Integer FAILCOUNT = 0;
	//访问数据失败时的提示
	protected static final String FAILMSG = "数据访问失败！！！";
	//操作成功的常量
	protected static final String SUCCESS = "success";
	//操作失败的常量
	protected static final String FAIL = "fail";
	//操作异常的常量
	protected static final String ERROR = "error";

	//日志对象
	protected final static Logger logger= LoggerFactory.getLogger(BaseController.class);
	
	//基础业务层对象
	@Autowired
	protected BaseService<T> baseService;

	//加入系统用户业务层对象
	@Autowired
	protected TAdminService tAdminService;

	/**
	 * 
	 * @param code  1 成功  0 失败  
	 * @param msg   消息内容
	 * @param count 最大条数
	 * @param data  具体内容
	 * @return
	 */
	public Map<String,Object> putMsgToJsonString(int code,String msg,int count ,Object data){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("count", count);
		map.put("data", data);
		return map;
	}

	/**
	 * 加载所有
	 */
	@RequestMapping("/loadAll")
	@ResponseBody
	public List<T> loadAll(){
		List<T> list = new ArrayList<T>();
		try {
			return baseService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据主键id加载单个数据
	 */
	@RequestMapping("/loadTById")
	@ResponseBody
	public T loadTById(Integer id){
		try {
			return baseService.findTById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 加载（分页） 根据是否存在条件加载
	 */
	@RequestMapping("/listByPramas")
	@ResponseBody
	public Map<String, Object> listByPramas(Integer page,Integer limit,@RequestBody T t){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map = baseService.findListByPramas(page, limit, t);
			map.put("code", SUCCESSCODE);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return putMsgToJsonString(FAILCODE,FAILMSG,FAILCOUNT,null);
		}
	}

	/**
	 * 加载（分页） 根据是否存在条件加载
	 */
	@RequestMapping("/pageByPramas")
	@ResponseBody
	public PageInfo<T> pageByPramas(Integer page, Integer limit,@RequestBody T t){
		try {
			return baseService.findPageByPramas(page,limit,t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据条件查询单个结果
	 * 
	 */
	@RequestMapping("/loadObjectByPramas")
	@ResponseBody
	public T loadObjectByPramas(@RequestBody T t){
		try {
			return baseService.findObjectByPramas(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	//获取表的数据记录条数
	@RequestMapping("/getTotalByPramas")
	@ResponseBody
	public Long getTotalByPramas(@RequestBody T t){
		try {
			return baseService.getTotalByPramas(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	};
	
	/**
	 * 根据条件删除
	 * 
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(@RequestBody T t){
		try {
			return baseService.remove(t);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping("/deletes")
	@ResponseBody
	public String deletes(Integer[] ids){
		try {
			return baseService.removeBatch(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
	}
	
	/**
	 * 添加
	 */
	 @RequestMapping("/saveT")
	 @ResponseBody
	 public String saveT(@RequestBody T entity){
		try {
			return baseService.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}	 
	 }
	 
	 /**
	 * 修改
	 */
	 @RequestMapping("/updT")
	 @ResponseBody
	 public String updT(@RequestBody T entity){
		try {
			return baseService.upd(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}	 
	 }
	
}
