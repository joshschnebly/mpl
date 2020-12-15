projectFolder = "${CFG.'project_folder'}" ?: "."
OUT.'returns.previous_version_number' = readJSON(file: "${projectFolder}\\package.json").version
bat(label: 'Previous Release', script: "echo ${OUT.'returns.previous_version_number'}")


