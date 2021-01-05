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
input name: 'Test', message: 'Publish Models Package to Nuget', parameters: [[$class: 'BooleanParameterDefinition', defaultValue: false, description: '', name: 'test']]
OUT.'returns.publish_package' = publish_package
OUT.'returns.publish_package_beta' = publish_package_beta