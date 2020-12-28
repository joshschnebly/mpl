bat "net use t: /delete /y & net use T: ${CFG.'deployment_folder'}"
bat "T: & cd . > app_offline.htm"
robocopy("/MIR ${CFG.'output_directory'} ${CFG.'deployment_folder'} /xf app_offline.htm *.pfx")
bat "T: & del app_offline.htm & net use t: /delete /y"		  



