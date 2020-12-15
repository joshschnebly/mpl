//CFG.'archive_name' = "builds\\${currentBuild.id}\\${CFG.'project_name'}.${CFG.'current_package_version'}.zip"
//CFG.'deployment_directory' = "${CFG.'project_name'}\\build\\${CFG.'build_type'}\\"
        
zip zipFile: "${CFG.'archive_name'}", archive: true,  dir: "${CFG.'deployment_directory'}", glob: '/**/*'
