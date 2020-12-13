if (CFG.'current_release_number' == null) {
    //Default versioning 
    OUT.'returns.current_release_number' = VersionNumber([projectStartDate: '2020-01-01', versionNumberString: '1.${BUILD_MONTH}.${BUILD_DAY}.${BUILDS_TODAY}'])  
}
echo "ReleaseNumber: ${OUT.'returns.current_release_number'}"
changeAsmVer(regexPattern: 'Assembly(\\w*)Version\\("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)"\\)', replacementPattern: 'Assembly$1Version("%s")', versionPattern: OUT.'shared.current_release_number')
