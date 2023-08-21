package org.lone64.jobsadder.util.vault;

import org.lone64.jobsadder.JobsAdder;

public class API {

    public static VaultUtil getVault() {
        return JobsAdder.getInstance().getUtil();
    }

}
