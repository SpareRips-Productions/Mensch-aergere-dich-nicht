package de.spareripsproduction.tinyengine.logic;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Thomas Hampe <thomas@hampe.co>
 * @version 1.0
 * @since 2014-03-23
 */
public class GitRepository {

    String branch;

    String describe;

    String commitId;

    String buildUserName;

    String buildUserEmail;

    String buildTime;

    String commitUserName;

    String commitUserEmail;

    String commitMessageShort;

    String commitMessageFull;

    String commitTime;

    public GitRepository() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("git.properties"));
            this.branch = properties.get("git.branch").toString();
            this.describe = properties.get("git.commit.id.describe").toString();
            this.commitId = properties.get("git.commit.id").toString();
            this.buildUserName = properties.get("git.build.user.name").toString();
            this.buildUserEmail = properties.get("git.build.user.email").toString();
            this.buildTime = properties.get("git.build.time").toString();
            this.commitUserName = properties.get("git.commit.user.name").toString();
            this.commitUserEmail = properties.get("git.commit.user.email").toString();
            this.commitMessageShort = properties.get("git.commit.message.short").toString();
            this.commitMessageFull = properties.get("git.commit.message.full").toString();
            this.commitTime = properties.get("git.commit.time").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getBranch() {
        return branch;
    }

    public String getDescribe() {
        return describe;
    }

    public String getCommitId() {
        return commitId;
    }

    public String getBuildUserName() {
        return buildUserName;
    }

    public String getBuildUserEmail() {
        return buildUserEmail;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public String getCommitUserName() {
        return commitUserName;
    }

    public String getCommitUserEmail() {
        return commitUserEmail;
    }

    public String getCommitMessageShort() {
        return commitMessageShort;
    }

    public String getCommitMessageFull() {
        return commitMessageFull;
    }

    public String getCommitTime() {
        return commitTime;
    }
}
