package app.dto;

/**
 * This {@code Data Transfer Object} class represents statistical data for the application.
 */
public class Dashboard {
    /**
     * The number of the {@link app.model.Project Projects} for the currently authenticated {@link app.model.User User}.
     */
    private int projectNumber;
    /**
     * The {@link app.model.Task Tasks} what are in <code>in plan</code> status for the currently authenticated {@link app.model.User User}.
     */
    private int inPlanTaskNumber;
    /**
     * The number of the {@link app.model.Task Tasks} what are in <code>in progress</code> status for the currently authenticated {@link app.model.User User}.
     */
    private int inProgressTaskNumber;
    /**
     * The number of the {@link app.model.Task Tasks} what are in <code>done</code> status for the currently authenticated {@link app.model.User User}.
     */
    private int doneTaskNumber;

    /**
     * Returns the number of the {@link app.model.Project Projects} for the currently authenticated {@link app.model.User User}.
     * @return the number of the {@link app.model.Project Projects}
     */
    public int getProjectNumber() {
        return projectNumber;
    }

    /**
     * This method sets the number of the {@link app.model.Project Projects} for the currently authenticated {@link app.model.User User}.
     * @param projectNumber The number of the {@link app.model.Project Projects}
     */
    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    /**
     * Returns the number of the {@link app.model.Task Tasks} what are in <code>in plan</code> status for the currently authenticated {@link app.model.User User}.
     * @return The number of the {@link app.model.Task Tasks} what are in <code>in plan</code> status
     */
    public int getInPlanTaskNumber() {
        return inPlanTaskNumber;
    }

    /**
     * This method sets the number of the {@link app.model.Project Projects} for the currently authenticated {@link app.model.User User}.
     * @param inPlanTaskNumber The number of the {@link app.model.Task Tasks}
     */
    public void setInPlanTaskNumber(int inPlanTaskNumber) {
        this.inPlanTaskNumber = inPlanTaskNumber;
    }

    /**
     * Returns the number of the {@link app.model.Task Tasks} what are in <code>in progress</code> status for the currently authenticated {@link app.model.User User}.
     * @return The {@link app.model.Task Tasks} what are in <code>in progress</code> status
     */
    public int getInProgressTaskNumber() {
        return inProgressTaskNumber;
    }

    /**
     * This method sets the number of the {@link app.model.Project Projects} for the currently authenticated {@link app.model.User User}.
     * @param inProgressTaskNumber The number of the {@link app.model.Task Tasks}
     */
    public void setInProgressTaskNumber(int inProgressTaskNumber) {
        this.inProgressTaskNumber = inProgressTaskNumber;
    }

    /**
     * Returns the number of the {@link app.model.Task Tasks} what are in <code>done</code> status for the currently authenticated {@link app.model.User User}.
     * @return The number of the {@link app.model.Task Tasks} what are in <code>done</code> status
     */
    public int getDoneTaskNumber() {
        return doneTaskNumber;
    }

    /**
     * This method sets the number of the {@link app.model.Project Projects} for the currently authenticated {@link app.model.User User}.
     * @param doneTaskNumber The number of the {@link app.model.Task Tasks}
     */
    public void setDoneTaskNumber(int doneTaskNumber) {
        this.doneTaskNumber = doneTaskNumber;
    }
}
