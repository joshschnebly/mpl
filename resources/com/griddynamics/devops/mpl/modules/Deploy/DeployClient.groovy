def packageManager = CFG.'package_manager' ?: 'yarn'
//bat(label: "Deploy client package via ${packageManager}", script: "${packageManager} deploy") 
echo "FAKE - Deploy client package via ${packageManager}"