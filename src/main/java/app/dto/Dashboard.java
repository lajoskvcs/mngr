package app.dto;

public class Dashboard {
    private int projectNumber;
    private int inPlanTaskNumber;
    private int inProgressTaskNumber;
    private int doneTaskNumber;

    /**
     * Returns the number of the {@link app.model.Project Projects} for the currently authenticated {@link app.model.User User}
     * @return
     */
    public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    /**
     * Returns the number of the {@link app.model.Task Tasks} what are in <code>in plan</code> status for the currently authenticated {@link app.model.User User}
     * @return
     */
    public int getInPlanTaskNumber() {
        return inPlanTaskNumber;
    }

    public void setInPlanTaskNumber(int inPlanTaskNumber) {
        this.inPlanTaskNumber = inPlanTaskNumber;
    }

    /**
     * Returns the number of the {@link app.model.Task Tasks} what are in <code>in progress</code> status for the currently authenticated {@link app.model.User User}
     * @return
     */
    public int getInProgressTaskNumber() {
        return inProgressTaskNumber;
    }

    public void setInProgressTaskNumber(int inProgressTaskNumber) {
        this.inProgressTaskNumber = inProgressTaskNumber;
    }

    /**
     * Returns the number of the {@link app.model.Task Tasks} what are in <code>done</code> status for the currently authenticated {@link app.model.User User}
     * @return
     */
    public int getDoneTaskNumber() {
        return doneTaskNumber;
    }

    public void setDoneTaskNumber(int doneTaskNumber) {
        this.doneTaskNumber = doneTaskNumber;
    }
}
