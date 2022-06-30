def call (Map config)
{ 
    
    node
        {
            
            /*dir("/var/jenkins_home/workspace/Front_main/source/DevOpsFront") {
                        stage('Packing') 

                        {
                            sh "cd ${config.dist}"
                            sh 'npm pack'
                             sh 'ls -a'
                            sh "ls -a ${config.dist}"

                        }*/
            
            /*stage('Publish') 
                { 
                //sh 'npm login --registry http://192.168.56.115:8081/repository/aosoFront/'
               // sh 'ng build ng-trc-lib'
                //sh 'npm publish /var/jenkins_home/workspace/Front_main/source/DevOpsFront/dev-ops-front-0.0.0.tgz'
                   //sh "npm token create"   
                       
                   //sh 'npm install --no-package-lock'
                   //sh "npm adduser --registry=http://192.168.56.115:8081/repository/aosoFront/ --always-auth"
                  //sh "npm login --registry=http://192.168.56.115:8081/repository/aosoFront/ publish /var/jenkins_home/workspace/Front_main/source/DevOpsFront/dev-ops-front-0.0.0.tgz"
                    //sh "npm login --registry http://192.168.56.115:8081/repository/aosoFront/"
                    //sh 'ng build'
                    //sh "npm publish /var/jenkins_home/workspace/Front_main/source/DevOpsFront/dev-ops-front-0.0.0.tgz"
                }*/
               stage('Creating our image'){      
                        //def version = "latest"
                        //sh 'docker build -f "${config.Dockerfile}" -t nexus_docker/aoso '
                        dir("${config.Dockerfile}")
                            {
                            dockerImage = docker.build "nexus-udd/aosofront" + ":latest" 
                            }
                    }
                        stage('push image in nexus'){      
                                //def version = "latest"
                                //sh 'docker build -f "${config.Dockerfile}" -t nexus_docker/aoso '
                                docker.withRegistry( 'http://192.168.56.115:8082/repository/nexus-udd', 'nexus-udd' ) { 
                                    dockerImage.push() 
                                } 
                             }
             
        

               }
        
        
}