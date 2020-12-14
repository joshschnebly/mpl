def packageManager = CFG.'package_manager'
if(packageManager == 'npm' || packageManager == 'yarn') {
  bat(label: "Build NPM Packages via ${packageManager}", script: "cd ${CFG.'restore_folder'} & ${packageManager} run build") 
}
else {
  echo 'Error - MPL Build Client- invalid package manager'
}
