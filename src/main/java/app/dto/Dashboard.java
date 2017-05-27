package app.dto;

public class Dashboard {
    /**
     * The number of the {@link app.model.Project Projects} for the currently authenticated {@link app.model.User User}
     */
    private int projectNumber;
    /**
     * The number of the {@link app.model.Task Tasks} what are in <code>in plan</code> status for the currently authenticated {@link app.model.User User}
     */
    private int inPlanTaskNumber;
    /**
     * The number of the {@link app.model.Task Tasks} what are in <code>in progress</code> status for the currently authenticated {@link app.model.User User}
     */
    private int inProgressTaskNumber;
    /**
     * The number of the {@link app.model.Task Tasks} what are in <code>done</code> status for the currently authenticated {@link app.model.User User}
     */
    private int doneTaskNumber;

    public Dashboard() {}

    public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    public int getInPlanTaskNumber() {
        return inPlanTaskNumber;
    }

    public void setInPlanTaskNumber(int inPlanTaskNumber) {
        this.inPlanTaskNumber = inPlanTaskNumber;
    }

    public int getInProgressTaskNumber() {
        return inProgressTaskNumber;
    }

    public void setInProgressTaskNumber(int inProgressTaskNumber) {
        this.inProgressTaskNumber = inProgressTaskNumber;
    }

    public int getDoneTaskNumber() {
        return doneTaskNumber;
    }

    public void setDoneTaskNumber(int doneTaskNumber) {
        this.doneTaskNumber = doneTaskNumber;
    }
}
