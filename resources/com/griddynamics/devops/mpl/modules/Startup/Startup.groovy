currentBuild.displayName = CFG.'display_name' ?: "${BRANCH_NAME}-${currentBuild.id}"
bat(label: 'Current Build', script: "echo Current Build : ${currentBuild.displayName}")

if(CFG.'previous_version_number' != null){
  OUT.'returns.previous_version_number' =  MPLModule('Startup Semantic').'returns.previous_version_number'
}