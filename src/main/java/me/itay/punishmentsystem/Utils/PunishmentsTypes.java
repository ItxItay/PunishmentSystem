package me.itay.punishmentsystem.Utils;

public enum PunishmentsTypes {
    KICK,
    MUTE,
    BAN,
    BANIP;

    public static PunishmentsTypes getByString(String punishType){
        try {
            return PunishmentsTypes.valueOf(punishType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
