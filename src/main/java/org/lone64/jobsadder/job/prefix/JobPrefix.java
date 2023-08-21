package org.lone64.jobsadder.job.prefix;

public class JobPrefix {

    private final boolean enabled;
    private final String prefix;
    private final String suffix;

    public JobPrefix(boolean enabled, String prefix, String suffix) {
        this.enabled = enabled;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

}
