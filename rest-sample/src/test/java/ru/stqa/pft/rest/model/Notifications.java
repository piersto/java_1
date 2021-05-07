package ru.stqa.pft.rest.model;

public class Notifications {
    private boolean creator;
    private boolean assignee;
    private boolean following;

    public boolean isCreator() {
        return creator;
    }

    public void setCreator(boolean creator) {
        this.creator = creator;
    }

    public boolean isAssignee() {
        return assignee;
    }

    public void setAssignee(boolean assignee) {
        this.assignee = assignee;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}
