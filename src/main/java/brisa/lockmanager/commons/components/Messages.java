package brisa.lockmanager.commons.components;

import static brisa.lockmanager.commons.utils.StringUtil.EMPTY_STRING;
import static brisa.lockmanager.commons.utils.StringUtil.REGEX_CURLY_BRACES;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Messages {

    @Autowired
    protected MessageSource messageSource;

    public String get(final String messageKey, final Object... args) {
        final Locale locale = LocaleContextHolder.getLocale();
        return this.get(locale, messageKey, args);
    }

    public String get(final Locale locale, String messageKey, final Object... args) {

        messageKey = messageKey.replaceAll(REGEX_CURLY_BRACES, EMPTY_STRING);
        if (args != null && args.length > 0) {
            return this.messageSource.getMessage(messageKey, args, messageKey, locale);
        }
        return this.messageSource.getMessage(messageKey, null, messageKey, locale);
    }

}