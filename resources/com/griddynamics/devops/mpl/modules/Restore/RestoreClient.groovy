def packageManager = CFG.'package_manager' ?: 'yarn'
def cdString = CFG.'client_folder' != null ? "cd ${CFG.'client_folder'} & " : ""

bat(label: "Restore NPM Packages via ${packageManager}", script: "${cdString} ${packageManager} install") 




