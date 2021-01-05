bat "net use t: /delete /y & net use T: ${CFG.'web_robocopy_destination_folder'}"
bat "T: & cd . > app_offline.htm"
Robocopy("/MIR ${CFG.'output_directory'} ${CFG.'web_robocopy_destination_folder'} /xf app_offline.htm *.pfx")
bat "T: & del app_offline.htm & net use t: /delete /y"