def packageManager = CFG.'package_manager'

if(packageManager == 'npm' || packageManager == 'yarn') {
  def clientFolder = CFG.'client_folder' != null ? "cd ${CFG.'client_folder'} & " : ""
  def postRunBuildArgs = CFG.'post_run_build_args' != null ? ":${CFG.'post_run_build_args'}" : ""
  
  bat(label: "Build Client via ${packageManager}", script: "${clientFolder} ${packageManager} run build${postRunBuildArgs}") 
}
else {
  echo "Error - MPL Build Client- invalid package manager: ${packageManager}"
}

