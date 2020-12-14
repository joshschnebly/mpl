echo "ss-${CFG.'project_folder'}"
echo "ss-${CFG.toString()}"
OUT.'common.previous_release_number' = readJSON(file: "${CFG.'project_folder'}\\package.json").version
bat(label: 'Previous Release', script: "echo ${OUT.'common.previous_release_number'}")


