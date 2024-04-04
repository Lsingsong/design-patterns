//package org.itstack.demo.design.agent;
//
//import org.itstack.demo.design.IUserDao;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanDefinitionHolder;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.beans.factory.support.GenericBeanDefinition;
//
//public class test implements BeanDefinitionRegistryPostProcessor {
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//        beanDefinition.setBeanClass(MapperFactoryBean.class);
//        beanDefinition.setScope("singleton");
//        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(IUserDao.class);
//
//        BeanDefinitionHolder userDao = new BeanDefinitionHolder(beanDefinition, "userDao");
//        BeanDefinitionReaderUtils.registerBeanDefinition(userDao, beanDefinitionRegistry);
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//
//    }
//}
