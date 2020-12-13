bat(label: 'Clear release notes', script: """
    if exist .\\${CFG.'project_name'}\\RELEASE_NOTES.md del .\\${CFG.'project_name'}\\RELEASE_NOTES.md
""")
env.GIT_REPOSITORY_URL = CFG.'git_repository_url'

//withCredentials([usernamePassword(credentialsId: "${CFG.'jenkins_ghe_token'}", usernameVariable: 'GITHUB_USERNAME', passwordVariable: 'GITHUB_TOKEN')]) {
    //bat(label: 'Generating Semantic Release Notes', script: "cd ${CFG.'project_name'} & npx semantic-release --branches ${env.BRANCH_NAME}")
//}

