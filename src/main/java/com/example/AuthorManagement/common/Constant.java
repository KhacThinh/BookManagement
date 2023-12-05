package com.example.AuthorManagement.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Constant {
    private Constant() {
        throw new UnsupportedOperationException("Đây là một lớp tiện ích và không thể khởi tạo được");
    }

    public static final String LOCAL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    public static final String CONFIG_FILE_FORMAT = "config-%s%s.properties";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String CUSTOM_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String MESSAGE_NOT_FOUND = "Resource not found";

    public static final String REGEX_EMAIL =
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static Date DATE_PATTERN(String patternDate, Date dateType) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(patternDate);
        Date currentDate = dateType;
        String formattedDate = dateFormat.format(currentDate);
        try {
            return dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
