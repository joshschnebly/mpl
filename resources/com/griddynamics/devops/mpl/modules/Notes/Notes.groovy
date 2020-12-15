def releaseNotesPath = 'RELEASE_NOTES.md'
def projectFolderCd = ''

if(CFG.'project_folder' != null)
{
    releaseNotesPath = ".\\${CFG.'project_folder'}\\RELEASE_NOTES.md"
    projectFolderCd = "cd ${CFG.'project_folder'} & "
}

bat(label: 'Clear release notes', script: "if exist ${releaseNotesPath} del ${releaseNotesPath}")
env.GIT_REPOSITORY_URL = CFG.'git_repository_url'

withCredentials([usernamePassword(credentialsId: "${CFG.'jenkins_ghe_token'}", usernameVariable: 'GITHUB_USERNAME', passwordVariable: 'GITHUB_TOKEN')]) {
    bat(label: 'Generating Semantic Release Notes', script: "${projectFolderCd} npx semantic-release --branches ${env.BRANCH_NAME}")
}

