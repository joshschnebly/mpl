if (CFG.'common.current_release_number' == null) {
    OUT.'common.current_release_number' = VersionNumber([projectStartDate: '2020-01-01', versionNumberString: '1.${BUILD_MONTH}.${BUILD_DAY}.${BUILDS_TODAY}'])  
}
echo OUT.'common.current_release_number'
changeAsmVer(regexPattern: 'Assembly(\\w*)Version\\("([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+)"\\)', replacementPattern: 'Assembly$1Version("%s")', versionPattern: OUT.'common.current_release_number')
