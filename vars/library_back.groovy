/*def call (Map config)
{
    node
       
           /* stage('location of dockerfile') 
                {  
                    sh "docker build -t teeeeeest -f ${config.dockerfileLocation} ."
                    echo "checking out the source dockerfile "
                    //echo "${config.dockerfileLocation}",
                }*/
            /*stage('Container of SonarQube') 
                {  //sh "${config.dockerComposeLocation} -f ${config.dockerComposeElasticDestintination} build "
                    sh "${config.dockerComposeLocation} -f ${config.dockerComposeSonarQubeDestintion} up -d"
                    echo "Buid Image with docker-compose sonar "
                    //echo "${config.dockerfileLocation}",
                }*/
           /*stage('Quality Gate') 
                {   scannerHome = tool name: 'sonarscanner';
                    withSonarQubeEnv('sonarQube') {
                    //sh "dotnet restore source/DevOpsProject/DevOpsProject/DevOpsProject.csproj"
                    dir("source/${config.ProjectName}") {
                    sh " ls -la ${pwd()}"
                    //sh "dotnet ${scannerHome}/bin/sonar-scanner  /k:Aoso /d:sonar.host.url= http://192.168.56.113:9000"
                    //sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Aoso -Dsonar.sources=. -Dsonar.host.url=http://192.168.56.113:9000 -Dsonar.login=ab9f339761ec69b84c33072c739b28b604d3f8ce "
                    //sh "${scannerHome}/bin/sonar-scanner -D /k:Aoso -D /d:sonar.host.url=http://192.168.56.113:9000  -D /d:sonar.login=ab9f339761ec69b84c33072c739b28b604d3f8ce "
                    //sh "dotnet build DevOpsProject.csproj"
                    sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=Aoso "
                    
                    }
                }
            }*/
            
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

            
           /*stage("SonarQube analysis")
            {
                        MSBUILD_SQ_SCANNER_HOME = tool 'sonarscanner'//, type: 'hudson.plugins.sonar.MsBuildSQRunnerInstallation';
                        withSonarQubeEnv('sonarQube') {
                        dir("source/${config.ProjectName}")
                    {
                        sh " ls -la ${pwd()}"
                        // sh "dotnet tool install --global dotnet-sonarscanner"
                        sh "${MSBUILD_SQ_SCANNER_HOME}/bin/sonar-scanner -Dsonar.projectKey=Aoso -Dsonar.sources=. -Dsonar.host.url=http://192.168.56.113:9000 -Dsonar.login=ab9f339761ec69b84c33072c739b28b604d3f8ce "
                        // sh ("${scannerHome}/bin/sonar-scanner begin -D /k:AosoDevops -D /d:sonar.host.url=http://localhost:9000 -D /d:sonar.login=23b5d4c1c1f76c539f1d0019945228a4003d6a51")
                        sh "dotnet build "
                        sh "${MSBUILD_SQ_SCANNER_HOME}/bin/sonar-scanner end" 
                        }
                    }
            }
            */
            /*stage('Quality Gate') {

                    //def scannerHome = tool 'sonarscanner';
                        def scannerHome = tool name: 'SonarScanner for MSBuild'
                        // MSBUILD_SQ_SCANER_HOME = tool 'sonarscanner'//, type: 'hudson.plugins.sonar.MsBuildSQRunnerInstallation';
                        withSonarQubeEnv('sonarQube') {
                        dir("source/${config.ProjectName}") {
                        sh " ls -la ${pwd()}"
                        
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll begin /k:\"Aoso\" /d:sonar.login=ab9f339761ec69b84c33072c739b28b604d3f8ce "
                        sh "dotnet build"
                        sh "dotnet ${scannerHome}/SonarScanner.MSBuild.dll end /d:sonar.login=ab9f339761ec69b84c33072c739b28b604d3f8ce"
                        }
                        }
            }*/

            
                 /* stage('Quality code analysis') 
                {   def scannerHome = tool name: 'sonarscanner';
                    withSonarQubeEnv('sonarQube') {
                    //sh "dotnet restore source/DevOpsProject/DevOpsProject/DevOpsProject.csproj"
                            dir("${config.ProjectName}") {
                                    sh " ls -la ${pwd()}"
                                    //sh "dotnet ${scannerHome}/bin/sonar-scanner  /k:AosoFront /d:sonar.host.url= http://192.168.56.113:9000"
                                    sh "${scannerHome}/bin/sonar-scanner begin -Dsonar.projectKey=AosoBack -Dsonar.login=a650a854dfc5fdfd835f432b6cbf52f369f6a2b1 -Dsonar.scm.provider=git "
                                    //sh "${scannerHome}/bin/sonar-scanner -D /k:Aoso -D /d:sonar.host.url=http://192.168.56.113:9000  -D /d:sonar.login=ab9f339761ec69b84c33072c739b28b604d3f8ce "
                                    sh "dotnet build "
                                    sh "${scannerHome}/bin/sonar-scanner end -Dsonar.projectKey=Aosofront "
                                    
                            }
                }
                }*/
                                    
           
            
            

 