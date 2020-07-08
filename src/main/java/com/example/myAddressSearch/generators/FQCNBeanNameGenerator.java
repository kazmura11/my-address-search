package com.example.myAddressSearch.generators;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

public class FQCNBeanNameGenerator extends AnnotationBeanNameGenerator {
	@Override
	protected String buildDefaultBeanName(BeanDefinition definition) {
		return definition.getBeanClassName();
	}
}