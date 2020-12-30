
echo "MPL Version Start CFG - ${CFG.toString()}"
echo "MPL Version Start OUT - ${OUT.toString()}"

def currentVersionNumber = ''
if (CFG.'previous_version_number' == null) {
    //Default versioning 
  currentVersionNumber = VersionNumber([projectStartDate: '2020-01-01', versionNumberString: '1.${BUILD_MONTH}.${BUILD_DAY}.${BUILDS_TODAY}'])  
  changeAsmVer(regexPattern: 'Assembly(\\w*)Version\\("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)"\\)', replacementPattern: 'Assembly$1Version("%s")', versionPattern: "${currentVersionNumber}")
}
else {
  //Semantic versioning
  def packageJsonPath = (CFG.'project_folder' ?: ".") + "\\package.json"
  currentVersionNumber = readJSON(file: "${packageJsonPath}").version
  changeAsmVer(regexPattern: 'Assembly(\\w*)Version\\("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.(\\*)"\\)', replacementPattern: 'Assembly$1Version("%s")', versionPattern: "${currentVersionNumber}.*")
}
//Current version number
OUT.'returns.current_version_number' = currentVersionNumber
bat(label: 'Current Version Number', script: "echo ${currentVersionNumber}")

//Current package version
if(CFG.'release_candidate_suffix' != null) {
  OUT.'returns.current_package_version' = "${currentVersionNumber}${CFG.'release_candidate_suffix'}"
  bat(label: 'Current Package Version', script: "echo ${OUT.'returns.current_package_version'}")
}

//Build Type Selected
if(CFG.'build_type' != null) {
  bat(label: 'Build Selected', script: "echo ${CFG.'build_type'}")
}

echo "MPL Version End CFG - ${CFG.toString()}"
echo "MPL Version End OUT - ${OUT.toString()}"


