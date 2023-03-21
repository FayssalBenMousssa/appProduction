package dev.fenix.application;


import static dev.fenix.application.api.accounting.CustomerAccountResource.getString;

public class CharSequence {
    public static void main(String[] params) throws Exception {
        ///    lowercase
       // System.out.println(CharLettering("ZZZ", "uppercase"));
        System.out.println(CharLettering("AAZ", "uppercase"));
        System.out.println(CharLettering("AZZ", "uppercase"));
        System.out.println(CharLettering("AZA", "uppercase"));
        System.out.println(CharLettering("aza", "lowercase"));
        System.out.println(CharLettering("dfc", "lowercase"));
    }

    /**
     * @param letter
     * @param letterCase = uppercase  || lowercase
     * @return letter
     * @throws Exception
     */
    private static String CharLettering(String letter, String letterCase) throws Exception {
        return getString(letter, letterCase);
    }


}
