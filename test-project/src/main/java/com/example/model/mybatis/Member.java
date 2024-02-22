package com.example.model.mybatis;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	String usrId;
	String primary;
	String stfId;
	String auth;
	String dispName;
	String imagePath;
	String chkUse;
	String templateId;
	String createDate;
	String lastLoginDatetime;
	String pwRefreshDate;
	String loginFailCnt;
	String otdId;
	String ipAdres;
	String ipCeckYn;
}



