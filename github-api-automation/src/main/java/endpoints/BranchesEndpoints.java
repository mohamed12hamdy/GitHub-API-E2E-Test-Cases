package endpoints;

public class BranchesEndpoints {
    public static final String CREATE_BRANCH = "/repos/{username}/{repo}/git/refs";
    public static final String GET_DEFAULT_BRANCH = "/repos/{username}/{repo}/git/ref/heads/master";
    public static final String CREATE_FILE_IN_BRANCH = "/repos/{username}/{repo}/contents/{filepath}";
    public static final String CREATE_PULL_REQUEST = "/repos/{username}/{repo}/pulls";
    public static final String GET_PULL_REQUEST = "/repos/{username}/{repo}/pulls/{pullNumber}";
    public static final String MERGE_PULL_REQUEST = "/repos/{username}/{repo}/pulls/{pullNumber}/merge";
}
