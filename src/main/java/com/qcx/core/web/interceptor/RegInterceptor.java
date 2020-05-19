package com.qcx.core.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 
* @ClassName: RegInterceptor
*
 */
@Configuration
public class RegInterceptor implements WebMvcConfigurer {
	@Autowired
	private AppContextInterceptor appContextInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(appContextInterceptor);
	}
}
