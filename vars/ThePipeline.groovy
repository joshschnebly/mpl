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
      jenkins_ghe_token: 'usa_houston-jschnebly-GHE-Token',
      git_repository_url: gitUrl,
      solution_filename: "${gitRepositoryName}.sln",
      models_package_project_name: "${gitRepositoryName}.Models"
    ], 
    [:])

  def branches_version_build_test = MPL.config.'branches_version_build_test' ?: '.*'
  def when_branches_version_server = MPL.config.'when_branches_version_server' ?: branches_version_build_test
  def when_branches_build = MPL.config.'when_branches_build' ?: branches_version_build_test
  def when_branches_test = MPL.config.'when_branches_test' ?: branches_version_build_test
  
  pipeline {
    agent any
    //parameters {
        //booleanParam(name:'publish_package', defaultValue: false, description: 'Publish Models Package to Nuget')
        //booleanParam(name:'publish_package_beta', defaultValue: false, description: 'Publish Models Beta Package to Nuget')
    //}
    stages {
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
        steps { 
          echo "Before VersionServer: ${MPL.config.toString()}"
          MPLModule() 
          echo "After VersionServer: ${MPL.config.toString()}"
        }
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
      stage( 'Deploy' ) {
        when { expression { MPLModuleEnabled() } }
        steps { MPLModule() }
      }
      /*
      stage( 'Publish' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
    */
    }
    post {
      always {  
        MPLPostStepsRun('always')
        echo MPL.pipelineCode
      }
      success {
        MPLPostStepsRun('success')
      }
      failure {
        MPLPostStepsRun('failure')
      }
    }  
  }
}

/*
ThePipeline {
  modules = [
    Startup: [:],
    StartupSemantic: [
      project_folder: //'SafeOps.Web' 
    ],
    Restore: [:],
    RestoreServer : [
      solution_filename: //'SafeOps.sln'
    ],
    RestoreClient : [
      package_manager: //'yarn',
      client_folder: //'SafeOps.Web',
	    output_directory: //"Deployments\\SafeOps.Web\\ClientApp\\dist"   //optional
    ],
    Notes : [
      git_repository_url: //'https://h2-vspghe01.corp.lfco.cc/jschnebly/SafeOps.git',
      jenkins_ghe_token: //'usa_houston-jschnebly-GHE-Token',
      project_folder: //'SafeOps.Web',  //optional
      master_branch: //'master'
    ],
    VersionServer : [
      project_folder: //'SafeOps.Web',             						//optional
      release_candidate_suffix: isDevBranch ? '-rc' : ''        		//optional
    ],
    Build : [:],
    BuildClient: [
      package_manager: //'npm',
      client_folder: //'SafeOps.Web',  									//optional
      post_run_build_args: //''   										//optional
    ],
	  BuildServerWebMsBuild: [
      solution_filename: //'SafeOps.sln',
      configuration_property: //'release',
      web_build_type: //'WebPublish',
      runtime_identifier: //'win-x64',
      projectfile_path: //SafeOps.Web\\SafeOps.Web.csproj",
      project_name: //'SafeOps.Web'
    ],
    BuildServerWebPublish : [
      configuration_property: //'release',
      web_project_name: //'SafeOps.Web',
      web_build_type: //'WebMsBuild',
      solution_filename: //'SafeOps.sln'
    ],
    BuildServerModels : [
      configuration_property: //'release',
      models_package_project_name: //'Stratum.Harmony.Models',
      solution_filename: 'Stratum.Harmony.sln'
    ],
    Archive : [
      project_name: //'SafeOps.Web'
    ]
    solution_filename: //'SafeOps.sln'
  ]
  previous_version_number = //''  										//optional for Semantic Versioning
  current_version_number = ''
  //current_package_version = '' 										//output field for VersionServer
  build_type =  isDevBranch ? 'test' : 'prod'  							//optional for Advanced Build

}
*/