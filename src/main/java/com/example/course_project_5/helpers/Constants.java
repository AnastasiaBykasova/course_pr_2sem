package com.example.course_project_5.helpers;

public class Constants {
    public class USER_TABLE {
        public static String table_name = "User";
        public static String id_column = "id";
        public static String login_columm = "login";
        public static String password_columm = "password_hash";
        public static String status_columm = "user_status";
        public static String level_columm = "user_level";
    }
    public class QUOTE_TABLE {
        public static String table_name = "Quote";
        public static String id_column = "id";
        public static String quote_text_column = "quote_text";
        public static String quote_date_column = "quote_date";
        public static String subject_column = "subject_name";
        public static String teacher_column = "teacher_name";
        public static String creator_column = "creator";
        public static String access_level_column = "access_level";
    }
}
