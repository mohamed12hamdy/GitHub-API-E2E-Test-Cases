package endpoints;

public class IssuesEndpoints {
    public static final String CREATE_ISSUE = "/repos/{username}/{repo}/issues";
    public static final String GET_ISSUE = "/repos/{username}/{repo}/issues/{issueNumber}";
    public static final String UPDATE_ISSUE = "/repos/{username}/{repo}/issues/{issueNumber}";
    public static final String ADD_COMMENT_TO_ISSUE = "/repos/{username}/{repo}/issues/{issueNumber}/comments";
    public static final String CLOSE_ISSUE = "/repos/{username}/{repo}/issues/{issueNumber}";
}
