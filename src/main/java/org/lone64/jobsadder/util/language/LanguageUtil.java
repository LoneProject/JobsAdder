package org.lone64.jobsadder.util.language;

import org.lone64.jobsadder.JobsAdder;

public class LanguageUtil {

    public static String getCurrentLanguage() {
        return "lang_" + JobsAdder.getMainConfig().getString("config-language");
    }

}
