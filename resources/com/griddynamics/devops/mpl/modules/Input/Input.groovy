input {
  message "Input Parameters"
    parameters {
      booleanParam(name:'publish_package', defaultValue: false, description: 'Publish Models Package to Nuget')
      booleanParam(name:'publish_package_beta', defaultValue: false, description: 'Publish Models Beta Package to Nuget')
    }
}
OUT.'returns.publish_package' = publish_package
OUT.'returns.publish_package_beta' = publish_package_beta