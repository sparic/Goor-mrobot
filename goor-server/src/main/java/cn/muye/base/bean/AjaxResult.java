package cn.muye.base.bean;

public class AjaxResult {
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_FAILED = 1;
	public static final int CODE_PARAM_ERROR = 2;

	private int code;
	private String message;
	private Object data;

	public AjaxResult(int code, Object data, String message) {
		this.code = code;
		this.message = message;
		this.data = (data == null?new Object():data);
	}

	public AjaxResult(int code, String message) {
		this.code = code;
		this.message = message;
		this.data = "";
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public int getCode(){return code;}

	public boolean isSuccess() {
		return CODE_SUCCESS == code;
	}

	public static final AjaxResult success() {
		return new AjaxResult(CODE_SUCCESS, "", "");
	}

	public static final AjaxResult success(Object data) {
		return new AjaxResult(CODE_SUCCESS, data, "");
	}

	public static final AjaxResult success(String msg) {
		return new AjaxResult(CODE_SUCCESS, "", msg);
	}

	public static final AjaxResult success(Object data, String message) {
		return new AjaxResult(CODE_SUCCESS, data, message);
	}

	public static final AjaxResult failed() {
		return new AjaxResult(CODE_FAILED, "", "");
	}

	public static final AjaxResult failed(String message) {
		return new AjaxResult(CODE_FAILED, "", message);
	}

	public static final AjaxResult failed(Object data, String message) {
		return new AjaxResult(CODE_FAILED, data, message);
	}

	public static final AjaxResult failed(int code, String message) {
		return new AjaxResult(code, message);
	}
	
	public static final AjaxResult failed(Object data) {
		return new AjaxResult(CODE_FAILED, data, "");
	}
	
}
