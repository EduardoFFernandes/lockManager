package brisa.lockmanager.commons.utils;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil extends StringUtils {

    public static final String NULL = "null";
    public static final String EMPTY_STRING = "";
    public static final String PIPE = "|";
    public static final String REGEX_PIPE = "\\|";
    public static final String DOLLAR_SIGN = "$";
    public static final String HASH_TAG = "#";
    public static final String REGEX_DOLLAR_SIGN = "\\$";
    public static final String SEMICOLON = ";";
    public static final String SIGN_PLUS = "+";
    public static final String SYMBOM_AT_SIGN = "@";
    public static final String REGEX_BREAK_TAB_LINE = "[\n\t]";
    public static final String BREAK_CR_LF = "\r\n";
    public static final String REGEX_BREAK_CR_LF = "[\r\n]";
    public static final String REGEX_CURLY_BRACES = "[{}]";
    public static final String REGEX_NON_ASCII = "[^\\p{ASCII}]";
    private static final String RANDOM_KEY_RANGE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // ---------------------------------------------------------------------------------------------
    // Constructors.
    // ---------------------------------------------------------------------------------------------
    private StringUtil() {
        super();
    }

    public static boolean isEmpty(final String string) {
        return string == null || string.isEmpty() || string.trim().equals(NULL) ? true : false;
    }

    public static boolean isNotEmpty(final String string) {
        return !isEmpty(string);
    }

    public static String removeSpecialsCharacters(final String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll(REGEX_NON_ASCII, EMPTY_STRING);
    }

    public static String getCurrencyString(final Locale locale, final BigDecimal value) {
        final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(value);
    }

    public static String generateRandomKey(final int size) {
        final SecureRandom rand = new SecureRandom();
        final StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(RANDOM_KEY_RANGE.charAt(rand.nextInt(RANDOM_KEY_RANGE.length())));
        }
        return sb.toString();
    }
}