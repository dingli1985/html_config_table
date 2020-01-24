package web;

import org.apache.shiro.authc.CredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobanExceptionHandler {

	/**
	 * 账号密码错误
	 *
	 * @author fengshuonan
	 */
	@ExceptionHandler(CredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String credentials(CredentialsException e, Model model) {
		model.addAttribute("tips", "账号密码错误");
		return "/login";
	}

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public String methodNotSupported(HttpRequestMethodNotSupportedException e,Model model){
		model.addAttribute("tips", "请求方式不对");
		return "405";
	}
}
