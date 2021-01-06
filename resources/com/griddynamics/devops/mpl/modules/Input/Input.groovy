        // input {
        //   message "Input Parameters"
        //     parameters {
        //       booleanParam(name:'publish_package', defaultValue: false, description: 'Publish Models Package to Nuget')
        //       booleanParam(name:'publish_package_beta', defaultValue: false, description: 'Publish Models Beta Package to Nuget')
        //     }
        // }

        //     input(id: 'userInput', message: 'Merge to?',
        //      parameters: [[$class: 'ChoiceParameterDefinition', defaultValue: 'false', description:'Publish Models Package to Nuget']
        //      ])
//input message: 'Proceed with this step?', submitter: "${approvers}"
timeout(time: 1, unit: 'MINUTES') {
  def userInput = input message: 'User input required', parameters: [
    booleanParam(name: 'publish_package', defaultValue: 'false', description: 'Publish Models Package to Nuget'),
    booleanParam(name: 'publish_package_beta', defaultValue: 'false', description: 'Publish Models Beta Package to Nuget')
  ]
  OUT.'returns.publish_package' = userInput['publish_package']
  OUT.'returns.publish_package_beta' = userInput['publish_package_beta']
}



