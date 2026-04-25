# GitHub API — E2E Test Cases

Manual API test scenarios for testing GitHub REST API endpoints.

> 🔜 Automation with RestAssured — In Progress

---

## 📋 Test Coverage

| Category | Scenarios |
|----------|-----------|
| Repository Lifecycle | 5 |
| Issues Lifecycle | 6 |
| Branches & Pull Requests | 7 |
| Labels & Milestones | 4 |
| Authentication & Permissions | 4 |
| **Total** | **26** |

---

## 🔗 Base URL & Headers

```
Base URL: https://api.github.com
```

```
Authorization: Bearer YOUR_TOKEN
Accept: application/vnd.github+json
X-GitHub-Api-Version: 2026-03-10
```

---

## 🗂️ SCENARIO 1: Repository Lifecycle

### TC-01 — Create a Repository
- **Method:** `POST`
- **Endpoint:** `/user/repos`
- **Body:**
```json
{
  "name": "test-repo",
  "description": "E2E test repo",
  "private": false,
  "auto_init": true
}
```
- **Expected:** `201 Created` — response contains `"name": "test-repo"`

---

### TC-02 — Get the Repository
- **Method:** `GET`
- **Endpoint:** `/repos/:owner/test-repo`
- **Body:** None
- **Expected:** `200 OK` — `full_name` matches `owner/test-repo`

---

### TC-03 — Update Repository Description
- **Method:** `PATCH`
- **Endpoint:** `/repos/:owner/test-repo`
- **Body:**
```json
{
  "description": "Updated description"
}
```
- **Expected:** `200 OK` — `description` field updated

---

### TC-04 — Delete the Repository
- **Method:** `DELETE`
- **Endpoint:** `/repos/:owner/test-repo`
- **Body:** None
- **Expected:** `204 No Content`

---

### TC-05 — Get Deleted Repository ❌ Negative
- **Method:** `GET`
- **Endpoint:** `/repos/:owner/test-repo`
- **Body:** None
- **Expected:** `404 Not Found`

---

## 🐛 SCENARIO 2: Issues Lifecycle

> Prerequisite: repo exists

### TC-06 — Create an Issue
- **Method:** `POST`
- **Endpoint:** `/repos/:owner/:repo/issues`
- **Body:**
```json
{
  "title": "Bug: Login fails on mobile",
  "body": "Steps to reproduce: ...",
  "labels": ["bug"]
}
```
- **Expected:** `201 Created` — returns `issue_number`

---

### TC-07 — Get the Issue
- **Method:** `GET`
- **Endpoint:** `/repos/:owner/:repo/issues/:issue_number`
- **Body:** None
- **Expected:** `200 OK` — `title` matches, `state` is `"open"`

---

### TC-08 — Add a Comment to the Issue
- **Method:** `POST`
- **Endpoint:** `/repos/:owner/:repo/issues/:issue_number/comments`
- **Body:**
```json
{
  "body": "This is a test comment"
}
```
- **Expected:** `201 Created`

---

### TC-09 — Update the Issue Title
- **Method:** `PATCH`
- **Endpoint:** `/repos/:owner/:repo/issues/:issue_number`
- **Body:**
```json
{
  "title": "Bug: Login fails on mobile [UPDATED]"
}
```
- **Expected:** `200 OK` — `title` updated

---

### TC-10 — Close the Issue
- **Method:** `PATCH`
- **Endpoint:** `/repos/:owner/:repo/issues/:issue_number`
- **Body:**
```json
{
  "state": "closed"
}
```
- **Expected:** `200 OK` — `state` is `"closed"`

---

### TC-11 — Create Issue Without Title ❌ Negative
- **Method:** `POST`
- **Endpoint:** `/repos/:owner/:repo/issues`
- **Body:**
```json
{
  "body": "No title provided"
}
```
- **Expected:** `422 Unprocessable Entity`

---

## 🔀 SCENARIO 3: Branches & Pull Requests

### TC-12 — Get Default Branch SHA
- **Method:** `GET`
- **Endpoint:** `/repos/:owner/:repo/git/ref/heads/main`
- **Body:** None
- **Expected:** `200 OK` — returns `object.sha`

---

### TC-13 — Create a New Branch
- **Method:** `POST`
- **Endpoint:** `/repos/:owner/:repo/git/refs`
- **Body:**
```json
{
  "ref": "refs/heads/feature-branch",
  "sha": "SHA_FROM_TC12"
}
```
- **Expected:** `201 Created`

---

### TC-14 — Create a File on the New Branch
- **Method:** `PUT`
- **Endpoint:** `/repos/:owner/:repo/contents/test.md`
- **Body:**
```json
{
  "message": "Add test file",
  "content": "SGVsbG8gV29ybGQ=",
  "branch": "feature-branch"
}
```
> `content` must be **Base64 encoded** — `SGVsbG8gV29ybGQ=` = `Hello World`

- **Expected:** `201 Created`

---

### TC-15 — Create a Pull Request
- **Method:** `POST`
- **Endpoint:** `/repos/:owner/:repo/pulls`
- **Body:**
```json
{
  "title": "Feature: Add test file",
  "body": "This PR adds a test markdown file",
  "head": "feature-branch",
  "base": "main"
}
```
- **Expected:** `201 Created` — returns `pull_number`

---

### TC-16 — Get the Pull Request
- **Method:** `GET`
- **Endpoint:** `/repos/:owner/:repo/pulls/:pull_number`
- **Body:** None
- **Expected:** `200 OK` — `state` is `"open"`

---

### TC-17 — Merge the Pull Request
- **Method:** `PUT`
- **Endpoint:** `/repos/:owner/:repo/pulls/:pull_number/merge`
- **Body:**
```json
{
  "commit_title": "Merge feature-branch into main",
  "merge_method": "merge"
}
```
- **Expected:** `200 OK` — `"merged": true`

---

### TC-18 — Merge Already Merged PR ❌ Negative
- **Method:** `PUT`
- **Endpoint:** `/repos/:owner/:repo/pulls/:pull_number/merge`
- **Body:** None
- **Expected:** `405 Method Not Allowed`

---

## 🏷️ SCENARIO 4: Labels & Milestones

### TC-19 — Create a Label
- **Method:** `POST`
- **Endpoint:** `/repos/:owner/:repo/labels`
- **Body:**
```json
{
  "name": "critical",
  "color": "FF0000",
  "description": "Critical priority"
}
```
- **Expected:** `201 Created`

---

### TC-20 — Assign Label to an Issue
- **Method:** `POST`
- **Endpoint:** `/repos/:owner/:repo/issues/:issue_number/labels`
- **Body:**
```json
{
  "labels": ["critical"]
}
```
- **Expected:** `200 OK`

---

### TC-21 — Create a Milestone
- **Method:** `POST`
- **Endpoint:** `/repos/:owner/:repo/milestones`
- **Body:**
```json
{
  "title": "v1.0 Release",
  "due_on": "2025-12-31T00:00:00Z"
}
```
- **Expected:** `201 Created` — returns `milestone_number`

---

### TC-22 — Assign Milestone to Issue
- **Method:** `PATCH`
- **Endpoint:** `/repos/:owner/:repo/issues/:issue_number`
- **Body:**
```json
{
  "milestone": MILESTONE_NUMBER
}
```
- **Expected:** `200 OK`

---

## 🔐 SCENARIO 5: Authentication & Permissions

### TC-23 — Get Authenticated User
- **Method:** `GET`
- **Endpoint:** `/user`
- **Body:** None
- **Expected:** `200 OK` — `login` matches your GitHub username

---

### TC-24 — Request Without Token ❌ Negative
- **Method:** `GET`
- **Endpoint:** `/user`
- **Headers:** No Authorization header
- **Body:** None
- **Expected:** `401 Unauthorized`

---

### TC-25 — Access Private Repo Without Permission ❌ Negative
- **Method:** `GET`
- **Endpoint:** `/repos/someother/private-repo`
- **Body:** None
- **Expected:** `404 Not Found`

> GitHub returns 404 instead of 403 to hide the existence of private repos.

---

### TC-26 — Check Rate Limit
- **Method:** `GET`
- **Endpoint:** `/rate_limit`
- **Body:** None
- **Expected:** `200 OK` — returns `limit`, `remaining`, `reset`

---

## 📊 Full Summary Table

| TC | Scenario | Method | Endpoint | Expected |
|----|----------|--------|----------|----------|
| 01 | Create Repo | POST | `/user/repos` | 201 |
| 02 | Get Repo | GET | `/repos/:owner/:repo` | 200 |
| 03 | Update Repo | PATCH | `/repos/:owner/:repo` | 200 |
| 04 | Delete Repo | DELETE | `/repos/:owner/:repo` | 204 |
| 05 | Get Deleted Repo ❌ | GET | `/repos/:owner/:repo` | 404 |
| 06 | Create Issue | POST | `/repos/:owner/:repo/issues` | 201 |
| 07 | Get Issue | GET | `/repos/:owner/:repo/issues/:id` | 200 |
| 08 | Comment on Issue | POST | `/repos/:owner/:repo/issues/:id/comments` | 201 |
| 09 | Update Issue | PATCH | `/repos/:owner/:repo/issues/:id` | 200 |
| 10 | Close Issue | PATCH | `/repos/:owner/:repo/issues/:id` | 200 |
| 11 | Create Issue No Title ❌ | POST | `/repos/:owner/:repo/issues` | 422 |
| 12 | Get Branch SHA | GET | `/repos/:owner/:repo/git/ref/heads/main` | 200 |
| 13 | Create Branch | POST | `/repos/:owner/:repo/git/refs` | 201 |
| 14 | Create File on Branch | PUT | `/repos/:owner/:repo/contents/:path` | 201 |
| 15 | Create PR | POST | `/repos/:owner/:repo/pulls` | 201 |
| 16 | Get PR | GET | `/repos/:owner/:repo/pulls/:id` | 200 |
| 17 | Merge PR | PUT | `/repos/:owner/:repo/pulls/:id/merge` | 200 |
| 18 | Merge Merged PR ❌ | PUT | `/repos/:owner/:repo/pulls/:id/merge` | 405 |
| 19 | Create Label | POST | `/repos/:owner/:repo/labels` | 201 |
| 20 | Assign Label | POST | `/repos/:owner/:repo/issues/:id/labels` | 200 |
| 21 | Create Milestone | POST | `/repos/:owner/:repo/milestones` | 201 |
| 22 | Assign Milestone | PATCH | `/repos/:owner/:repo/issues/:id` | 200 |
| 23 | Get Auth User | GET | `/user` | 200 |
| 24 | No Token ❌ | GET | `/user` | 401 |
| 25 | No Permission ❌ | GET | `/repos/other/private` | 404 |
| 26 | Check Rate Limit | GET | `/rate_limit` | 200 |

