bat "cd ${ClientSourceDirectory} yarn run build"

def packageManager = CFG.'package_manager'
if(packageManager == 'npm' || packageManager == 'yarn') {
  bat(label: "Restore NPM Packages via ${packageManager}", script: "cd ${CFG.'restore_folder'} & ${packageManager} run build") 
}
else {
  echo 'Error - MPL Restore Client- invalid package manager'
}
