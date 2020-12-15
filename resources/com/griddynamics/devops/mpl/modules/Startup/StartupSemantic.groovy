OUT.'shared.previous_version_number' = readJSON(file: "${CFG.'project_folder'}\\package.json").version
bat(label: 'Previous Release', script: "echo ${OUT.'shared.previous_version_number'}")


