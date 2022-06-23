def call (Map config)
{ 
    
    node
        {
            env
                    {
                        NPM_TOKEN: ${{ secrets.NPM_TOKEN }}
                    }
            dir("/var/jenkins_home/workspace/Front_main/source/DevOpsFront") {
                        stage('Packing') 

                        {
                            
                            sh "cd ${config.dist}"
                            sh 'npm pack'
                             sh 'ls -a'
                            sh "ls -a ${config.dist}"

                        }
            
            stage('Publish') 
                { 
                //sh 'npm login --registry http://192.168.56.115:8081/repository/aosoFront/'
               // sh 'ng build ng-trc-lib'
                //sh 'npm publish /var/jenkins_home/workspace/Front_main/source/DevOpsFront/dev-ops-front-0.0.0.tgz'
                   sh "npm token create"   
                       
                   sh 'npm install --no-package-lock'
                   sh "npm adduser --registry=http://192.168.56.115:8081/repository/aosoFront/ --always-auth"

                    sh "npm login --registry=http://192.168.56.115:8081/repository/aosoFront/ publish /var/jenkins_home/workspace/Front_main/source/DevOpsFront/dev-ops-front-0.0.0.tgz"
                
                }
        }
        }
        
}