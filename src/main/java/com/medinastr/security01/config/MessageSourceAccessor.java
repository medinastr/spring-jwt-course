package com.medinastr.security01.config;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceAccessor {

  private static MessageSource messageSource;

  public MessageSourceAccessor(MessageSource messageSource) {
    MessageSourceAccessor.messageSource = messageSource;
  }

  public static void setMessageSource(MessageSource messageSource) {
    MessageSourceAccessor.messageSource = messageSource;
  }

  public static String getNoArgsMessage(String key) {
    return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
  }
}
