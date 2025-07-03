package me.zyq.phonebook.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 
 * @author djin
 *    TPhonebook实体类
 * @date 2020-12-05 08:49:13
 */
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class TPhonebook implements Serializable{

	  private static final long serialVersionUID = 1L;
	
      //联系人id
	  private Integer id;
      //联系人姓名
	  private String name;
      //手机号码
	  private String phonenumber;
      //座机电话号码
	  private String telenumber;
      //工作单位地址
	  private String workaddress;
      //家庭地址
	  private String homeaddress;
      //头像
	  private String image;
      //备注其他
	  private String remark;
      //姓名首字母
	  private String initial;

	  @Override
	  public String toString() {
		  return  ReflectionToStringBuilder.toString(this);
	  }

}
