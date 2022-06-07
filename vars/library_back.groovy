def call (Map config)
{
    node
        { 
            stage('check source scmurl')
            {
                echo "checking out the source scmurl "
                echo "${config.scmurl}"
            }
           stage("testCheckout") {
            checkout([$class: 'GitSCM', 
            branches: [[name: 'refs/heads/main']], 
            userRemoteConfigs: [[
                //refspec: '+refs/tags/*:refs/remotes/origin/tags/*',
                url:"${config.scmurl}"]]
               
        ])
         }
            /*stage('location of dockerfile') 
                {  
                    sh "docker build -t teeeeeest -f ${config.dockerfileLocation} ."
                    echo "checking out the source dockerfile "
                    //echo "${config.dockerfileLocation}",
                }*/
            /*stage('Container of SonarQube') 
                {  //sh "${config.dockerComposeLocation} -f ${config.dockerComposeElasticDestintination} build "
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQubeDestintion} up -d"
                    echo "Buid Image with docker-compose"
                    //echo "${config.dockerfileLocation}",
                }*/
            stage('SonarQube Analysis') 
                {  
                    
                    withSonarQubeEnv('sonarQube') {
                    //sh "dotnet restore source/DevOpsProject/DevOpsProject/DevOpsProject.csproj"
                    dir("source/${config.ProjectName}") {
                    sh " ls -la ${pwd()}"
                    def scannerHome = tool name: 'sonarscanner';
                    //sh "dotnet ${scannerHome}/bin/sonar-scanner begin /k: Aoso /d:sonar.host.url= http://192.168.56.113:9000"
                    //sh sonar-scanner -Dsonar.projectKey=Aoso -Dsonar.sources=. -Dsonar.host.url=http://192.168.56.113:9000 
 // -Dsonar.login=ab9f339761ec69b84c33072c739b28b604d3f8ce
                    sh "${scannerHome}/bin/sonar-scanner -X  -D /k: Aoso -D /d:sonar.host.url= http://192.168.56.113:9000  -D /d:sonar.login= ab9f339761ec69b84c33072c739b28b604d3f8ce "
                    sh "dotnet build DevOpsProject.csproj"
                    sh 'dotnet sonarscanner end /d:sonar.login="aoso"'
                    }
                }
                }
            /*stage('SonarQube analysis') {

                    def scannerHome = tool 'SonarScanner for MSBuild ';

                    withSonarQubeEnv('sonarQube') {

                                dir("source/${config.ProjectName}") 

                                sh " ls -la ${pwd()}"

                                    // sh "dotnet tool install --global dotnet-sonarscanner"

                                // sh (""" ${scannerHome}/bin/sonar-scanner begin k:"Aoso" /d:sonar.host.url='http://192.168.56.113:9000'""")
                                sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll begin /k:\"Aoso\""
                                sh "dotnet build DevOpsProject.csproj"
                                sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll end"


                            
                    }}*/

            
                /*stage("SonarQube analysis") {
                    def sonarScanner = tool name: 'SonarScanner for MSBuild', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                    sh "${sonarScanner}/bin/sonar-scanner begin k:'Aoso' /d:sonar.host.url='http://192.168.56.113:9000'"
                    sh "dotnet build DevOpsProject.csproj"
                    sh "dotnet ${sonarScanner}/bin/sonar-scanner end"   
                            
                    
                }*/
            
            
            stage ('copy all file from BACK')
            {    
                 sh "ls -la ${pwd()}"
                 sh "ls -la ${pwd()}/source"
                 sh "ls -la ${pwd()}/${config.BackPath}"
                 //sh "cd /var/jenkins_home/workspace/"
                 sh "mkdir -p /srv/aoso/DevOps/backend "
                 //sh "rm -rf /var/jenkins_home/workspace/aoso/DevOps/back"
                sh "ls -a ${config.DestinationFolder1}"
                sh "ls -a ${config.DestinationFolder2}"
                sh "ls -a ${config.DestinationFile}"
                 //sh "rm -rf ${config.DestinationFile}"
                 //sh "cp -r ${pwd()}/source/DevOpsProject ${config.DestinationFile}"
                 //sh "rm -rf ${config.DestinationFile}/Dockerfile"
                 sh "cp -r ${pwd()}${config.BackPath} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.DockerfileLocation} ${config.DestinationFile}${config.ProjectName}"
                 sh "cp -r ${pwd()}${config.dockerComposeFileLocation} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.nginxLocation} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.elasticsearch} ${config.DestinationFile}"
                 sh "cp -r ${pwd()}${config.dockerComposeSonarQube} ${config.dockerComposeSonarQubeDestintion}"

                 sh "ls -la ${config.DestinationFile}/nginx "
                 sh "ls -la ${config.DestinationFile} "
            }
           
            stage('Elasticsearch') 
                {  //sh "${config.dockerComposeLocation} -f ${config.dockerComposeElasticDestintination} build "
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeElasticDestintination} up -d"
                    echo "Buid Image with docker-compose"
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
                        echo"Validate"
                    }
                    else
                    {
                           
                        //slackSend color: 'danger', channel: '#devops', message: "<${currentBuild.absoluteUrl}|Server build ${env.BUILD_NUMBER}> failed to deploy build "
	                       echo"Don't have access"
                    }
                 }
                }

                stage('location of docker-compose') 
                {  //sh "${config.dockerComposeLocation} -f ${config.dockerComposeDestination} build"
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeDestination} up -d"
                    echo "Buid Image with docker-compose"
                    //echo "${config.dockerfileLocation}",
                }
        }
              
            
}