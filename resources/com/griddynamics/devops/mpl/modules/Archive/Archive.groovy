def archiveFilePath = "${CFG.'deployment_directory'}\\${CFG.'archive_name'}"
if exist archiveFilePath del archiveFilePath
zip zipFile: "${CFG.'archive_name'}", archive: true,  dir: "${CFG.'deployment_directory'}", glob: '/**/*'