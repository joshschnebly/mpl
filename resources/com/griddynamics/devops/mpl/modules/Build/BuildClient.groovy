def packageManager = CFG.'package_manager'

if(packageManager == 'npm' || packageManager == 'yarn') {
  def cdString = CFG.'client_folder' != null ? "cd ${CFG.'client_folder'} & " : ""
  bat(label: "Build NPM Packages via ${packageManager}", script: "${cdString} ${packageManager} run build") 
}
else {
  echo "Error - MPL Build Client- invalid package manager: ${packageManager}"
}
