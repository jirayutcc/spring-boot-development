package com.spring.development.constant;

public class Constant {

    public static class DateTimeFormat {
        public static final String DATE_TIME_FORMAT_WITH_SPACE = "yyyy-MM-dd HH:mm:ss.SSS";
        public static final String DATE_TIME_FORMAT_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        private DateTimeFormat() {}
    }

    public enum Action {
        CREATE,
        UPDATE,
        DELETE
    }
    public enum Flag {
        Y,
        N
    }
}
