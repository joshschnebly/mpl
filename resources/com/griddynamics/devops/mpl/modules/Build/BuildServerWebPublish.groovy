echo "outputd-BuildServerWebPublish:${CFG.'output_directory'}"
bat "dotnet clean ${CFG.'solution_filename'} -c ${CFG.'configuration_property'} -r ${CFG.'runtime_identifier'} -v minimal"
bat "dotnet publish ${CFG.'projectfile_path'} -c ${CFG.'configuration_property'} -o ${CFG.'output_directory'} -r ${CFG.'runtime_identifier'} --self-contained true /p:UseAppHost=true" 
