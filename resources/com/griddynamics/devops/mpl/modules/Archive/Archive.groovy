bat(label: 'Delete existing archive', script: """
    cd "${CFG.'deployment_directory'}" & 
    if exist "${CFG.'archive_name'}" del "${CFG.'archive_name'}"
""")
zip zipFile: "${CFG.'archive_name'}", archive: true,  dir: "${CFG.'deployment_directory'}", glob: '/**/*'