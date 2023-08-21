package org.lone64.jobsadder.job.main;

import org.lone64.jobsadder.job.prefix.JobPrefix;
import org.lone64.jobsadder.job.stage.JobMaximum;
import org.lone64.jobsadder.job.stage.JobStage;

import java.util.ArrayList;
import java.util.List;

public class Job {

    private final String name;
    private final String icon;
    private final String displayName;
    private final JobPrefix jobPrefix;
    private final JobMaximum jobMaximum;
    private final List<String> commands;
    private final List<JobStage> jobStages;

    public Job(String name, String icon, String displayName, JobPrefix jobPrefix, JobMaximum jobMaximum, List<String> commands) {
        this.name = name;
        this.icon = icon;
        this.displayName = displayName;
        this.jobPrefix = jobPrefix;
        this.jobMaximum = jobMaximum;
        this.commands = commands;
        this.jobStages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public JobPrefix getJobPrefix() {
        return jobPrefix;
    }

    public JobMaximum getJobMaximum() {
        return jobMaximum;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addJobStage(JobStage jobStage) {
        this.jobStages.add(jobStage);
    }

    public List<JobStage> getJobStages() {
        return jobStages;
    }

}
