def gitCommitShort = bat(label: 'Extract short commit hash', script: "@git rev-parse --short ${GIT_COMMIT}", returnStdout: true).trim()
def configurationPropery = CFG.'configuration_property' ?: 'release'
def releaseNumber = "${CFG.'current_version_number'}"
def informationalVersion = "${releaseNumber}.${gitCommitShort}"
def modelProjectName = "${CFG.'models_package_project_folder'}"
def modelProject = "${modelProjectName}\\${modelProjectName}.csproj"
def solutionName = "${CFG.'solution_filename'}"
def modelProjectNuGetSuffix = ''

if (CFG.'publish_package_beta') {
  modelProjectNuGetSuffix = "-beta${currentBuild.id}" 
}

bat(label: "Build and Package ${modelProjectName}", script: """
	dotnet build ${solutionName} --configuration ${configurationPropery} -p:Version=\"${releaseNumber}\" -p:FileVersion=\"${releaseNumber}\" -p:InformationalVersion=\"${informationalVersion}\"
	dotnet pack ${modelProject} --output \"${WORKSPACE}\" --verbosity quiet --configuration ${configurationPropery} -p:Version=\"${releaseNumber}${modelProjectNuGetSuffix}\" -p:FileVersion=\"${releaseNumber}\" -p:InformationalVersion=\"${informationalVersion}\""
""")
archiveArtifacts artifacts: '*.nupkg', fingerprint: false