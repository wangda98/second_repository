package com.cyberwise.workanalysis.common;

import java.io.Serializable;

/**
 * 自定义响应结构
 */
public class CyberResultInfo implements Serializable {

	private static final long serialVersionUID = -8763973654674770653L;

	// 响应业务状态
	private Integer code;

	// 响应消息
	private String msg;

	// 跳转目标
	private String target;

	// 响应中的数据
	private Object result;

	public CyberResultInfo(Integer code, String msg, Object result) {
		super();
		this.code = code;
		this.msg = msg;
		this.result = result;
	}

	public CyberResultInfo(Integer code, String msg, String target, Object result) {
		super();
		this.code = code;
		this.msg = msg;
		this.target = target;
		this.result = result;
	}

	public static CyberResultInfo ok() {
		return new CyberResultInfo(1, "ok", null);
	}

	public static CyberResultInfo ok(Object result) {
		return new CyberResultInfo(1, "ok", result);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
