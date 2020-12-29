// withCredentials([string(credentialsId: CFG.'octopus_deploy_token', variable: 'APIKey')]) {
//     bat(label: "Push ${archiveName} to Octopus Deploy", script: """dotnet octo push --space SafePoint --package ${CFG.'archive_package_filepath'} --replace-existing --server ${octopusServerUrl} --apiKey ${APIKey} --debug""") 
//     bat(label: "Create ${CFG.'application_name'} v${CFG.'current_version_number'} release", script: """dotnet octo create-release --space SafePoint --project ${CFG.'application_name'} --version ${packageVersion} --packageVersion ${packageVersion} --releaseNotesFile "${CFG.release_notes_path'}" --ignoreExisting --server ${CFG.'octopus_deploy_url'} --apiKey ${APIKey} --debug""") 
// }
