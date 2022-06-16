def call (Map config)
{
    node
    {
       stage('Quality Gate') 
            {   def scannerHome = tool name: 'sonarscanner';
                    withSonarQubeEnv('SonarQube') {
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
    }
}