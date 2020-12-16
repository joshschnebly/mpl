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

MPLPostStep('always') {
  if (env.BRANCH_NAME != CFG.'master_branch' && CFG.'previous_version_number' != CFG.'current_version_number') {
    withCredentials([usernamePassword(credentialsId: "${CFG.'jenkins_ghe_token'}", usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS')]) {
    bat(label: "Clear git tags", script: """
        git tag -d v${CFG.'current_version_number'}
        git push https://$GIT_USER:$GIT_PASS@${GIT_REPOSITORY_URL} -d v${CFG.'current_version_number'}
    """)
    }
  }
}


