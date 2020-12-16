if (CFG.'current_version_number' == null) {
    //Default versioning 
  OUT.'returns.current_version_number' = VersionNumber([projectStartDate: '2020-01-01', versionNumberString: '1.${BUILD_MONTH}.${BUILD_DAY}.${BUILDS_TODAY}'])  
  changeAsmVer(regexPattern: 'Assembly(\\w*)Version\\("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)"\\)', replacementPattern: 'Assembly$1Version("%s")', versionPattern: OUT.'returns.current_version_number')
}
else {
  //Semantic versioning
  def packageJsonPath = (CFG.'project_folder' ?: ".") + "\\package.json"
  OUT.'returns.current_version_number' = readJSON(file: "${packageJsonPath}").version
  changeAsmVer(regexPattern: 'Assembly(\\w*)Version\\("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.(\\*)"\\)', replacementPattern: 'Assembly$1Version("%s")', versionPattern: "${OUT.'returns.current_version_number'}.*")
}
bat(label: 'Current Version Number', script: "echo ${OUT.'returns.current_version_number'}")

if(CFG.'release_candidate_suffix' != null) {
  OUT.'returns.current_package_version' = "${currentVersionNumber}${CFG.'release_candidate_suffix'}"
  bat(label: 'Current Package Version', script: "echo ${packageVersion}")
}

if(CFG.'build_type' != null) {
  bat(label: 'Build Selected', script: "echo ${CFG.'build_type'}")
}


