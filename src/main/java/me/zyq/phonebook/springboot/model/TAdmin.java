package me.zyq.phonebook.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 
 * @author djin
 *    TAdmin实体类
 * @date 2020-12-05 08:49:13
 */
@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class TAdmin implements Serializable{

	  private static final long serialVersionUID = 1L;
	
      //用户主键id
	  private Integer id;
      //用户名
	  private String username;
      //密码
	  private String password;

	  @Override
	  public String toString() {
		  return  ReflectionToStringBuilder.toString(this);
	  }

}
