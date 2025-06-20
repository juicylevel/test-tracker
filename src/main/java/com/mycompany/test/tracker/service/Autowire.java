package com.mycompany.test.tracker.service;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class Autowire {

  private static final Logger logger = LoggerFactory.getLogger(Autowire.class);
  private static final Object mutex = new Object();
  private static final Autowire INSTANCE = getInstance();

  private final ConcurrentHashMap<Class<? extends Bean>, Bean> beans;

  private Autowire() {
    beans = new ConcurrentHashMap<>();
  }

  public static <T extends Bean> T autowire(Class<T> beanClass) {
    return INSTANCE.inject(beanClass);
  }

  private static Autowire getInstance() {
    Autowire autowire = INSTANCE;
    if (autowire == null) {
      synchronized (mutex) {
        autowire = INSTANCE;
        if (autowire == null) {
          autowire = new Autowire();
        }
      }
    }
    return autowire;
  }

  @SuppressWarnings("unchecked")
  private <T extends Bean> T inject(Class<T> beanClass) {
    return (T) beans.computeIfAbsent(beanClass, Autowire::createNewInstance);
  }

  @NotNull
  private static <T extends Bean> T createNewInstance(Class<T> beanClass) {
    try {
      return beanClass.getDeclaredConstructor().newInstance();
    } catch (ReflectiveOperationException e) {
      logger.error("can't instantiate bean {} by {}", beanClass, e.getMessage(), e);
      throw new IllegalArgumentException("can't instantiate bean %s by %s".formatted(beanClass, e.getMessage()), e);
    }
  }

  interface Bean {
  }
}
