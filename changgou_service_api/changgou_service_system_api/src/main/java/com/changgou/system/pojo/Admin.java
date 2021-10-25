package com.changgou.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * admin实体类
 *
 */

@Data
@NoArgsConstructor
@Table(name="tb_admin")
public class Admin implements Serializable {

	@Id
	private Integer id;//id
	private String loginName;//用户名
	private String password;//密码
	private String status;//状态

	public Admin(String loginName, String status) {
		this.loginName = loginName;
		this.status = status;
	}
}
