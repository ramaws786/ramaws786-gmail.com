package com.mahendra.crudapp;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer{

	@Override
	public SpringApplicationBuilder  configure(SpringApplicationBuilder app)
	{
		
		return app.sources(CrudNoDbApplication.class);
	}
	
}
