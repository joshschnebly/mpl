bat "dotnet clean ${CFG.'solution_filename'} -c ${CFG.'configuration_property'} -r ${CFG.'runtime_identifier'} -v minimal"

echo "CRN-${CFG.'current_release_number'}"
def outputDirectory = CFG.'current_release_number' != null ? "${CFG.'output_directory'}.${CFG.'current_release_number'}" : "${CFG.'output_directory'}" 
bat "dotnet publish ${CFG.'projectfile_path'} -c ${CFG.'configuration_property'} -o ${outputDirectory} -r ${CFG.'runtime_identifier'} --self-contained true /p:UseAppHost=true" 
