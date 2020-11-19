package world.ucode.utils;

public class Enums {
    public enum UserChange {
        LOGIN("change_login"),
        PASSWORD("change_password"),
        BALANCE("change_balance"),
        LOGOUT("log_out"),
        PHOTO("change_photo");

        private final String value;

        UserChange(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
