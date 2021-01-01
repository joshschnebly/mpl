//def releaseNotesFlag = CFG.'release_notes_path' != null ? " --releaseNotesFile ${CFG.release_notes_path'} " : ''
// withCredentials([string(credentialsId: CFG.'octopus_deploy_token', variable: 'APIKey')]) {
//     bat(label: "Create ${CFG.'application_name'} v${CFG.'current_version_number'} release", script: """dotnet octo create-release --space ${octopus_space} --project ${CFG.'application_name'} --version ${CFG.'current_package_version'} --packageVersion ${CFG.'current_package_version'} "${releaseNotesFlag}" --ignoreExisting --server ${CFG.'octopus_deploy_url'} --apiKey ${APIKey} --debug""") 
// }
