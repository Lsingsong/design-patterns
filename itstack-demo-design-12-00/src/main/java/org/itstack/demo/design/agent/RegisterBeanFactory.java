package org.itstack.demo.design.agent;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;

public class RegisterBeanFactory implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//        //方式一
//        RootBeanDefinition beanDefinition = new RootBeanDefinition(IUserDao.class);
//        registry.registerBeanDefinition("userDao", beanDefinition);
        //方式二
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(MapperFactoryBean.class);
        genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(IUserDao.class);

        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(genericBeanDefinition, "userDao");
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
