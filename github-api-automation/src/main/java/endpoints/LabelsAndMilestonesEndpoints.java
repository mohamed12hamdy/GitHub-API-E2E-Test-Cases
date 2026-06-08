package endpoints;

public class LabelsAndMilestonesEndpoints {
    // Labels Endpoints
    public static final String CREATE_LABEL = "/repos/{username}/{repo}/labels";
    public static final String ASSIGN_LABEL_TO_ISSUE = "/repos/{username}/{repo}/issues/{issueNumber}/labels";

    // Milestones Endpoints
    public static final String CREATE_MILESTONE = "/repos/{username}/{repo}/milestones";
    public static final String ASSIGN_MILESTONE_TO_ISSUE = "/repos/{username}/{repo}/issues/{issueNumber}";
}
