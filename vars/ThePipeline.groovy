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

def call(body) {
  def MPL = MPLPipelineConfig(body, 
    [:]
      //modules: [
        //Startup: [
        //  application_name: '' //Needed for Semantic Startup   CashOps.Web
        //],
        //RestoreServer: [
        //  solution_filename: '' //'CashOps.sln'
        //],
        //RestoreClient: [
        //  package_manager: '',   //npm or yarn
        //  restore_folder: '' 
        //], 
        //Notes: [
        //  project_name: '', //'CashOps.Web' 
        //  jenkins_ghe_token: 'XXX',  //jenkins GHE API token 
        //  git_repository_url: ''   //'https://h2-vspghe01.corp.lfco.cc/CMS/CashOps.git' 
        //],
        //Version: [
        //  when_branch : ''  //'/^dev$|^release/.+/'
        //],
        //VersionServer: [:],
        //VersionClient: [:],
        //Build: [
        //  when_branch : ''  //'/^dev$|^release/.+/'
        //],
        //BuildServer: [
        //  project_folder : ''  //CashOps.Web
        //],
        //BuildClient: [:]
      //],
      //common : [ 
      //  previous_release_number: null,
      //  current_release_number: null
      //]  
    //]
    ,
    [
      modules: [
        Notes: [
          jenkins_ghe_token: 'usa_houston-svc_Octopus_BS-GHE-Token'  //jenkins GHE API token 
        ] 
      ]
    ]
  )
  println 'Before Pipeline' + MPL.config.toString()
  pipeline {
    agent any
    stages {
      stage( 'Startup' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          echo "Before Startup MPLModule: ${MPL.config.toString()}"
          MPLPipelineConfigMerge(MPLModule())
          echo "After Startup MPLModule: ${MPL.config.toString()}"
        }
      }
      stage( 'Restore' ) {
        parallel {    
           stage( 'Restore Server' ) {
            when { expression { MPLModuleEnabled() } }
            steps {
              MPLPipelineConfigMerge(MPLModule())
            }
          } 
          stage( 'Restore Client' ) {
            when { expression { MPLModuleEnabled() } }
            steps {
              MPLPipelineConfigMerge(MPLModule())
            }
          }
        }
      }    
      stage( 'Notes' ) {
        steps {
          MPLPipelineConfigMerge(MPLModule())
        }
      }
      stage( 'Version' ) {
        when { 
          expression { MPLModuleEnabled() } 
          branch pattern: "${MPL.moduleConfig('Version').'when_branch'}", comparator: "REGEXP"
        }
        parallel {     
          stage( 'Version Server' ) {
            when { expression { MPLModuleEnabled() } }
            steps {
              MPLPipelineConfigMerge(MPLModule())
              echo MPL.config.toString()
            }
          }
          stage( 'Version Client' ) {
            when { expression { MPLModuleEnabled() } }
            steps {
              MPLPipelineConfigMerge(MPLModule())
            }
          }
        }
      }
      stage( 'Build' ) {
        when { 
          expression { MPLModuleEnabled() } 
          branch pattern: "${MPL.moduleConfig('Build').'when_branch'}", comparator: "REGEXP"
        }
        parallel {     
          stage( 'Build Server' ) {
            when { expression { MPLModuleEnabled() } }
            steps {
              MPLPipelineConfigMerge(MPLModule())
            }
          }
          stage( 'Build Client' ) {
            when { expression { MPLModuleEnabled() } }
            steps {
              MPLPipelineConfigMerge(MPLModule())
            }
          }
        }
      }
      /*
      stage( 'Package' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
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
      stage( 'Archive' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
    */
    }
    post {
      always {  
        //MPLPostStepsRun('always')
        echo MPL.pipelineCode
      }
      //success {
        //MPLPostStepsRun('success')
      //}
      //failure {
        //MPLPostStepsRun('failure')
      //}
    }  
  }
}
