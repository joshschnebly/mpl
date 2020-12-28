bat "net use t: /delete /y & net use T: ${CFG.'robocopy_web_destination_folder'}"
bat "T: & cd . > app_offline.htm"
Robocopy("/MIR ${CFG.'output_directory'} ${CFG.'robocopy_web_destination_folder'} /xf app_offline.htm *.pfx")
bat "T: & del app_offline.htm & net use t: /delete /y"		  

