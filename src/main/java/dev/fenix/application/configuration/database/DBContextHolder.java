package dev.fenix.application.configuration.database;

public class DBContextHolder {
    private static final ThreadLocal<DBEnum> contextHolder = new ThreadLocal<>();
    public static void setCurrentDb(DBEnum dbType) {

        contextHolder.set(dbType);

     // System.out.println("setCurrentDb -> " + dbType.name());

    }
    public static DBEnum getCurrentDb() {
        return contextHolder.get();
    }
    public static void clear() {
        contextHolder.remove();
    }
}