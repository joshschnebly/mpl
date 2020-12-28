if(CFG.'robocopy_web_destination_folder' != null){
    MPLModule('Deploy WebRobocopy', CFG)
}
else {
    MPLModule('Deploy Octopus', CFG)
}
