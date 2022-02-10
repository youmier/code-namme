package com.thoughtworks.codename

import com.thoughtworks.codename.service.CodeNameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CodenameApplication


fun main(args: Array<String>) {

	val runApplication = runApplication<CodenameApplication>(*args)
	val bean = runApplication.beanFactory.getBean("codeNameService") as CodeNameService

	bean.parseAndOutput()

}
