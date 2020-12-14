def packageManager = CFG.'package_manager'

if(packageManager == 'npm' || packageManager == 'yarn') {
  def cdString = CFG.'client_folder' != null ? "cd ${CFG.'client_folder'} & " : ""
  echo "-${cdString} ${packageManager} install-"
  bat(label: "Restore NPM Packages via ${packageManager}", script: "${cdString} ${packageManager} install") 
}
else {
  echo 'Error - MPL Restore Client- invalid package manager'
}




