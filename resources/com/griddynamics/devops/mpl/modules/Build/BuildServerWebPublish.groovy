bat "dotnet clean ${CFG.'solution_filename'} -c ${CFG.'configuration_property'} -v minimal"
bat "dotnet publish ${CFG.'projectfile_path'} -c ${CFG.'configuration_property'} -o ${CFG.'output_directory'} --self-contained true /p:UseAppHost=true" 