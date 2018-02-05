package com.company.project.exception;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.project.core.ResponseData;

@Controller
public class GlobalErrorController implements ErrorController{

//	private static final String ERROR_VIEW = "common/error";
	
	private final static String ERROR_PATH = "/error";

	private static final Logger logger = LoggerFactory
            .getLogger(GlobalErrorController.class);
    
    /**
     * Error Attributes in the Application
     */
    @Autowired
    private ErrorAttributes errorAttributes;

//    /**
//     * Supports the HTML Error View
//     *
//     * @param request
//     */
//    @RequestMapping(value = ERROR_PATH, produces=MediaType.TEXT_HTML_VALUE)
//    public ModelAndView errorHtml(HttpServletRequest request) {
//    		return new ModelAndView(ERROR_VIEW, this.getErrorAttributes(request, false));
//    }

    /**
     * Supports other formats like JSON, XML
     *
     * @param request
     */
    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public ResponseData<String> error(HttpServletRequest request) {
		Map<String, Object> body = this.getErrorAttributes(request,
        this.getTraceParameter(request));
	    String message = body.get("message").toString();
		return new ResponseData<String>(this.getStatus(request).value(), message, message);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                   boolean includeStackTrace) {
        Map<String, Object> map = this.errorAttributes
                .getErrorAttributes(new ServletRequestAttributes(request), includeStackTrace);
        logger.error("map is [{}]", map);
        return map;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            return HttpStatus.valueOf(statusCode);
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
