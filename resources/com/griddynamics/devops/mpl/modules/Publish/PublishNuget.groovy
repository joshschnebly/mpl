echo "FAKE - Publish Nuget Package"
//withCredentials([string(credentialsId: CFG.'proget_token', variable: 'APIKey')]) {
//    bat (label: "Publish ${CFG.'nuget_archive'} Package to Nuget", script: "dotnet nuget push ${CFG.'nuget_archive'}.nupkg -s ${CFG.'proget_server_url'} -k ${APIKey}")
//}