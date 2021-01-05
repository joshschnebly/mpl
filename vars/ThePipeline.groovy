//
// Copyright (c) 2018 Grid Dynamics International, Inc. All Rights Reserved
// https://www.griddynamics.com
//
// Classification level: Public
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// $Id: $
// @Project:     MPL
// @Description: Shared Jenkins Modular Pipeline Library
//
//echo "Before Pipeline: ${MPL.config.toString()}"

def call(body) {
  def gitUrl = scm.getUserRemoteConfigs()[0].getUrl()
  def gitRepositoryName = gitUrl.substring(gitUrl.lastIndexOf("/") + 1, gitUrl.length()-4)

  def MPL = MPLPipelineConfig(body, 
    [
      git_repository_name: gitRepositoryName,   
      git_repository_url: gitUrl,
      application_name: gitRepositoryName,    
      solution_filename: "${gitRepositoryName}.sln",
      models_package_project_name: "${gitRepositoryName}.Models",
      jenkins_ghe_token: 'usa_houston-jschnebly-GHE-Token',
      octopus_deploy_url: 'http://h2-voctopus01:80',
      octopus_deploy_token: 'octopus-deploy-token'
    ], 
    [:])

  def branches_version_build_test_deploy = MPL.config.'branches_version_build_test_deploy' ?: '.*'
  def when_branches_version_server = MPL.config.'when_branches_version_server' ?: branches_version_build_test_deploy
  def when_branches_build = MPL.config.'when_branches_build' ?: branches_version_build_test_deploy
  def when_branches_test = MPL.config.'when_branches_test' ?: branches_version_build_test_deploy
  def when_branches_deploy = MPL.config.'when_branches_deploy' ?: branches_version_build_test_deploy
  
  pipeline {
    agent any
    //parameters {
        //booleanParam(name:'publish_package', defaultValue: false, description: 'Publish Models Package to Nuget')
        //booleanParam(name:'publish_package_beta', defaultValue: false, description: 'Publish Models Beta Package to Nuget')
    //}
    stages {
      stage('Input'){
        when { expression { MPLModuleEnabled() } }
        steps { 
          MPLModule()
        }
      }
      stage('ScmSkip') {
        when { expression { MPLModuleEnabled() } }
        steps { 
          MPLModule()    
        }
      }
      stage('Startup') {
        when { expression { MPLModuleEnabled() } }
        steps { MPLModule() }
      }
      stage('Restore') {
        when { expression { MPLModuleEnabled() } }
        parallel {    
          stage('Restore Server') {
            when { expression { MPLModuleEnabled() } }
            steps { MPLModule() }
          } 
          stage('Restore Client') {
            when { expression { MPLModuleEnabled() } }
            steps { MPLModule() }
          }
        }
      }    
      stage('Notes') {
        when { expression { MPLModuleEnabled() } }
        steps { MPLModule() }
      }
      stage('Version Server') {
        when { 
          expression { MPLModuleEnabled() } 
          branch pattern: when_branches_version_server, comparator: "REGEXP"
        }
        steps { MPLModule() }
      }
      stage('Build') {
        when { 
          expression { MPLModuleEnabled() } 
          branch pattern: when_branches_build, comparator: "REGEXP"
        }
        parallel {    
          stage('Build Server') {
            when { expression { MPLModuleEnabled() } }
            steps { MPLModule() }
          }
          stage('Build Server Models') {
            when { expression { MPLModuleEnabled() } }
            steps { MPLModule() }
          } 
          stage('Build Client') {
            when { expression { MPLModuleEnabled() } }
            steps { MPLModule() }
          }
        }
      }
      stage('Archive') {
        when { expression { MPLModuleEnabled() } }
        steps { MPLModule() }
      }  
      stage( 'Test' ) {
        when { 
          expression { MPLModuleEnabled() } 
          branch pattern: when_branches_test, comparator: "REGEXP"
        }
        steps { MPLModule() }
      }
      stage( 'Publish' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
      stage( 'Deploy' ) {
        when { 
          expression { MPLModuleEnabled() } 
          branch pattern: when_branches_deploy, comparator: "REGEXP"
        }
        steps { 
          MPLModule() 
        }
      }
    }
    post {
      always {  
        MPLPostStepsRun('always')
        echo MPL.pipelineCode
      }
      success {
        MPLPostStepsRun('success')
      }
      changed {
        MPLPostStepsRun('changed')
      }
      failure {
        MPLPostStepsRun('failure')
      }
    }  
  }
}
