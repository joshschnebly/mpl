if(CFG.'web_robocopy_destination_folder' != null){
    MPLModule('Deploy WebRobocopy', CFG)
}
else {
    MPLModule('Deploy Octopus', CFG)
}
