package me.itay.punishmentsystem.Utils;

import java.util.Arrays;
import java.util.List;

public enum StaffLevelEnum {
    HELPER(new StaffLevelEnum[]{}),
    MOD(new StaffLevelEnum[]{HELPER}),
    ADMIN(new StaffLevelEnum[]{HELPER, MOD});;

    private final List<StaffLevelEnum> prev;

    StaffLevelEnum(StaffLevelEnum[] prev) {
        this.prev = Arrays.asList(prev);
    }

    public List<StaffLevelEnum> getPrev() {
        return prev;
    }
}
