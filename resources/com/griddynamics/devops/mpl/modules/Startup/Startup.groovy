scmSkip(deleteBuild: true, skipPattern:'.*\\[ci skip\\].*')
currentBuild.displayName = "${BRANCH_NAME}-${currentBuild.id}"
bat(label: 'Current Build', script: "echo Current Build : ${currentBuild.displayName}")
echo "s-${CFG.'project_folder'}"
if(CFG.'previous_release_number' != null){
  OUT.'returns.previous_release_number' =  MPLModule('Startup Semantic').'returns.previous_release_number'
}