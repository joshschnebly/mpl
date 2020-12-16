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
  def MPL = MPLPipelineConfig(body, [:], [:])
 
  pipeline {
    agent any
    parameters {
      booleanParam(name:'publish_package', defaultValue: false, description: 'Publish Models Package to Nuget')
      booleanParam(name:'publish_package_beta', defaultValue: false, description: 'Publish Models Beta Package to Nuget')
    }
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
          branch pattern: "${MPL.moduleConfig('VersionServer').'when_branch'}", comparator: "REGEXP"
        }
        steps { 
          MPLModule() 
          echo "After Version Server: ${MPL.config.toString()}"
          
          }
      }
      stage('Build') {
        when { 
          expression { MPLModuleEnabled() } 
          branch pattern: "${MPL.moduleConfig('Build').'when_branch'}", comparator: "REGEXP"
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
        steps {
          MPLModule()
          echo "After Archive: ${MPL.config.toString()}"
        }
      }
      /*
      stage( 'Test' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
      stage( 'Deploy' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
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
