def packageJsonPath = (${CFG.'project_folder'} ?: ".") + "\\package.json"
OUT.'returns.previous_version_number' = readJSON(file: "${packageJsonPath}").version
bat(label: 'Previous Release', script: "echo ${OUT.'returns.previous_version_number'}")


