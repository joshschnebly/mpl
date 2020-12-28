if(CFG.'robocopy_web_destination_folder' != null){
    MPLModule('DeployWebRobocopy', CFG)
}
else {
    MPLModule('DeployOctopus', CFG)
}
