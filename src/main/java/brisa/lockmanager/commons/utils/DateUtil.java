package brisa.lockmanager.commons.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public final class DateUtil extends DateUtils {

    public static final String MMM = "MMM";
    public static final String YYYY = "yyyy";
    public static final String HH_MM = "HH:mm";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String HH_MM_SS_A = "hh:mm:ss a";
    public static final String HH_MM_A = "hh:mm a";
    public static final String YYYY_MM_DD_TIMESTAMP_HH_MM_SS = "yyyy/MM/dd " + HH_MM_SS; // Data Table sort pattern
    public static final String DD_MMMM_YYYY = "dd, MMMM yyyy ";
    public static final String DD_MMMM_YYYY_HH_MM = DD_MMMM_YYYY + HH_MM;
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd " + HH_MM_SS;
    public static final String YYYY_MM_DD_T_HH_MM_SS_SSSXXX = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    // ---------------------------------------------------------------------------------------------
    // Constructors.
    // ---------------------------------------------------------------------------------------------
    private DateUtil() {
        super();
    }

    public static Date getCurrentDate() {
        final Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static boolean isGreaterThan(final Date yourDate, final Date anotherDate) {
        return compare(yourDate, anotherDate) > 0;
    }

    public static boolean isGreaterOrEqualsThan(final Date yourDate, final Date anotherDate) {
        return compare(yourDate, anotherDate) >= 0;
    }

    public static boolean isLessThan(final Date yourDate, final Date anotherDate) {
        return compare(yourDate, anotherDate) < 0;
    }

    public static boolean isLessOrEqualsThan(final Date yourDate, final Date anotherDate) {
        return compare(yourDate, anotherDate) <= 0;
    }

    public static boolean isEqualsThan(final Date yourDate, final Date anotherDate) {
        return compare(yourDate, anotherDate) == 0;
    }

    private static int compare(final Date currentDate, final Date comparedDate) {
        return currentDate.compareTo(comparedDate);
    }

    public static Date toLastTimeOfDay(Date date) {

        date = setHours(date, 23);
        date = setMinutes(date, 59);
        date = setSeconds(date, 59);
        date = setMilliseconds(date, 999);
        return date;
    }

    public static Date toFirstTimeOfDay(Date date) {

        date = setHours(date, 0);
        date = setMinutes(date, 0);
        date = setSeconds(date, 0);
        date = setMilliseconds(date, 0);
        return date;
    }

    public static boolean isOutOfRange(final Date start, final Date end, final int rangeInDays) {

        final long daysBetween = ChronoUnit.DAYS.between(
                new Timestamp(start.getTime()).toLocalDateTime(),
                new Timestamp(end.getTime()).toLocalDateTime());
        return daysBetween > rangeInDays;
    }

    public static int getDay(final Date date) {
        return get(Calendar.DAY_OF_MONTH, date);
    }

    public static Integer getDayOfWeek(final Date target) {
        if (target == null) {
            return null;
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(target);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getYear(final Date date) {
        return get(Calendar.YEAR, date);
    }

    public static int getMonth(final Date date) {
        return get(Calendar.MONTH, date);
    }

    public static int getHours(final Date date) {
        return get(Calendar.HOUR_OF_DAY, date);
    }

    public static int getMinutes(final Date date) {
        return get(Calendar.MINUTE, date);
    }

    public static int getSeconds(final Date date) {
        return get(Calendar.SECOND, date);
    }

    public static int get(final int dateField, final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateField);
    }

    public static Date setTime(Date date, String time) {

        if (date == null || time == null) {
            return null;
        }
        time = normalizeTime(time);
        date = DateUtils.setHours(date, LocalTime.parse(time).getHour());
        date = DateUtils.setMinutes(date, LocalTime.parse(time).getMinute());
        return date;
    }

    public static Date add(final Date date, final int value, final ChronoUnit unit) {
        final Instant instant = date.toInstant().plus(value, unit);
        return Date.from(instant);
    }

    public static Date atStartOfDay() {
        return DateUtil.atStartOfDay(LocalDate.now());
    }

    public static Date atStartOfDay(final Date date) {
        final LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date atStartOfDay(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalTime getTime(final Date date) {
        return LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault())
                .toLocalTime();
    }

    public static String getTimeStr(final Date date) {
        return getTime(date).toString();
    }

    public static LocalTime toLocalTime(final String time) {

        if (StringUtil.isNotEmpty(time)) {
            if(is12HourPattern(time)){
                return LocalTime.parse(normalizeTime(time), DateTimeFormatter.ofPattern(DateUtil.HH_MM_A));
            }
            return LocalTime.parse(normalizeTime(time));
        }
        return null;
    }

    private static String normalizeTime(final String time) {
        if(is12HourPattern(time)){
            return StringUtils.leftPad(time, 8, "0");
        }
        return StringUtils.leftPad(time, 5, "0");
    }

    public static String parseString(final Date date, final String pattern) {
        if (date == null || pattern == null) {
            throw new IllegalArgumentException();
        }
        final DateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static Date parseDate(final String date) {
        try {
            return new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS).parse(date);
        } catch (final ParseException e) {
            return null;
        }
    }

    public static boolean is12HourPattern(final String time) {
        return time.contains("PM") || time.contains("AM");
    }

    public static long getTimestampDiff(final Timestamp older, final Timestamp newer, final TimeUnit timeUnit) {
        final long diffMillis = newer.getTime() - older.getTime();
        return timeUnit.convert(diffMillis, TimeUnit.MILLISECONDS);
    }

    public static long getDiff(final Date older, final Date newer, final TimeUnit timeUnit) {
        return DateUtil.getTimestampDiff(
                new Timestamp(older.getTime()), new Timestamp(newer.getTime()), timeUnit);
    }

    public static long getDiffDays(final Instant start, final Instant end, final ZoneId zoneId, final boolean startOfDay) {
        final LocalDateTime dtStart = LocalDateTime.ofInstant(start, zoneId);
        final LocalDateTime dtEnd = LocalDateTime.ofInstant(end, zoneId);
        if (startOfDay) {
            return ChronoUnit.DAYS.between(dtStart.with(LocalTime.MIN), dtEnd.with(LocalTime.MIN));
        }
        return ChronoUnit.DAYS.between(start, end);
    }
}
