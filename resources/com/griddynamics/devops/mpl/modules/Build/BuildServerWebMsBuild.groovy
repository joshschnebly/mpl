def configurationPropery = CFG.'configuration_property' ?: 'release'
def packageVersionValue = "${CFG.'current_version_number'}"
def projectName = "${CFG.'project_name'}"
def projectPath = "${projectName}\\${projectName}.csproj"
def deploymentDirectory = "${WORKSPACE}\\Deployment"

bat(label: "Build and Package ${projectName}.csproj", script: "\"${tool 'Visual Studio 2019'}\\MSBuild.exe\" ${projectPath} /p:Configuration=${configurationPropery};Platform=\"AnyCPU\";ReleaseVersion=\"${packageVersionValue}\";PackageVersion=\"${packageVersionValue}\";publishUrl=\"${deploymentDirectory}\";WebPublishMethod=FileSystem /t:WebPublish /p:VisualStudioVersion=15.0")