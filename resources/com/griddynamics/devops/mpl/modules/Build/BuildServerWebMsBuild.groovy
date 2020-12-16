def configurationPropery = "${CFG.'configuration_property'}"
def packageVersionValue = "${CFG.'current_version_number'}"
def webProjectName = "${CFG.'web_project_name'}"
def projectPath = "${webProjectName}\\${webProjectName}.csproj"
def deploymentDirectory = "${WORKSPACE}\\Deployment"

bat(label: "Build and Package ${webProjectName}.csproj", script: "\"${tool 'Visual Studio 2019'}\\MSBuild.exe\" ${projectPath} /p:Configuration=${configurationPropery};Platform=\"AnyCPU\";ReleaseVersion=\"${packageVersionValue}\";PackageVersion=\"${packageVersionValue}\";publishUrl=\"${deploymentDirectory}\";WebPublishMethod=FileSystem /t:WebPublish /p:VisualStudioVersion=15.0")