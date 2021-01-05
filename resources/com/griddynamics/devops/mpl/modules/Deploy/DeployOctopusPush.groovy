// withCredentials([string(credentialsId: CFG.'octopus_deploy_token', variable: 'APIKey')]) {
//     bat(label: "Push ${CFG.'archive_name'} to Octopus Deploy", script: """dotnet octo push --space ${CFG.'octopus_space'} --package ${CFG.'archive_package_filepath'} --replace-existing --server ${CFG.'octopus_deploy_url'} --apiKey ${APIKey} --debug""") 
// }