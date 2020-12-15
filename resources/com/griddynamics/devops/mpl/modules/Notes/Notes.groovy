def releaseNotesPath = ${CFG.'project_folder'} ? ".\\${CFG.'project_folder'}\\RELEASE_NOTES.md": "RELEASE_NOTES.md"

bat(label: 'Clear release notes', script: "if exist ${releaseNotesPath} del ${releaseNotesPath}")
env.GIT_REPOSITORY_URL = CFG.'git_repository_url'

withCredentials([usernamePassword(credentialsId: "${CFG.'jenkins_ghe_token'}", usernameVariable: 'GITHUB_USERNAME', passwordVariable: 'GITHUB_TOKEN')]) {
    bat(label: 'Generating Semantic Release Notes', script: "cd ${CFG.'project_folder'} & npx semantic-release --branches ${env.BRANCH_NAME}")
}

