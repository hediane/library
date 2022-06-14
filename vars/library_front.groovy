def call (Map config)
{
         
            /*stage('location of dockerfile') 
                {  
                    sh "docker build -t teeeeeest -f ${config.dockerfileLocation} ."
                    echo "checking out the source dockerfile "
                    //echo "${config.dockerfileLocation}",
                }*/
            /*stage ('copy all file from FRONT')
            {    
                 sh "ls -la ${pwd()}"
                 sh "ls -la ${pwd()}/source"
                 sh "ls -la ${pwd()}/source/DevOpsFront/"
                 //sh "cd /var/jenkins_home/workspace/"
                 sh "mkdir -p ${config.DestinationFileFront} "
                 //sh "rm -rf /var/jenkins_home/workspace/aoso/DevOps/back"
                 sh "ls -a ${config.DestinationFolder1}"
                 sh "ls -a ${config.DestinationFolder2}"
                 sh "ls -a ${config.DestinationFileFront}"
                 //sh "rm -rf ${config.DestinationFile}"
                 //sh "cp -r ${pwd()}/source/DevOpsProject ${config.DestinationFile}"
                 //sh "rm -rf ${config.DestinationFileFront}/Dockerfile"
                 sh "cp -r ${pwd()}${config.FrontPath} ${config.DestinationFileFront}"
                 sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFileFront}"
                 sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFileFront}"
                 sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFileFront}"
                 sh "cp -r ${pwd()}${config.dockerComposeSonarQube} ${config.dockerComposeSonarQubeDestintion}"
                 sh "cp -r ${pwd()}/source/DevOpsFront/package.json ${config.DestinationFileFront}"
                 sh "cp -r ${pwd()}/source/DevOpsFront/package-lock.json ${config.DestinationFileFront}"
                 sh "ls -la ${config.DestinationFileFront} "
            }*/
           /* stage('Container of SonarQube') 
                { // sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQubeDestintion} build "

                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQube} up -d"
                    echo "Buid Image with docker-compose sonar "
                    //echo "${config.dockerfileLocation}",
                }*/
         stage('Quality Gate') 
                {   def scannerHome = tool name: 'sonarscanner';
                    withSonarQubeEnv('sonarQube') {
                    //sh "dotnet restore source/DevOpsProject/DevOpsProject/DevOpsProject.csproj"
                            dir("${config.FrontPath}") {
                                    sh " ls -la ${pwd()}"
                                    //sh "dotnet ${scannerHome}/bin/sonar-scanner  /k:AosoFront /d:sonar.host.url= http://192.168.56.113:9000"
                                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Aosofront -Dsonar.login=a650a854dfc5fdfd835f432b6cbf52f369f6a2b1 -Dsonar.scm.provider=git "
                                    //sh "${scannerHome}/bin/sonar-scanner -D /k:Aoso -D /d:sonar.host.url=http://192.168.56.113:9000  -D /d:sonar.login=ab9f339761ec69b84c33072c739b28b604d3f8ce "
                                   
                                    //sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Aosofront "
                                    
                            }
                }
                }
           /* stage('location of docker-compose') 
                {  
                    sh "${config.dockerComposeLocation} -f ${config.DestinationFileFront}/docker-compose-front.yml up -d --build "
                    echo "Build Image with docker-compose "
                    //echo "${config.dockerfileLocation}",
                }
            stage('GetUserJenkins') 
                {  
                    wrap([$class: 'BuildUser']) {
                    def user = env.BUILD_USER_ID
                    echo "${user}"
                    //def list = ['AmaniGHADDAB']
                    echo "${config.devValidator}"
                    if ("${config.devValidator}".contains("${user}"))
                    {
                        echo "Validate"
                    }
                    else
                    {
                           
                        //slackSend color: 'danger', channel: '#devops', message: "<${currentBuild.absoluteUrl}|Server build ${env.BUILD_NUMBER}> failed to deploy build "
	                       echo"Don't have access"
                    }
                 }
        }*/
               
            
    }

}